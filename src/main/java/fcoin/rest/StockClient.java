package fcoin.rest;

import base.dao.OrderInfoMapper;
import base.model.OrderInfo;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import fcoin.rest.stock.IStockRestApi;
import fcoin.rest.stock.impl.StockRestApi;
import org.apache.http.HttpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 现货 REST API 客户端请求
 *
 */
@Service("stockClient")
public class StockClient {

    static String api_key = "***";  //OKCoin申请的apiKey
    static String secret_key = "***";  //OKCoin申请的secretKey
    static String url_prex = "https://api.fcoin.com/v2";

    static IStockRestApi stockGet;


    private static final Logger logger = LoggerFactory.getLogger(StockClient.class);
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    public void insertOrder(OrderInfo orderDTO){
        orderInfoMapper.insertOrder(orderDTO);
    }

    public static void main(String[] args) throws HttpException, IOException {
//        URL prop = Thread.currentThread().getContextClassLoader().getResource("properties/log4j.properties");
//        PropertyConfigurator.configure(prop);

        stockGet = new StockRestApi(url_prex, api_key, secret_key);
        ScheduledThreadPoolExecutor scheduledExecutor = new ScheduledThreadPoolExecutor(1);
        /**
         * new timeTaskForException() 要执行的任务线程
         * 1000：延迟多长时间执行
         * 2000: 每隔多少长时间执行一次
         * TimeUnit.MILLISECONDS：时间单位
         */
        scheduledExecutor.scheduleAtFixedRate(new timerTask(), 0, 8000, TimeUnit.MILLISECONDS);
    }


    public static JSONArray getOrderList(IStockRestApi restApi) throws HttpException, IOException{
        // 查订单列表，若有未提交，则取消
        String orderList = restApi.getOrderList("btcusdt","submitted,partial_filled","","","");
        JSONObject jsonOrder = JSONObject.parseObject(orderList);
        JSONArray orderArray = jsonOrder.getJSONArray("data");
        return orderArray;
    }

    public static JSONArray getBalance(IStockRestApi restApi) throws HttpException, IOException{
        String balance = restApi.queryBalance();
        JSONObject jsonBalance = JSONObject.parseObject(balance);
        JSONArray jsonArray = jsonBalance.getJSONArray("data");
        return jsonArray;
    }

    public static void cancelOrder(JSONArray orderArray,IStockRestApi restApi) throws HttpException, IOException{
        for (Object object : orderArray) {
            JSONObject jsonObjectone = (JSONObject) object;
            String orderID = jsonObjectone.getString("id");
            restApi.cancelOrder(orderID);
        }
    }

    public static void judge(JSONArray balanceArray,IStockRestApi restApi) throws HttpException, IOException{
        BigDecimal usdtAmount ;
        BigDecimal btcAmount ;
        DecimalFormat df = new DecimalFormat("0.00");
        BigDecimal buyPrice ;
        BigDecimal sellPrice ;
        BigDecimal huadian= new BigDecimal("0.05");

        //遍历JSONArray
        for (Object object : balanceArray) {
            JSONObject jsonObjectone = (JSONObject) object;
            String currency = jsonObjectone.getString("currency");

            if("btc".equals(currency)){
                btcAmount = jsonObjectone.getBigDecimal("available");
                String msg = restApi.getDepthInfo("btcusdt","L20");
                JSONObject jsonObj = JSONObject.parseObject(msg);
                JSONObject jsonData = jsonObj.getJSONObject("data");
                JSONArray jsonAsks = jsonData.getJSONArray("asks");
                JSONArray jsonBids = jsonData.getJSONArray("bids");

                logger.info("当前账户btc余额：{}",btcAmount);
                if(btcAmount.compareTo(new BigDecimal(0.1))>0){
                    // 查询卖二
                    logger.info("卖btc");
                    sellPrice = ((BigDecimal)jsonAsks.get(2)).setScale(2).subtract(huadian);
                    btcAmount = btcAmount.setScale(3,BigDecimal.ROUND_FLOOR);
                    restApi.trade("btcusdt","limit",sellPrice.toString(),btcAmount.toString(),"sell");
                    //  break;
                }
            }

            if("usdt".equals(currency)){
                usdtAmount = jsonObjectone.getBigDecimal("available");
                String msg = restApi.getDepthInfo("btcusdt","L20");
                JSONObject jsonObj = JSONObject.parseObject(msg);
                JSONObject jsonData = jsonObj.getJSONObject("data");
                JSONArray jsonAsks = jsonData.getJSONArray("asks");
                JSONArray jsonBids = jsonData.getJSONArray("bids");

                logger.info("当前账户usdt余额:{}",usdtAmount);
                // 若usdt可用大于10，查询买二价格并下单买btc
                if(usdtAmount.compareTo(new BigDecimal(100))>0){
                    // 查询卖二
                    logger.info("买btc");
                    buyPrice = ((BigDecimal)jsonBids.get(2)).setScale(2).add(huadian);

                    btcAmount = usdtAmount.divide(buyPrice, 4,BigDecimal.ROUND_FLOOR);
                    logger.info("买入btc数量:{}",btcAmount);
                    restApi.trade("btcusdt","limit",buyPrice.toString(),btcAmount.toString()
                            ,"buy");
                    //   break;
                }
            }
        }
    }


    static class timerTask implements Runnable{
        public void run()  {
            // 行情信息查询
            try {
                // 查订单列表，若有未提交，则取消订单
                JSONArray orderArray = getOrderList(stockGet);
                if(orderArray.size()>0){
                    cancelOrder(orderArray,stockGet);
                    return;  // 取消订单，程序结束，等待下一个15s
                }
                // 查询账户余额
                JSONArray balanceArray = getBalance(stockGet);
                judge(balanceArray,stockGet);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}



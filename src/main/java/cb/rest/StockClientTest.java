package cb.rest;

import cb.rest.stock.IStockRestApi;
import cb.rest.stock.impl.StockRestApi;
import cb.util.LimitQueue;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import common.CBconstants;
import common.CPconstants;
import org.apache.http.HttpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * 现货 REST API 客户端请求
 */
public class StockClientTest {
    private static final Logger logger = LoggerFactory.getLogger(StockClientTest.class);
    static IStockRestApi restApi = new StockRestApi(CBconstants.URL_PREX, CBconstants.TEST_API_KEY, CBconstants.TEST_SECRET_KEY);

    static int SLEEP_TIME = 1200;
    static int CYCLE_TIME = 10000;
    static BigDecimal huadian = new BigDecimal("0.05");
    static boolean isCannel = false;

    //监控3分钟内容
    static LimitQueue<BigDecimal> pricelist = new LimitQueue<BigDecimal>(30 * 6000 / CYCLE_TIME);

    // CP最低价格设置
    static BigDecimal LOW_SELLPRICE = new BigDecimal("0.29");

    // CP挖矿安全价格
    static BigDecimal MAX_MINE_PRICE = new BigDecimal("0.32");

    static ArrayList<String> al = new ArrayList();

    public static void main(String[] args) throws Exception {

        getBalance("BTC");


        BigDecimal buyPrice;
        String depthInfo = restApi.getDepthInfo(CBconstants.BTC_USDT_PAIR, "5");
        JSONObject jsonDepth = JSONObject.parseObject(depthInfo);
        JSONObject jsonData = jsonDepth.getJSONObject("data");
        JSONArray jsonAsks = jsonData.getJSONArray("asks");
        JSONArray jsonBids = jsonData.getJSONArray("bids");
        System.out.println(jsonAsks);
        buyPrice = (BigDecimal)((JSONArray) jsonAsks.get(1)).get(0);
        System.out.println(buyPrice);
        restApi.trade("BTC_USDT","buy","11","0.1");

        Thread.sleep(3000);
        getOrderList("BTC_USDT");
        Thread.sleep(3000);
        cancelOrder(al);

    }

    public static BigDecimal getOkBTCPrice() throws HttpException, IOException {
        String url_prex = "https://www.okb.com";  //注意：请求URL https://www.okex.cn
        okcoin.rest.stock.IStockRestApi stockGet = new okcoin.rest.stock.impl.StockRestApi(url_prex);

        // 行情信息查询
        String tickerResult = stockGet.ticker("btc_usdt");
//        logger.info(tickerResult);
        JSONObject jsonOrder = JSONObject.parseObject(tickerResult);

        String tradeOrderV1 = JSONObject.parseObject(jsonOrder.getString("ticker")).getString("buy");
        logger.info("okPrice->" + tradeOrderV1);
        return new BigDecimal(tradeOrderV1);
    }


    public static ArrayList<String> getOrderList(String symbol) throws Exception {
        // 查订单列表，若有未提交，则取消
        //  1未成交,2部分成交
        String orderList1 = restApi.getOrderList(symbol,"5","1");
        JSONObject jsonOrder1 = JSONObject.parseObject(orderList1);
        JSONArray orderArray1 = jsonOrder1.getJSONObject("data").getJSONArray("orders");

        Thread.sleep(1000);
        String orderList2 = restApi.getOrderList(symbol,"5","2");
        JSONObject jsonOrder2 = JSONObject.parseObject(orderList2);
        JSONArray orderArray2 = jsonOrder2.getJSONObject("data").getJSONArray("orders");

        for (Object object : orderArray1) {
            JSONObject jsonObject1 = (JSONObject) object;
            String orderID1 = jsonObject1.getString("order_id");
            al.add(orderID1);
        }

        for (Object object : orderArray2) {
            JSONObject jsonObject2 = (JSONObject) object;
            String orderID2 = jsonObject2.getString("order_id");
            al.add(orderID2);
        }
        return al;
    }


    /*public static JSONArray getOrderList(String symbol) throws HttpException, IOException {
        // 查订单列表，若有未提交，则取消
        String orderList = restApi.getOrderList(symbol,"5","1");
        JSONObject jsonOrder = JSONObject.parseObject(orderList);
        JSONArray orderArray = jsonOrder.getJSONObject("data").getJSONArray("orders");
        return orderArray;
    }
    */

    public static JSONObject getBalance(String symbol) throws HttpException, IOException {
        String balance = restApi.queryBalance(symbol);
        JSONObject jsonBalance = JSONObject.parseObject(balance);
        JSONObject balanceObj = jsonBalance.getJSONObject("data").getJSONObject("info").getJSONObject("free");
        logger.info("balanceArray:{}",balanceObj);
        return balanceObj;
    }

    /*
     *  访问频率 1次/1秒
     */
    public static void cancelOrder(ArrayList<String> al) throws Exception {
        for (String orderID : al) {
            Thread.sleep(1000);
            restApi.cancelOrder(orderID);
        }
        al.clear();
    }

    public static void buy(JSONObject balanceObj, BigDecimal okprice) throws HttpException, IOException {
        BigDecimal usdtAmount;
        DecimalFormat df = new DecimalFormat("0.00");
        BigDecimal buyPrice;
        BigDecimal sellPrice;
        //遍历JSONArray
        logger.info("buy check in ");
        usdtAmount = balanceObj.getBigDecimal("USDT");
        String depthInfo = restApi.getDepthInfo(CPconstants.CP_USDT_PAIR, "5");
        JSONObject jsonDepth = JSONObject.parseObject(depthInfo);
        JSONObject jsonData = jsonDepth.getJSONObject("data");
        JSONArray jsonAsks = jsonData.getJSONArray("asks");
        JSONArray jsonBids = jsonData.getJSONArray("bids");
        logger.info("usdtAmount:{}", usdtAmount);

        if (usdtAmount.compareTo(new BigDecimal(1)) > 0) {
            // 查询卖二
            buyPrice = (BigDecimal)((JSONArray) jsonAsks.get(1)).get(0);
            buyPrice = buyPrice.setScale(2, BigDecimal.ROUND_FLOOR).add(huadian);
            logger.info("当前价卖2价:" + buyPrice.toString() + ",当前价Ok:" + okprice.toString());
            if (buyPrice.subtract(okprice).compareTo(new BigDecimal(5)) > 0) {
                //FT价格高于OK，2USDT，放弃购买
                logger.info("FT价格高于OK价格超过10USDT，不购买！");
                return;
            }
            String kk = usdtAmount.divide(buyPrice, 4, BigDecimal.ROUND_FLOOR).toString();
            logger.info("！！！开始购买buyPrice:" + buyPrice.toString() + ",amount:" + kk.toString());

            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            restApi.trade("BTC_USDT","buy","11","0.1");
            //   break;
        }
    }

    public static void sell(JSONObject balanceObj, BigDecimal okprice) throws HttpException, IOException {
        BigDecimal usdtAmount;
        BigDecimal btcAmount;
        DecimalFormat df = new DecimalFormat("0.00");
        BigDecimal buyPrice;
        BigDecimal sellPrice;
        //遍历JSONArray
        logger.info("buy check in ");
        btcAmount = balanceObj.getBigDecimal("BTC");
        String depthInfo = restApi.getDepthInfo(CPconstants.CP_USDT_PAIR, "5");
        JSONObject jsonDepth = JSONObject.parseObject(depthInfo);
        JSONObject jsonData = jsonDepth.getJSONObject("data");
        JSONArray jsonAsks = jsonData.getJSONArray("asks");
        JSONArray jsonBids = jsonData.getJSONArray("bids");
        logger.info("usdtAmount:{}", btcAmount);

        if (btcAmount.compareTo(new BigDecimal(0.001)) > 0) {
            // 查询买二
            sellPrice = (BigDecimal)((JSONArray) jsonBids.get(1)).get(0);
            sellPrice = sellPrice.setScale(2, BigDecimal.ROUND_FLOOR).add(huadian);
            logger.info("当前买2价:" + sellPrice.toString() + ",当前价Ok:" + okprice.toString());
            if (sellPrice.subtract(okprice).compareTo(new BigDecimal(5)) > 0) {
                //FT价格高于OK，2USDT，放弃购买
                logger.info("FT价格高于OK价格超过10USDT，不购买！");
                return;
            }
            String kk = btcAmount.divide(sellPrice, 4, BigDecimal.ROUND_FLOOR).toString();
            logger.info("！！！开始购买buyPrice:" + sellPrice.toString() + ",amount:" + kk.toString());

            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            restApi.trade("BTC_USDT","sell","11","0.1");
            //   break;
        }
    }
}







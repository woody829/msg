package cc.rest;

import cc.rest.stock.IStockRestApi;
import cc.rest.stock.impl.StockRestApi;
import cc.util.LimitQueue;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import common.CBconstants;
import common.CPconstants;
import org.apache.http.HttpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 现货 REST API 客户端请求
 */
public class StockClient {
    private static final Logger logger = LoggerFactory.getLogger(StockClient.class);
    static IStockRestApi restApi = new StockRestApi(CPconstants.URL_PREX, CPconstants.MINE_API_KEY, CPconstants.MINE_SECRET_KEY);

    static int SLEEP_TIME = 1200;
    static int CYCLE_TIME = 7000;
    static BigDecimal HUADIAN = new BigDecimal("0.005");
    static BigDecimal CP_HUADIAN = new BigDecimal("0.0004");

    //监控3分钟内容
    static LimitQueue<BigDecimal> pricelist = new LimitQueue<BigDecimal>(3000 * 6000 / CYCLE_TIME);

    // CP最低价格设置
    static BigDecimal CB_LOW_SELLPRICE = new BigDecimal("0.075");

    // CP挖矿安全价格
    static BigDecimal MAX_MINE_PRICE = new BigDecimal("0.07");

    //买卖开始时间
    static int BuyStartHour = 100;

    //1买卖结束时间
    static int BuyEndHour = 2400;

    //超过OK多少就不买
    static BigDecimal OKpriceStopBuyLimit = new BigDecimal("2");

    //超过OK多少就不卖
    static BigDecimal OKpriceStopSellLimit = new BigDecimal("4");

    //USDT 保留的底仓
    static BigDecimal BuyKeepLimit = new BigDecimal("10");

    //BTC 保留的底仓
    static BigDecimal SellKeepLimit = new BigDecimal("0.001");

    //是否开启OK监控策略
    static boolean isMonitorOK = false;

    //是否砸盘CB
    static boolean isSellCB = false;

    //是否自买自卖
    static boolean isAutoTrade = true;


    public static void main(String[] args) throws HttpException, IOException {
        ScheduledThreadPoolExecutor scheduledExecutor = new ScheduledThreadPoolExecutor(1);
        /**
         * new timeTaskForException() 要执行的任务线程
         * 1000：延迟多长时间执行
         * 2000: 每隔多少长时间执行一次
         * TimeUnit.MILLISECONDS：时间单位
         */
        //   scheduledExecutor.scheduleAtFixedRate(new timerTask_auto(), 0, CYCLE_TIME, TimeUnit.MILLISECONDS);
        scheduledExecutor.scheduleAtFixedRate(new timerTask_quick(), 0, 5000, TimeUnit.MILLISECONDS);
//        scheduledExecutor.scheduleAtFixedRate(new timerTask_quick(), 0, 10000, TimeUnit.MILLISECONDS);
//        scheduledExecutor.scheduleAtFixedRate(new timerTask_sellft(), 0, CYCLE_TIME, TimeUnit.MILLISECONDS);
    }

    public static BigDecimal getOkPrice(String symbol) throws HttpException, IOException {
        String url_prex = "https://www.okb.com";  //注意：请求URL https://www.okex.cn
        okcoin.rest.stock.IStockRestApi stockGet = new okcoin.rest.stock.impl.StockRestApi(url_prex);
        // 行情信息查询
        String tickerResult = stockGet.ticker(symbol);
        JSONObject jsonOrder = JSONObject.parseObject(tickerResult);
        String tradeOrderV1 = JSONObject.parseObject(jsonOrder.getString("ticker")).getString("buy");
        logger.info("okPrice->" + tradeOrderV1);
        return new BigDecimal(tradeOrderV1);
    }


    /**
     * 获取订单列表
     *
     * @param symbol 币种
     * @return 未成交、部分成交的挂单
     * 访问频率 1次/1秒
     */
    public static JSONArray getOrderList(String pair, String symbol) throws HttpException, IOException {
        // 查订单列表，若有未提交，则取消
        // 最后一个参数不传
        // // "BTC_USDT", 0,     1,   10,    "BTC",      "USDT",          1);
        String orderList = restApi.getOrderList(pair, 0, 1, 10, symbol, "USDT", 1);
        JSONObject jsonOrder = JSONObject.parseObject(orderList);
        JSONArray orderArray = ((JSONObject) jsonOrder.getJSONArray("result").get(0)).getJSONObject("result").getJSONArray("items");
        return orderArray;
    }


    /**
     * 获取委托价格
     *
     * @param symbol 币种
     * @return 币种对应可用数量
     * 访问频率 1次/1秒
     */
    public static BigDecimal getBalance(String symbol) throws HttpException, IOException {
        BigDecimal amount = new BigDecimal(0);
        String balance = restApi.queryBalance();
        JSONObject jsonBalance = JSONObject.parseObject(balance);
        JSONArray balanceArray = ((JSONObject) jsonBalance.getJSONArray("result").get(0)).getJSONObject("result").getJSONArray("assets_list");
        //遍历JSONArray
        for (Object object : balanceArray) {
            JSONObject jsonObjectone = (JSONObject) object;
            String currency = jsonObjectone.getString("coin_symbol");
            if (symbol.equals(currency)) {
                amount = jsonObjectone.getBigDecimal("balance");
                return amount;
            }
        }
        return amount;
    }


    /**
     * 获取委托价格
     *
     * @param num       委托档次
     * @return 盘口价格
     * 访问频率 1次/1秒
     */
    public static HashMap<String,BigDecimal> getEntrustPrice(String pair, int num) throws Exception {
        HashMap<String,BigDecimal> hm = new HashMap();

        BigDecimal asksPrice;
        BigDecimal bidsPrice;
        String depthInfo = restApi.getDepthInfo(pair, "5");
        JSONObject jsonDepth = JSONObject.parseObject(depthInfo);
        JSONObject jsonData = jsonDepth.getJSONObject("result");
        JSONArray jsonAsks = jsonData.getJSONArray("asks");
        JSONArray jsonBids = jsonData.getJSONArray("bids");
        if(jsonData == null){
            logger.error("获取委托价格异常:{}",pair);
            return null;
        }
        asksPrice = ((JSONObject) jsonAsks.get(num)).getBigDecimal("price").setScale(4, BigDecimal.ROUND_FLOOR);
        bidsPrice = ((JSONObject) jsonBids.get(num)).getBigDecimal("price").setScale(4, BigDecimal.ROUND_FLOOR);

        hm.put("asks",asksPrice);
        hm.put("bids",bidsPrice);
        return hm;
    }

    /**
     * 获取委托价格
     *
     * @param orderArray 挂单订单列表
     *                   访问频率 1次/1秒
     */
    public static void cancelOrder(JSONArray orderArray) throws Exception {
        for (Object object : orderArray) {
            JSONObject jsonObjectone = (JSONObject) object;
            String orderID = jsonObjectone.getString("id");
            String status = jsonObjectone.getString("status");
            // 状态，1-待成交，2-部分成交，3-完全成交，4-部分撤销，5-完全撤销，6-待撤销
            if (!StringUtils.isEmpty(status)) {
                if ("1".equals(status) || "2".equals(status)) {
                    restApi.cancelOrder(orderID);
                    Thread.sleep(1000l);
                }
            }
        }
    }


    /*
     * 自动买卖时间
     *
     */
    public static boolean checkTime() throws HttpException, IOException {
        SimpleDateFormat df = new SimpleDateFormat("HHmm");//设置日期格式
        int HourMin = Integer.parseInt(df.format(new Date()).toString());
        logger.info("Now:" + HourMin);

        if (HourMin < BuyStartHour) {
            return false;
        }
        if (HourMin > BuyEndHour) {
            return false;
        }
        return true;
    }


    public static void buy(String pair,BigDecimal midPrice,BigDecimal okprice) throws Exception {
        BigDecimal buyPrice;
        if (checkTime() == false) {
            logger.info("时间未到，开始时间为：" + BuyStartHour + "结束时间为" + BuyEndHour);
            return;
        }
        BigDecimal usdtAmount = getBalance("USDT");

        if (isAutoTrade) {
            usdtAmount = usdtAmount.divide(new BigDecimal(2), 4, BigDecimal.ROUND_FLOOR);
        }

        logger.info("buy check in usdtAmount:{}", usdtAmount);
        if (usdtAmount.compareTo(BuyKeepLimit) > 0) {
            // 查询卖二
            buyPrice = midPrice.setScale(2, BigDecimal.ROUND_FLOOR).add(HUADIAN);
            logger.info("当前价CP:" + buyPrice.toString() + ",当前价Ok:" + okprice.toString());
            if (buyPrice.subtract(okprice).compareTo(OKpriceStopBuyLimit) > 0) {
                //FT价格高于OK，2USDT，放弃购买
                logger.info("CB价格高于OK价格超过:" + OKpriceStopBuyLimit);
                return;
            }
            String amount = usdtAmount.divide(buyPrice, 4, BigDecimal.ROUND_FLOOR).toString();
            logger.info("！！！开始购买buyPrice:" + buyPrice.toString() + ",amount:" + amount.toString());
            restApi.trade(pair, 0, 2, 1, buyPrice, new BigDecimal(amount));
        }
    }

    // 1.底仓判断  2.ok\cb上BTC差价比较  3.卖BTC
    // 访问频率 3秒/次
    public static void sell(String pair,BigDecimal midPrice,BigDecimal okprice) throws Exception {
        BigDecimal sellPrice;
        BigDecimal ethAmount = getBalance("ETH");
        logger.info("sell check in ethAmount:{}", ethAmount);
        // btc底仓判断
        if (ethAmount.compareTo(SellKeepLimit) > 0) {
            // 查询买二
            sellPrice = midPrice.setScale(2, BigDecimal.ROUND_FLOOR).subtract(HUADIAN);
            logger.info("当前买2价:" + sellPrice.toString() + ",当前价Ok:" + okprice.toString());
            // OK网价格与CP网价格比较
            if (okprice.subtract(sellPrice).compareTo(OKpriceStopSellLimit) > 0) {
                logger.info("CB价格低于OK价格超过限制" + OKpriceStopSellLimit);
                return;
            }
            ethAmount = ethAmount.setScale(3, BigDecimal.ROUND_FLOOR);
            logger.info("sellPrice:" + sellPrice.toString() + ", amount:" + ethAmount.toString());
            // order_side,     //交易方向，1-买，2-卖
            restApi.trade(pair, 0, 2, 2, sellPrice, ethAmount);

        }
    }

    public static void sell_cb() throws Exception {
        BigDecimal cbAmount = getBalance("CP");
        BigDecimal sellPrice = getEntrustPrice(CBconstants.CP_USDT_PAIR, 1).get("bids");
        sellPrice = sellPrice.subtract(CP_HUADIAN);


        if (cbAmount.compareTo(new BigDecimal(1)) > 0) {
            if (sellPrice.compareTo(CB_LOW_SELLPRICE) < 0) {
                logger.info("价格太低不卖！当前价格:" + sellPrice + "，当前价格底线：" + CB_LOW_SELLPRICE);
                return;
            }

            /*
            // 获取未成交订单列表
            JSONArray orderArray = getOrderList(CPconstants.CP_USDT_PAIR,"CP");
            // 取消挂单
            if (orderArray.size() > 0) {
                cancelOrder(orderArray);
            }
            */

            // 查询卖二
            sellPrice = sellPrice.setScale(4, BigDecimal.ROUND_FLOOR);
            cbAmount = cbAmount.setScale(2, BigDecimal.ROUND_FLOOR);
            logger.info("！！！卖出CP价格：" + sellPrice + "数量:" + cbAmount);
            restApi.trade(CPconstants.CP_USDT_PAIR, 0, 2, 2, sellPrice, cbAmount);
        } else {
            logger.info("数量不足，不卖CP");
        }
    }


    static class timerTask_quick implements Runnable {
        public void run() {
            // 行情信息查询
            try {
                logger.info("---------START快速买卖版本-------->");
                // 获取未成交订单列表
                JSONArray orderArray = getOrderList(CPconstants.ETH_USDT_PAIR,"ETH");
                // 取消挂单
                if (orderArray.size() > 0) {
                    cancelOrder(orderArray);
                }
                //获取OK网站金额信息
                BigDecimal okprice = getOkPrice("eth_usdt");
                pricelist.offer(okprice);

                if (isSellCB) {
                    sell_cb();
                }

                BigDecimal asksPrice = getEntrustPrice(CPconstants.ETH_USDT_PAIR, 0).get("asks");
                BigDecimal bidsPrice = getEntrustPrice(CPconstants.ETH_USDT_PAIR, 0).get("bids");


                //卖出BTC，先卖后买
                sell(CPconstants.ETH_USDT_PAIR,bidsPrice,okprice);

                if (isMonitorOK) {
                    // ok网价格监控开启，进行价格判断
                    if (pricelist.jude(okprice)) {
                        buy(CPconstants.ETH_USDT_PAIR,asksPrice,okprice);
                    }
                } else {
                    // 直接购买
                    BigDecimal cbPrice = getEntrustPrice(CBconstants.CP_USDT_PAIR, 0).get("bids");
                    if(cbPrice.compareTo(MAX_MINE_PRICE) > 0){
                        logger.info("当前CB价格:{} 高于安全挖矿价格:{}" , cbPrice , MAX_MINE_PRICE);
                        return;
                    }
                    buy(CPconstants.ETH_USDT_PAIR,asksPrice,okprice);
                }
                logger.info("---------END----------->");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

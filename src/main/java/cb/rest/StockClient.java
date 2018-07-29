package cb.rest;

import cb.rest.stock.IStockRestApi;
import cb.rest.stock.impl.StockRestApi;
import cb.util.LimitQueue;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import common.CBconstants;
import org.apache.http.HttpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 现货 REST API 客户端请求
 */
public class StockClient {
    private static final Logger logger = LoggerFactory.getLogger(StockClient.class);
    static IStockRestApi restApi = new StockRestApi(CBconstants.URL_PREX, CBconstants.TEST_API_KEY, CBconstants.TEST_SECRET_KEY);

    static int SLEEP_TIME = 1200;
    static int CYCLE_TIME = 7000;
    static BigDecimal HUADIAN = new BigDecimal("0.01");
    static BigDecimal CB_HUADIAN = new BigDecimal("0.004");

    //监控3分钟内容
    static LimitQueue<BigDecimal> pricelist = new LimitQueue<BigDecimal>(3000 * 6000 / CYCLE_TIME);

    // CB最低价格设置
    static BigDecimal CB_LOW_SELLPRICE = new BigDecimal("0.29");

    // CP挖矿安全价格
    static BigDecimal MAX_MINE_PRICE = new BigDecimal("0.32");

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
        scheduledExecutor.scheduleAtFixedRate(new timerTask_quick(), 0, 8000, TimeUnit.MILLISECONDS);
//        scheduledExecutor.scheduleAtFixedRate(new timerTask_sellft(), 0, CYCLE_TIME, TimeUnit.MILLISECONDS);
    }

    public static BigDecimal getOkBTCPrice() throws HttpException, IOException {
        String url_prex = "https://www.okb.com";  //注意：请求URL https://www.okex.cn
        okcoin.rest.stock.IStockRestApi stockGet = new okcoin.rest.stock.impl.StockRestApi(url_prex);
        // 行情信息查询
        String tickerResult = stockGet.ticker("btc_usdt");
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
    public static JSONArray getOrderList(String symbol) throws Exception {
        String orderList = restApi.getOrderList(symbol, "5", "1,2");
        JSONObject jsonOrder = JSONObject.parseObject(orderList);
        JSONArray orderArray = jsonOrder.getJSONObject("data").getJSONArray("orders");
        Thread.sleep(1000l);
        return orderArray;
    }


    /**
     * 获取委托价格
     *
     * @param symbol 币种
     * @return 币种对应可用数量
     * 访问频率 1次/1秒
     */
    public static BigDecimal getBalance(String symbol) throws Exception {
        String balance = restApi.queryBalance(symbol);
        JSONObject jsonBalance = JSONObject.parseObject(balance);
        JSONObject balanceObj = jsonBalance.getJSONObject("data").getJSONObject("info").getJSONObject("free");
        logger.info("balanceArray:{}", balanceObj);
        BigDecimal balanceAmt = balanceObj.getBigDecimal(symbol);
        Thread.sleep(1000l);
        return balanceAmt;
    }

    /**
     * 获取委托价格
     *
     * @param tradeSide 买入 bids 卖出 asks
     * @param num       委托档次
     * @return 盘口价格
     * 访问频率 1次/1秒
     */
    public static BigDecimal getEntrustPrice(String pair, String tradeSide, int num) throws Exception {
        String depthInfo = restApi.getDepthInfo(pair, "5");
        JSONObject jsonDepth = JSONObject.parseObject(depthInfo);
        JSONObject jsonData = jsonDepth.getJSONObject("data");
        if(jsonData == null){
            logger.error("获取委托价格异常:{}",pair);
            return new BigDecimal(0);
        }
        JSONArray jsonPrice = jsonData.getJSONArray(tradeSide);
        BigDecimal entrustPrice = (BigDecimal) ((JSONArray) jsonPrice.get(num)).get(0);
        Thread.sleep(3000l);
        return entrustPrice;
    }

    /**
     * 获取委托价格
     *
     * @param orderArray 挂单订单列表
     *                   访问频率 1次/1秒
     */
    public static void cancelOrder(JSONArray orderArray) throws Exception {
        for (Object object : orderArray) {
            JSONObject jsonObject = (JSONObject) object;
            String orderID = jsonObject.getString("order_id");
            restApi.cancelOrder(orderID);
            Thread.sleep(1000);
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


    public static void buy(BigDecimal midPrice,BigDecimal okprice) throws Exception {
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
//            buyPrice = getEntrustPrice(CBconstants.BTC_USDT_PAIR,"asks", 1);
            buyPrice = midPrice.setScale(2, BigDecimal.ROUND_FLOOR).add(HUADIAN);
            logger.info("当前价CB:" + buyPrice.toString() + ",当前价Ok:" + okprice.toString());
            if (buyPrice.subtract(okprice).compareTo(OKpriceStopBuyLimit) > 0) {
                //FT价格高于OK，2USDT，放弃购买
                logger.info("CB价格高于OK价格超过:" + OKpriceStopBuyLimit);
                return;
            }
            String amount = usdtAmount.divide(buyPrice, 4, BigDecimal.ROUND_FLOOR).toString();
            logger.info("！！！开始购买buyPrice:" + buyPrice.toString() + ",amount:" + amount.toString());
            restApi.trade("BTC_USDT", "buy", buyPrice.toString(), amount);
        }
        Thread.sleep(3000l);
    }

    // 1.底仓判断  2.ok\cb上BTC差价比较  3.卖BTC
    // 访问频率 3秒/次
    public static void sell(BigDecimal midPrice,BigDecimal okprice) throws Exception {
        BigDecimal sellPrice;
        BigDecimal btcAmount = getBalance("BTC");
        logger.info("sell check in btcAmount:{}", btcAmount);
        // btc底仓判断
        if (btcAmount.compareTo(SellKeepLimit) > 0) {
            // 查询买二
//            sellPrice = getEntrustPrice(CBconstants.BTC_USDT_PAIR,"bids", 1);
            sellPrice = midPrice.setScale(4, BigDecimal.ROUND_FLOOR).subtract(HUADIAN);
            logger.info("当前买2价:" + sellPrice.toString() + ",当前价Ok:" + okprice.toString());
            // OK网价格与CP网价格比较
            if (okprice.subtract(sellPrice).compareTo(OKpriceStopSellLimit) > 0) {
                logger.info("CB价格低于OK价格超过限制" + OKpriceStopSellLimit);
                return;
            }
            btcAmount = btcAmount.setScale(3, BigDecimal.ROUND_FLOOR);
            logger.info("sellPrice:" + sellPrice.toString() + ", amount:" + btcAmount.toString());
            restApi.trade("BTC_USDT", "sell", sellPrice.toString(), btcAmount.toString());
        }
        Thread.sleep(3000l);
    }

    public static void sell_cb() throws Exception {
        BigDecimal cbAmount = getBalance("CB");
        BigDecimal sellPrice = getEntrustPrice(CBconstants.CP_USDT_PAIR,"bids", 1);
        sellPrice = sellPrice.subtract(CB_HUADIAN);

        if (sellPrice.compareTo(CB_LOW_SELLPRICE) < 0) {
            logger.info("价格太低不卖！当前价格:" + sellPrice + "，当前价格底线：" + CB_LOW_SELLPRICE);
            return;
        }
        if (cbAmount.compareTo(new BigDecimal(1)) > 0) {
            // 查询卖二
            sellPrice = sellPrice.setScale(3, BigDecimal.ROUND_FLOOR);
            cbAmount = cbAmount.setScale(2, BigDecimal.ROUND_FLOOR);
            logger.info("！！！卖出CP价格：" + sellPrice + "数量:" + cbAmount);
            restApi.trade(CBconstants.CP_USDT_PAIR, "sell", sellPrice.toString(), cbAmount.toString());
            //  break;
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
                JSONArray orderArray = getOrderList(CBconstants.BTC_USDT_PAIR);
                // 取消挂单
                if (orderArray.size() > 0) {
                    cancelOrder(orderArray);
                }
                //获取OK网站金额信息
                BigDecimal okprice = getOkBTCPrice();
                pricelist.offer(okprice);

                if (isSellCB) {
                    sell_cb();
                }

                BigDecimal midPrice = getEntrustPrice(CBconstants.BTC_USDT_PAIR,"asks",0);

                //卖出BTC，先卖后买
                sell(midPrice,okprice);

                if (isMonitorOK) {
                    // ok网价格监控开启，进行价格判断
                    if (pricelist.jude(okprice)) {
                        buy(midPrice,okprice);
                    }
                } else {
                    // 直接购买
                    buy(midPrice,okprice);
                }
                logger.info("---------END----------->");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class timerTask_sellcb implements Runnable {
        public void run() {
            // 行情信息查询
            try {
                logger.info("---------START SELLING CP------>");
                // 获取CP可用余额
                BigDecimal cbAmount = getBalance("CB");
                // 查询cb挂单
                JSONArray orderArray = getOrderList(CBconstants.CP_USDT_PAIR);
                if (orderArray.size() > 0) {
                    cancelOrder(orderArray);
                }
                sell_cb();
                logger.info("---------END----------->");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}







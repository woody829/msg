package aporoo.rest;

import aporoo.rest.stock.IStockRestApi;
import aporoo.rest.stock.impl.StockRestApi;
import aporoo.util.LimitQueue;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import common.CPconstants;
import org.apache.http.HttpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 现货 REST API 客户端请求
 */
public class StockClientDD {
    private static final Logger logger = LoggerFactory.getLogger(StockClientDD.class);
    static IStockRestApi restApi = new StockRestApi(CPconstants.URL_PREX, CPconstants.TEST_API_KEY, CPconstants.TEST_SECRET_KEY);

    static int SLEEP_TIME = 1200;
    static int CYCLE_TIME = 10000;
    static BigDecimal huadian = new BigDecimal("0.1");
    static boolean isCannel = false;

    //监控3分钟内容
    static LimitQueue<BigDecimal> pricelist = new LimitQueue<BigDecimal>(3000 * 6000 / CYCLE_TIME);

    // CP最低价格设置
    static BigDecimal LOW_SELLPRICE = new BigDecimal("0.29");

    // CP挖矿安全价格
    static BigDecimal MAX_MINE_PRICE = new BigDecimal("0.32");

    //买卖开始时间
    static int BuyStartHour = 1200;

    //1买卖结束时间
    static int BuyEndHour = 2400;

    //超过OK多少就不买
    static BigDecimal OKpriceStopBuyLimit = new BigDecimal("10");

    //超过OK多少就不卖
    static BigDecimal OKpriceStopSellLimit = new BigDecimal("30");

    //USDT 保留的底仓
    static BigDecimal BuyKeepLimit = new BigDecimal("1000");


    //BTC 保留的底仓
    static BigDecimal SellKeepLimit = new BigDecimal("0.5");


    public static void main(String[] args) throws HttpException, IOException {
        ScheduledThreadPoolExecutor scheduledExecutor = new ScheduledThreadPoolExecutor(2);
        /**
         * new timeTaskForException() 要执行的任务线程
         * 1000：延迟多长时间执行
         * 2000: 每隔多少长时间执行一次
         * TimeUnit.MILLISECONDS：时间单位
         */
        // 对倒交易
        scheduledExecutor.scheduleAtFixedRate(new autoSell(), 0, CYCLE_TIME, TimeUnit.MILLISECONDS);
//        scheduledExecutor.scheduleAtFixedRate(new autoBuy(), 0, CYCLE_TIME, TimeUnit.MILLISECONDS);
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


    public static JSONArray getOrderList(String pair,String symbol) throws HttpException, IOException {
        // 查订单列表，若有未提交，则取消
        // 最后一个参数不传
        // // "BTC_USDT", 0,     1,   10,    "BTC",      "USDT",          1);
        String orderList = restApi.getOrderList(pair, 0, 1, 20, symbol, "USDT", 1);
        JSONObject jsonOrder = JSONObject.parseObject(orderList);
        JSONArray orderArray = ((JSONObject) jsonOrder.getJSONArray("result").get(0)).getJSONObject("result").getJSONArray("items");
        return orderArray;
    }

    public static JSONArray getBalance() throws HttpException, IOException {
        String balance = restApi.queryBalance();
        JSONObject jsonBalance = JSONObject.parseObject(balance);
        JSONArray balanceArray = ((JSONObject) jsonBalance.getJSONArray("result").get(0)).getJSONObject("result").getJSONArray("assets_list");
        return balanceArray;
    }

    public static void cancelOrder(JSONArray orderArray) throws HttpException, IOException {
        for (Object object : orderArray) {
            JSONObject jsonObjectone = (JSONObject) object;
            String orderID = jsonObjectone.getString("id");
            String status = jsonObjectone.getString("status");
            // 状态，1-待成交，2-部分成交，3-完全成交，4-部分撤销，5-完全撤销，6-待撤销
            if (!StringUtils.isEmpty(status)) {
                if ("1".equals(status) || "2".equals(status)) {
                    restApi.cancelOrder(orderID);
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


    public static void buy(JSONArray balanceArray, BigDecimal okprice) throws HttpException, IOException {
        BigDecimal usdtAmount;
        DecimalFormat df = new DecimalFormat("0.00");
        BigDecimal buyPrice;
        BigDecimal sellPrice;

        if (checkTime() == false) {
            logger.info("时间未到，开始时间为：" + BuyStartHour + "结束时间为" + BuyEndHour);
            return;
        }
        //遍历JSONArray
        for (Object object : balanceArray) {
            JSONObject jsonObjectone = (JSONObject) object;
            String currency = jsonObjectone.getString("coin_symbol");
            if ("USDT".equals(currency)) {
                logger.info("buy check in ");
                usdtAmount = jsonObjectone.getBigDecimal("balance");
                String depthInfo = restApi.getDepthInfo("BTC_USDT", "5");
                JSONObject jsonDepth = JSONObject.parseObject(depthInfo);
                JSONObject jsonData = jsonDepth.getJSONObject("result");
                JSONArray jsonAsks = jsonData.getJSONArray("asks");
                JSONArray jsonBids = jsonData.getJSONArray("bids");

                logger.info("usdtAmount:{}", usdtAmount);
                if (usdtAmount.compareTo(BuyKeepLimit) > 0) {
                    // 查询卖二
                    buyPrice = ((JSONObject) jsonAsks.get(1)).getBigDecimal("price");
                    buyPrice = buyPrice.setScale(2, BigDecimal.ROUND_FLOOR).add(huadian);
                    logger.info("当前价Ft:" + buyPrice.toString() + ",当前价Ok:" + okprice.toString());
                    if (buyPrice.subtract(okprice).compareTo(OKpriceStopBuyLimit) > 0) {
                        //FT价格高于OK，2USDT，放弃购买
                        logger.info("FT价格高于OK价格超过:" + OKpriceStopBuyLimit);
                        return;
                    }
                    String kk = usdtAmount.divide(buyPrice, 4, BigDecimal.ROUND_FLOOR).toString();
                    logger.info("！！！开始购买buyPrice:" + buyPrice.toString() + ",amount:" + kk.toString());

                    try {
                        Thread.sleep(SLEEP_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // pair   account_type order_type order_side price amount
                    // "BTC_USDT", 0,          1,        1,       10,    1);
                    restApi.trade("BTC_USDT", 0, 2, 1, buyPrice, new BigDecimal(kk));
                    //   break;
                }
            }
        }
    }

    public static boolean sell(JSONArray balanceArray, BigDecimal okprice) throws HttpException, IOException {
        BigDecimal usdtAmount;
        BigDecimal btcAmount;
        DecimalFormat df = new DecimalFormat("0.00");
        BigDecimal buyPrice;
        BigDecimal sellPrice;


        //遍历JSONArray
        for (Object object : balanceArray) {
            JSONObject jsonObjectone = (JSONObject) object;
            String currency = jsonObjectone.getString("coin_symbol");

            if ("BTC".equals(currency)) {
                logger.info("sell check in");
                btcAmount = jsonObjectone.getBigDecimal("balance");
                String depthInfo = restApi.getDepthInfo(CPconstants.BTC_USDT_PAIR, "5");
                JSONObject jsonDepth = JSONObject.parseObject(depthInfo);

                JSONObject jsonData = jsonDepth.getJSONObject("result");
                JSONArray jsonAsks = jsonData.getJSONArray("asks");
                JSONArray jsonBids = jsonData.getJSONArray("bids");
                if (btcAmount.compareTo(SellKeepLimit) > 0) {
                    // 查询卖二
                    sellPrice = ((JSONObject) jsonAsks.get(1)).getBigDecimal("price").setScale(2, BigDecimal.ROUND_FLOOR).subtract(huadian);
                    // OK网价格与CP网价格比较
                    if (okprice.subtract(sellPrice).compareTo(OKpriceStopSellLimit) > 0) {
                        logger.info("FT价格低于OK价格超过限制" + OKpriceStopSellLimit);
                        return false;
                    }
                    btcAmount = btcAmount.setScale(3, BigDecimal.ROUND_FLOOR);
                    logger.info("sellPrice:" + sellPrice.toString() + ", amount:" + btcAmount.toString());

                    // order_side,     //交易方向，1-买，2-卖
                    restApi.trade("BTC_USDT", 0, 2, 2, sellPrice, btcAmount);
                    return true;
                }
            }
        }
        return false;
    }

    public static void sell_cp(JSONArray balanceArray) throws HttpException, IOException {
        BigDecimal cpAmount;
        BigDecimal sellPrice;

        String msg = restApi.getDepthInfo(CPconstants.CP_USDT_PAIR, "5");
        JSONObject jsonObj = JSONObject.parseObject(msg);
        JSONObject jsonData = jsonObj.getJSONObject("data");
        JSONArray jsonAsks = jsonData.getJSONArray("asks");

        sellPrice = ((BigDecimal) jsonAsks.get(2)).subtract(new BigDecimal(0.004));

        if (sellPrice.compareTo(LOW_SELLPRICE) < 0) {
            logger.info("价格太低不卖！当前价格:" + sellPrice + "，当前价格底线：" + LOW_SELLPRICE);
            return;
        }
        //遍历JSONArray
        for (Object object : balanceArray) {
            JSONObject jsonObjectone = (JSONObject) object;
            String currency = jsonObjectone.getString("currency");

            if ("CP".equals(currency)) {
                cpAmount = jsonObjectone.getBigDecimal("available");
                if (cpAmount.compareTo(new BigDecimal(1)) > 0) {
                    // 查询卖二
                    sellPrice = sellPrice.setScale(3, BigDecimal.ROUND_FLOOR);

                    cpAmount = cpAmount.setScale(2, BigDecimal.ROUND_FLOOR);

                    logger.info("！！！卖出CP价格：" + sellPrice + "数量:" + cpAmount);

                    // order_side,     //交易方向，1-买，2-卖
                    restApi.trade("CP_USDT", 0, 2, 2, sellPrice, cpAmount);
                    //  break;
                } else {
                    logger.info("数量不足，不卖CP");
                }
            }
        }
    }


    static class timerTask_quick implements Runnable {
        public void run() {
            // 行情信息查询
            try {
                logger.info("---------START快速买卖版本-------->");
                JSONArray orderArray = getOrderList(CPconstants.BTC_USDT_PAIR,"BTC");
                if (orderArray.size() > 0) {
                    cancelOrder(orderArray);
                }
                //获取OK网站金额信息
                BigDecimal okprice = getOkBTCPrice();
                pricelist.offer(okprice);

                //获取余额信息
                JSONArray balanceArray = getBalance();

                //卖出BTC
                sell(balanceArray, okprice);

                Thread.sleep(3000);
                buy(balanceArray, okprice);

                logger.info("---------END----------->");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class timerTask_auto implements Runnable {
        public void run() {
            // 行情信息查询
            try {
                logger.info("---------START无人值守版本-------->");
                JSONArray orderArray = getOrderList(CPconstants.BTC_USDT_PAIR,"BTC");
                if (orderArray.size() > 0) {
                    cancelOrder(orderArray);
                }
                //获取OK网站金额信息
                BigDecimal okprice = getOkBTCPrice();
                pricelist.offer(okprice);

                //获取余额信息
                JSONArray balanceArray = getBalance();

                //卖出BTC
                sell(balanceArray, okprice);

                Thread.sleep(3000);

                //购买BTC
                if (pricelist.jude(okprice)) {
                    //满足相应规则，才允许购买
                    buy(balanceArray, okprice);
                }

                logger.info("---------END----------->");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class autoSell implements Runnable {
        public void run() {
            // 行情信息查询
            try {
                logger.info("---------START SELLING ETH------>");
                // 查询挂单
                JSONArray orderArray = getOrderList(CPconstants.ETH_USDT_PAIR,"CP");
                if (orderArray.size() > 0) {
                    cancelOrder(orderArray);
                }
                JSONArray balanceArray = getBalance();
                sell_cp(balanceArray);
                logger.info("---------END----------->");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}







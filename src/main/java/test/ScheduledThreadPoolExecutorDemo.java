package test;

import base.model.SpotInfo;
import com.alibaba.fastjson.JSONObject;
import okcoin.rest.future.IFutureRestApi;
import okcoin.rest.future.impl.FutureRestApiV1;
import okcoin.rest.stock.IStockRestApi;
import okcoin.rest.stock.impl.StockRestApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by woody on 2018/5/1.
 */
@Service("scheduledThreadPoolExecutorDemo")
public class ScheduledThreadPoolExecutorDemo {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledThreadPoolExecutorDemo.class);

    String api_key = "20b788e8-ef8b-4f24-8488-ee1245365363";  //OKCoin申请的apiKey
    String secret_key = "B45C6080AAB9037EE0CBAACCB3AFC5FD";  //OKCoin申请的secretKey
    String url_prex = "https://www.okex.cn";  //注意：请求URL https://www.okex.cn


    IStockRestApi stockGet = new StockRestApi(url_prex);
    IFutureRestApi futureGetV1 = new FutureRestApiV1(url_prex);


    public void crawlInfo(){
        ScheduledThreadPoolExecutor scheduledExecutor = new ScheduledThreadPoolExecutor(2);
        /**
         * new timeTaskForException() 要执行的任务线程
         * 1000：延迟多长时间执行
         * 2000: 每隔多少长时间执行一次
         * TimeUnit.MILLISECONDS：时间单位
         */
        scheduledExecutor.scheduleAtFixedRate(new timeTask4Spot(), 0, 200, TimeUnit.MILLISECONDS);
//        scheduledExecutor.scheduleAtFixedRate(new timeTask4Future(), 0, 200, TimeUnit.MILLISECONDS);

    }

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml",
                "classpath:spring/applicationContext-mybatis.xml");
//        ScheduledThreadPoolExecutorDemo demo = (ScheduledThreadPoolExecutorDemo)ctx.getBean("scheduledThreadPoolExecutorDemo");

        ScheduledThreadPoolExecutorDemo demo = new ScheduledThreadPoolExecutorDemo();
        demo.crawlInfo();
    }

    class timeTask4Spot implements Runnable{

        public void run()  {
            // 行情信息查询
            try {
                String tickerResult = stockGet.ticker("btc_usdt");
                logger.info(tickerResult);
                JSONObject tickerJSV1 = JSONObject.parseObject(tickerResult);
                BigDecimal buy = tickerJSV1.getBigDecimal("buy");
                BigDecimal sell = tickerJSV1.getBigDecimal("sell");
                BigDecimal high = tickerJSV1.getBigDecimal("high");
                BigDecimal low = tickerJSV1.getBigDecimal("low");
                BigDecimal last = tickerJSV1.getBigDecimal("last");
                long d = tickerJSV1.getLongValue("date")*1000;
                Date date = new Date(d);


                SpotInfo spotInfo = new SpotInfo();
                spotInfo.setSymbol("btc_usdt");
                spotInfo.setBuy(buy);
                spotInfo.setSell(sell);
                spotInfo.setHigh(high);
                spotInfo.setLow(low);
                spotInfo.setLast(last);
                spotInfo.setServerTime(date);
                spotInfo.setCreateDate(new Date());



//                stockClient.insertOrder(orderInfo);




            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    class timeTask4Future implements Runnable {

        public void run()  {
            // 行情信息查询
            try {
                String futureResult = futureGetV1.future_ticker("btc_usdt", "this_week");
                logger.info(futureResult);
                JSONObject futureJSV1 = JSONObject.parseObject(futureResult);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
package test;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Created by woody on 2018/4/30.
 */
public class Test {
    private static final Logger logger = LoggerFactory.getLogger(Test.class);
    public static BigDecimal getOkBTCPrice() throws HttpException, IOException {
        String url_prex = "https://www.okb.com";  //注意：请求URL https://www.okex.cn

        /**
         * get请求无需发送身份认证,通常用于获取行情，市场深度等公共信息
         *
         */
        okcoin.rest.stock.IStockRestApi stockGet = new okcoin.rest.stock.impl.StockRestApi(url_prex);

        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml",
                "classpath:spring/applicationContext-mybatis.xml");
        okcoin.rest.StockClient stockClient = (okcoin.rest.StockClient)ctx.getBean("stockClient");

        // 行情信息查询
        String tickerResult = stockGet.ticker("btc_usdt");
        logger.info(tickerResult);
        JSONObject jsonOrder = JSONObject.parseObject(tickerResult);

        String tradeOrderV1 = JSONObject.parseObject(jsonOrder.getString("ticker")).getString("buy");
        logger.info("okPrice->"+tradeOrderV1);
        return new BigDecimal(tradeOrderV1);
    }

    public static void main(String[] args) throws Exception {

        getOkBTCPrice();

//Mon Apr 30 22:06:00 CST 2018
//
//        Date d = new Date(1529310233553l);
//        System.out.println(d.toString());
//
//
//        Date e = new Date(System.currentTimeMillis());
//        System.out.println(e.toString());

//        System.out.println(System.currentTimeMillis());

//        System.out.println(System.currentTimeMillis()-1528598877700l);




    }
}

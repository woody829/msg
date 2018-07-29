package okcoin.rest;

import base.dao.OrderInfoMapper;
import base.model.OrderInfo;
import com.alibaba.fastjson.JSONObject;
import okcoin.rest.stock.IStockRestApi;
import okcoin.rest.stock.impl.StockRestApi;
import org.apache.http.HttpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * 现货 REST API 客户端请求
 *
 */
@Service("stockClient")
public class StockClient {
    private static final Logger logger = LoggerFactory.getLogger(StockClient.class);
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    public void insertOrder(OrderInfo orderDTO){
        orderInfoMapper.insertOrder(orderDTO);
    }

    public static void main(String[] args) throws HttpException, IOException {


//        URL prop = Thread.currentThread().getContextClassLoader().getResource("properties/log4j.properties");
//        PropertyConfigurator.configure(prop);

        String api_key = "***";  //OKCoin申请的apiKey
        String secret_key = "***";  //OKCoin申请的secretKey
        String url_prex = "https://www.okex.cn";  //注意：请求URL https://www.okex.cn

        /**
         * get请求无需发送身份认证,通常用于获取行情，市场深度等公共信息
         *
         */
        IStockRestApi stockGet = new StockRestApi(url_prex);

        /**
         * post请求需发送身份认证，获取用户个人相关信息时，需要指定api_key,与secret_key并与参数进行签名，
         * 此处对构造方法传入api_key与secret_key,在请求用户相关方法时则无需再传入，
         * 发送post请求之前，程序会做自动加密，生成签名。
         *
         */
        IStockRestApi stockPost = new StockRestApi(url_prex, api_key, secret_key);

 /*
        //现货行情
        stockGet.ticker("btc_usd");

        //现货市场深度
        stockGet.depth("btc_usd");

        //现货OKCoin历史交易信息
        stockGet.trades("btc_usd", "20");
*/


        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml",
                "classpath:spring/applicationContext-mybatis.xml");
        StockClient stockClient = (StockClient)ctx.getBean("stockClient");

        OrderInfo orderInfo = new OrderInfo();
        String amount = "0.1";
        BigDecimal bdAmount = new BigDecimal(amount);
        orderInfo.setAmount(bdAmount);
        String symbol = "btc_usdt";
        orderInfo.setSymbol(symbol);
        String bussType = "buy";
        orderInfo.setBussType(bussType);
        String price = "90";
        BigDecimal bdPrice = new BigDecimal(price);
        orderInfo.setPrice(bdPrice);

        stockClient.insertOrder(orderInfo);

        //现货下单交易
        String tradeResult = stockPost.trade(symbol, bussType, price, amount);
        logger.info(tradeResult);
        JSONObject tradeJSV1 = JSONObject.parseObject(tradeResult);
        String tradeOrderV1 = tradeJSV1.getString("order_id");
        logger.info(tradeOrderV1);
        String error_code = "";

        /*
        {"result":true,"order_id":457235542}
        {"error_code":1007}
         */
        if(StringUtil.isEmpty(tradeOrderV1)){
            // 插入异常日志表
            logger.info("error log");
            error_code = tradeJSV1.getString("error_code");
        }else if(!StringUtil.isEmpty(tradeOrderV1)&&("true".equals(tradeJSV1.getString("result")))){
            // 插入ORDER_INFO表
            orderInfo.setOrderId(tradeOrderV1);
            stockClient.insertOrder(orderInfo);
        }


        // 现货获取用户订单信息
        // order_id|订单ID -1:所有未完成订单，否则查询相应订单号的订单
        String orderResult = stockPost.order_info("btc_usdt", tradeOrderV1);
        logger.info(orderResult);
        JSONObject orderJSV1 = JSONObject.parseObject(orderResult);
        String orders = orderJSV1.getString("orders");
        logger.info(orders);


        //现货撤销订单
        String cancelResult = stockPost.cancel_order("btc_usdt", tradeOrderV1);
        logger.info(cancelResult);
        JSONObject cancelJSV1 = JSONObject.parseObject(cancelResult);
        String cancel = tradeJSV1.getString("result");
        logger.info(cancel);


        // 行情信息查询
        String tickerResult = stockGet.ticker("btc_usdt");
        logger.info(tickerResult);
        JSONObject tickerJSV1 = JSONObject.parseObject(tickerResult);







        /*
        //现货批量下单
        stockPost.batch_trade("btc_usd", "buy", "[{price:50, amount:0.02},{price:50, amount:0.03}]");

        //批量获取用户订单
        stockPost.orders_info("0", "btc_usd", "125420341, 125420342");

        //获取用户历史订单信息，只返回最近七天的信息
        stockPost.order_history("btc_usd", "0", "1", "20");
        */

    }
}

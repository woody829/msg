package cb.rest.stock.impl;

import cb.rest.HttpUtilManager;
import cb.rest.stock.IStockRestApi;
import cb.util.MD5Util;
import org.apache.http.HttpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class StockRestApi implements IStockRestApi {
    private static final Logger logger = LoggerFactory.getLogger(StockRestApi.class);

	private String secret_key;
	
	private String api_key;
	
	private String url_prex;
	
	public StockRestApi(String url_prex,String api_key,String secret_key){
		this.api_key = api_key;
		this.secret_key = secret_key;
		this.url_prex = url_prex;
	}
	

	private final String GET_DEPTH_URL = "/api/publics/v1/depth";


    private final String POST_ORDERLIST_URL = "/api/publics/v1/orders_info";
    private final String POST_BALANCE_URL = "/api/publics/v1/userinfo";
    private final String POST_CANCELORDER_URL = "/api/publics/v1/cancel_order";
    private final String POST_TRADE_URL = "/api/publics/v1/trade";

	@Override
    // 访问频率 1次/1秒
	public String getDepthInfo(String symbol,String size) throws HttpException, IOException {
        String param = "size=5&symbol="+symbol;
		HttpUtilManager httpUtil = HttpUtilManager.getInstance();
		String fullurl = url_prex + GET_DEPTH_URL;
		String result = httpUtil.requestHttpGet(fullurl, param);
	    return result;
	}

	@Override
	public String getOrderList(String symbol,String size,String type) throws HttpException, IOException{
        //创建参数列表
        String sign = "";
        Map<String, String> params = new HashMap<>();
        params.put("apikey", api_key);
        params.put("size", size);
        params.put("type", type);
        params.put("symbol", symbol);
        params.put("time", String.valueOf(System.currentTimeMillis()));
        sign = MD5Util.buildMysignV1(params,secret_key);
        params.put("sign", sign);
//        logger.info("param:{}",params);

        // 发送post请求
        HttpUtilManager httpUtil = HttpUtilManager.getInstance();
        String result = httpUtil.requestHttpPost(url_prex,POST_ORDERLIST_URL,params);
//        logger.info("result---订单列表:{}",result);
        return result;
	}


    @Override
	public String queryBalance(String symbol) throws HttpException, IOException {
        //创建参数列表
        String sign = "";
        Map<String, String> params = new HashMap<>();
        params.put("apikey", api_key);
        params.put("time", String.valueOf(System.currentTimeMillis()));
//        params.put("shortName", symbol);
        sign = MD5Util.buildMysignV1(params,secret_key);
        params.put("sign", sign);
//        logger.info("param:{}",params);

        // 发送post请求
        HttpUtilManager httpUtil = HttpUtilManager.getInstance();
        String result = httpUtil.requestHttpPost(url_prex,POST_BALANCE_URL,params);
		logger.info("result---账户余额:{}",result);
        return result;
	}


	@Override
    public String trade(String symbol,String type,  String price, String amount) throws HttpException, IOException {
        //创建参数列表
        String sign = "";
        Map<String, String> params = new HashMap<>();
        params.put("apikey", api_key);
        params.put("symbol", symbol);
        params.put("type", type);
        params.put("price", price);
        params.put("amount", amount);
        params.put("time", String.valueOf(System.currentTimeMillis()));
        sign = MD5Util.buildMysignV1(params,secret_key);
        params.put("sign", sign);
        logger.info("param:{}",params);

        // 发送post请求
        HttpUtilManager httpUtil = HttpUtilManager.getInstance();
        String result = httpUtil.requestHttpPost(url_prex,POST_TRADE_URL,params);
        if("buy".equals(type)){
            logger.info("result---下单买:{}",result);
        } else {
            logger.info("result---下单卖:{}",result);
        }

        return result;
	}

	@Override
	public String cancelOrder(String order_id) throws HttpException, IOException {
        //创建参数列表
        String sign = "";
        Map<String, String> params = new HashMap<>();
        params.put("apikey", api_key);
        params.put("order_id", order_id);
        params.put("time", String.valueOf(System.currentTimeMillis()));
        sign = MD5Util.buildMysignV1(params,secret_key);
        params.put("sign", sign);
        logger.info("param:{}",params);

        // 发送post请求
        HttpUtilManager httpUtil = HttpUtilManager.getInstance();
        String result = httpUtil.requestHttpPost(url_prex,POST_CANCELORDER_URL,params);
        logger.info("result---订单撤销:{}",result);
        return result;
	}





	public String getSecret_key() {
		return secret_key;
	}

	public void setSecret_key(String secret_key) {
		this.secret_key = secret_key;
	}

	

	public String getApi_key() {
		return api_key;
	}

	public void setApi_key(String api_key) {
		this.api_key = api_key;
	}

	public String getUrl_prex() {
		return url_prex;
	}

	public void setUrl_prex(String url_prex) {
		this.url_prex = url_prex;
	}

}

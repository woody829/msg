package cc.rest.stock.impl;

import cc.rest.HttpUtilManager;
import cc.rest.StringUtil;
import cc.rest.stock.IStockRestApi;
import cc.util.HmacUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.Consts;
import org.apache.http.HttpException;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	

	private final String GET_DEPTH_URL = "mdata";
    private final String POST_TRANSFER_URL = "transfer";
    private final String POST_ORDERPENDING_URL = "orderpending";


	private final String CMD_BALANCE = "transfer/assets";
	private final String CMD_ORDER_LIST = "orderpending/orderPendingList";
	private final String CMD_CANCEL_ORDER = "orderpending/cancelTrade";
	private final String CMD_TRADE = "orderpending/trade";


	@Override
	public String getDepthInfo(String symbol,String level) throws HttpException, IOException {
        String param = "";
        //创建参数列表
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("cmd", "depth"));
        if(!StringUtil.isEmpty(symbol)){
            params.add(new BasicNameValuePair("pair", symbol));
        }
        if(!StringUtil.isEmpty(level)){
            params.add(new BasicNameValuePair("size", level));
        }
        param = EntityUtils.toString(new UrlEncodedFormEntity(params, Consts.UTF_8));
//        System.out.println(param);
		HttpUtilManager httpUtil = HttpUtilManager.getInstance();
		String fullurl = url_prex + GET_DEPTH_URL;
		String result = httpUtil.requestHttpGet(fullurl, param);
	    return result;
	}

	@Override
	public String getOrderList(String pair,int account_type,int page,int size,String coin_symbol,
                               String currency_symbol,int order_side) throws HttpException, IOException{
        // pair account_type page size coin_symbol currency_symbol order_side
        // "BTC_USDT", 0,     1,   10,    "BTC",      "USDT",          1);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("cmd", CMD_ORDER_LIST);
        JSONObject innerObj = new JSONObject();
        innerObj.put("pair", pair);
        innerObj.put("account_type", account_type);
        innerObj.put("page", page);
        innerObj.put("size", size);
        innerObj.put("coin_symbol", coin_symbol);
        innerObj.put("currency_symbol", currency_symbol);
//        innerObj.put("order_side", order_side);
        jsonObject.put("body", innerObj);
//        logger.info("jsonObject--挂单列表:{}",jsonObject);

        //创建参数列表
        String sign = "";
        Map<String, String> params = new HashMap<>();
        params.put("cmds", "["+jsonObject.toJSONString()+"]");
        params.put("apikey", api_key);
        try{
            sign = HmacUtil.encode("["+jsonObject.toJSONString()+"]",secret_key);
        }catch (Exception e){
            e.printStackTrace();
        }
        params.put("sign", sign);

        // 发送post请求
        HttpUtilManager httpUtil = HttpUtilManager.getInstance();
        String result = httpUtil.requestHttpPost(url_prex,POST_ORDERPENDING_URL,params);
        logger.info("result--挂单列表:{}",result);
        return result;
	}


    @Override
	public String queryBalance() throws HttpException, IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("cmd", CMD_BALANCE);

        JSONObject innerObj = new JSONObject();
        innerObj.put("select", 1);
        jsonObject.put("body", innerObj);

        //创建参数列表
        String sign = "";
        Map<String, String> params = new HashMap<>();
        params.put("cmds", "["+jsonObject.toJSONString()+"]");
        params.put("apikey", api_key);
        try{
            sign = HmacUtil.encode("["+jsonObject.toJSONString()+"]",secret_key);
        }catch (Exception e){
            e.printStackTrace();
        }
        params.put("sign", sign);

        // 发送post请求
        HttpUtilManager httpUtil = HttpUtilManager.getInstance();
        String result = httpUtil.requestHttpPost(url_prex,POST_TRANSFER_URL,params);
		logger.info("result---账户余额:{}",result);
        return result;
	}


	@Override
	public String trade(String pair, int account_type,
                        int order_type, int order_side, BigDecimal price, BigDecimal amount) throws HttpException, IOException {
        // pair   account_type order_type order_side price amount
        // "BTC_USDT", 0,          2,        1,       10,    1);
        // order_side,     //交易方向，1-买，2-卖
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("cmd", CMD_TRADE);
        JSONObject innerObj = new JSONObject();
        innerObj.put("pair", pair);
        innerObj.put("account_type", account_type);
        innerObj.put("order_type", order_type);
        innerObj.put("order_side", order_side);
        innerObj.put("price", price);
        innerObj.put("amount", amount);
        jsonObject.put("body", innerObj);

        if(order_side==1){
            logger.info("jsonObject--买交易请求报文:{}",jsonObject);
        } else {
            logger.info("jsonObject--卖交易请求报文:{}",jsonObject);
        }


        //创建参数列表
        String sign = "";
        Map<String, String> params = new HashMap<>();
        params.put("cmds", "["+jsonObject.toJSONString()+"]");
        params.put("apikey", api_key);
        try{
            sign = HmacUtil.encode("["+jsonObject.toJSONString()+"]",secret_key);
        }catch (Exception e){
            e.printStackTrace();
        }
        params.put("sign", sign);

        // 发送post请求
        HttpUtilManager httpUtil = HttpUtilManager.getInstance();
        String result = httpUtil.requestHttpPost(url_prex,POST_ORDERPENDING_URL,params);
        logger.info("result:{}",result);
        return result;
	}

	@Override
	public String cancelOrder(String orders_id) throws HttpException, IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("cmd", CMD_CANCEL_ORDER);
        JSONObject innerObj = new JSONObject();
        innerObj.put("orders_id", orders_id);
        jsonObject.put("body", innerObj);
//        logger.info("jsonObject:{}",jsonObject);

        //创建参数列表
        String sign = "";
        Map<String, String> params = new HashMap<>();
        params.put("cmds", "["+jsonObject.toJSONString()+"]");
        params.put("apikey", api_key);
        try{
            sign = HmacUtil.encode("["+jsonObject.toJSONString()+"]",secret_key);
        }catch (Exception e){
            e.printStackTrace();
        }
        params.put("sign", sign);

        // 发送post请求
        HttpUtilManager httpUtil = HttpUtilManager.getInstance();
        String result = httpUtil.requestHttpPost(url_prex,POST_ORDERPENDING_URL,params);
        logger.info("result--撤单:{}",result);
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

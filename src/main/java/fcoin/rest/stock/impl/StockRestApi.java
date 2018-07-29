package fcoin.rest.stock.impl;

import fcoin.rest.HttpUtilManager;
import fcoin.rest.StringUtil;
import fcoin.rest.stock.IStockRestApi;
import fcoin.util.Base64Util;
import fcoin.util.MD5Util;
import fcoin.util.RSAUtils;
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
	
	public StockRestApi(String url_prex){
		this.url_prex = url_prex;
	}



    /**
     * fcoin URL
     */
    private final String TEST_URL = "/public/server-time";

	private final String GET_ORDER_LIST = "/orders";
	private final String GET_DEPTH_URL = "/market/depth";



    /**
     * fcoin URL
     */
    private final String ACCOUNT_BALANCE = "/accounts/balance";

    /**
     * fcoin URL
     */
    private final String TEST_ORDER_URL = "/orders";

	/**
     * fcoin URL
     */
    private final String CANCEL_ORDER_URL = "/submit-cancel";

	
	/**
	 * 现货行情URL
	 */
	private final String TICKER_URL = "/api/v1/ticker.do?";
	
	/**
	 * 现货市场深度URL
	 */
	private final String DEPTH_URL = "/api/v1/depth.do?";
	
	/**
	 * 现货历史交易信息URL
	 */
	private final String TRADES_URL = "/api/v1/trades.do?";
	
	/**
	 * 现货获取用户信息URL
	 */
	private final String USERINFO_URL = "/api/v1/userinfo.do?";
	
	/**
	 * 现货 下单交易URL
	 */
	private final String TRADE_URL = "/api/v1/trade.do?";
	
	/**
	 * 现货 批量下单URL
	 */
	private final String BATCH_TRADE_URL = "/api/v1/batch_trade.do";
	

	/**
	 * 现货 获取用户订单URL
	 */
	private final String ORDER_INFO_URL = "/api/v1/order_info.do";
	
	/**
	 * 现货 批量获取用户订单URL
	 */
	private final String ORDERS_INFO_URL = "/api/v1/orders_info.do";
	
	/**
	 * 现货 获取历史订单信息，只返回最近七天的信息URL
	 */
	private final String ORDER_HISTORY_URL = "/api/v1/order_history.do";

	@Override
	public String ticker(String symbol) throws HttpException, IOException {
		HttpUtilManager httpUtil = HttpUtilManager.getInstance();
		String param = "";
		if(!StringUtil.isEmpty(symbol )) {
			if (!param.equals("")) {
				param += "&";
			}
			param += "symbol=" + symbol;
		}
		String result = httpUtil.requestHttpGet(url_prex, TEST_URL, param);
	    return result;
	}

    @Override
	public String tickerFcoin(String symbol) throws HttpException, IOException {
		HttpUtilManager httpUtil = HttpUtilManager.getInstance();
		String param = "";
		if(!StringUtil.isEmpty(symbol )) {
			if (!param.equals("")) {
				param += "&";
			}
			param += "symbol=" + symbol;
		}
		String result = httpUtil.requestHttpGet(url_prex, TEST_URL, param);
	    return result;
	}

	@Override
	public String getDepthInfo(String symbol,String level) throws HttpException, IOException {
		HttpUtilManager httpUtil = HttpUtilManager.getInstance();
		String param = "";

		String fullurl = url_prex + GET_DEPTH_URL + "/" + level + "/" + symbol;
//		if(!StringUtil.isEmpty(symbol )) {
//			if (!param.equals("")) {
//				param += "&";
//			}
//			param += "symbol=" + symbol;
//		}
//		if(!StringUtil.isEmpty(since )) {
//			if (!param.equals("")) {
//				param += "&";
//			}
//			param += "since=" + since;
//		}



		String result = httpUtil.requestHttpGet(fullurl, "", param);
	    return result;
	}

	@Override
	public String getOrderList(String symbol,String states,String before,String after,String limit) throws HttpException, IOException{
		String param = "";
		// 构造参数签名
		Map<String, String> params = new HashMap<String, String>();
		if(!StringUtil.isEmpty(symbol)){
			params.put("symbol", symbol);
		}
		if(!StringUtil.isEmpty(states)){
			params.put("states", states);
		}
		if(!StringUtil.isEmpty(before)){
			params.put("before", before);
		}
		if(!StringUtil.isEmpty(after)){
			params.put("after", after);
		}
		if(!StringUtil.isEmpty(limit)) {
			if (!param.equals("")) {
				param += "&";
			}
			param += "limit=" + limit;
		}

		// 参数排序
		String value = MD5Util.buildMysignV1Fcoin(params);

		// 拼接URL
		String time = Long.toString(System.currentTimeMillis());
		String fullUrl = url_prex + GET_ORDER_LIST + "?" + value;
		logger.info("拼接信息：{}",fullUrl);


		String signStr = "GET"+fullUrl+time;
//		logger.info("signStr:{}",signStr);
		// Base64转码
		String base64Str = Base64Util.base64Encoder(RSAUtils.getUTF8Byte(signStr));
//		logger.info("base64Str:{}",base64Str);

		// 签名 HMAC-SHA1
		String sign = Base64Util.base64Encoder(RSAUtils.HmacSha1Sign(RSAUtils.getUTF8Byte(base64Str),RSAUtils.getUTF8Byte(secret_key)));

		params.put("time",time);
		params.put("signature",sign);
		params.put("key",api_key);

		// 发送post请求
		HttpUtilManager httpUtil = HttpUtilManager.getInstance();
		String result = httpUtil.requestHttpGet(fullUrl,"",params);
		return result;
	}

	@Override
	public String depth(String symbol) throws HttpException, IOException {
		HttpUtilManager httpUtil = HttpUtilManager.getInstance();
		String param = "";
		if(!StringUtil.isEmpty(symbol )) {
			if(!param.equals("")) {
				param += "&";
			}
			param += "symbol=" + symbol;
		}
		String result = httpUtil.requestHttpGet(url_prex, this.DEPTH_URL, param);
	    return result;
	}


    @Override
	public String queryBalance() throws HttpException, IOException {
        // 构造参数签名
        Map<String, String> params = new HashMap<String, String>();

        // 拼接URL
        String time = Long.toString(System.currentTimeMillis());
        String fullUrl = url_prex + ACCOUNT_BALANCE;
        logger.info("拼接信息：{}",fullUrl);


        String signStr = "GET"+fullUrl+time;
        logger.info("signStr:{}",signStr);
        // Base64转码
        String base64Str = Base64Util.base64Encoder(RSAUtils.getUTF8Byte(signStr));
//        logger.info("base64Str:{}",base64Str);

        // 签名 HMAC-SHA1
        String sign = Base64Util.base64Encoder(RSAUtils.HmacSha1Sign(RSAUtils.getUTF8Byte(base64Str),RSAUtils.getUTF8Byte(secret_key)));

        params.put("time",time);
        params.put("signature",sign);
        params.put("key",api_key);

        // 发送post请求
        HttpUtilManager httpUtil = HttpUtilManager.getInstance();
        String result = httpUtil.requestHttpGet(url_prex,this.ACCOUNT_BALANCE,
                params);
        return result;
	}

	@Override
	public String userinfo() throws HttpException, IOException {
		// 构造参数签名
		Map<String, String> params = new HashMap<String, String>();
		params.put("api_key", api_key);
		String sign = MD5Util.buildMysignV1(params, this.secret_key);
		params.put("sign", sign);
		
		// 发送post请求
		HttpUtilManager httpUtil = HttpUtilManager.getInstance();
		String result = httpUtil.requestHttpPost(url_prex,this.USERINFO_URL,
				params);

		return result;
	}


	@Override
	public String trade(String symbol, String type,
                             String price, String amount,String side) throws HttpException, IOException {
        /* 1.参数排序
         * 2.参数base64编码
         * 3.
         * */
        // 构造参数签名
        Map<String, String> params = new HashMap<String, String>();
        if(!StringUtil.isEmpty(symbol)){
            params.put("symbol", symbol);
        }
        if(!StringUtil.isEmpty(type)){
            params.put("type", type);
        }
        if(!StringUtil.isEmpty(price)){
            params.put("price", price);
        }
        if(!StringUtil.isEmpty(amount)){
            params.put("amount", amount);
        }
		if(!StringUtil.isEmpty(side)){
            params.put("side", side);
        }

        // 参数排序
        String value = MD5Util.buildMysignV1Fcoin(params);

        // 拼接URL
        String time = Long.toString(System.currentTimeMillis());
        String fullUrl = url_prex + TEST_ORDER_URL;
        String signStr = "POST"+fullUrl+time+value;

        logger.info("拼接信息：{}",fullUrl);
//        logger.info("signStr:{}",signStr);


		String sss = signStr;
		String base64 = Base64Util.base64Encoder(RSAUtils.getUTF8Byte(sss));
//		System.out.println(base64);

		String privateKey = this.secret_key;
		byte[] kk = RSAUtils.HmacSha1Sign(RSAUtils.getUTF8Byte(base64),RSAUtils.getUTF8Byte(privateKey));

        params.put("time",time);
        params.put("signature",Base64Util.base64Encoder(kk));
        params.put("key",this.api_key);

        // 发送post请求
        HttpUtilManager httpUtil = HttpUtilManager.getInstance();
        String result = httpUtil.requestHttpPost(url_prex,this.TEST_ORDER_URL,
                params);
		logger.info("result:{}",result);
        return result;
	}

	@Override
	public String cancelOrder(String order_id) throws HttpException, IOException {
        // 拼接URL
        String time = Long.toString(System.currentTimeMillis());
        String fullUrl = url_prex + "/orders/" + order_id + CANCEL_ORDER_URL;
        String signStr = "POST"+fullUrl+time;

        logger.info("拼接信息：{}",fullUrl);
//        logger.info("signStr:{}",signStr);


		String sss = signStr;
		String base64 = Base64Util.base64Encoder(RSAUtils.getUTF8Byte(sss));
//		System.out.println(base64);

		String privateKey = this.secret_key;
		byte[] kk = RSAUtils.HmacSha1Sign(RSAUtils.getUTF8Byte(base64),RSAUtils.getUTF8Byte(privateKey));


		Map<String, String> params = new HashMap<String, String>();
        params.put("time",time);
        params.put("signature",Base64Util.base64Encoder(kk));
        params.put("key",this.api_key);

        // 发送post请求
        HttpUtilManager httpUtil = HttpUtilManager.getInstance();
        String result = httpUtil.requestHttpPost(fullUrl,"",
                params);
		logger.info("cancel result:{}",result);
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

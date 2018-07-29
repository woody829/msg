package fcoin.rest;

import com.alibaba.druid.support.json.JSONUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * 封装HTTP get post请求，简化发送http请求
 * @author zhangchi
 *
 */
public class HttpUtilManager {
	private static final Logger logger = LoggerFactory.getLogger(HttpUtilManager.class);
    private static final String KEYSTORE_PWD = "123456";
    private static final String KEY_PATH = "key/truststore.jks";
	private static HttpUtilManager instance = new HttpUtilManager();
	private static CloseableHttpClient client;
	private static long startTime = System.currentTimeMillis();
	public  static PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();  
	private static ConnectionKeepAliveStrategy keepAliveStrat = new DefaultConnectionKeepAliveStrategy() {  

	     public long getKeepAliveDuration(  
	            HttpResponse response,  
	            HttpContext context) {  
	        long keepAlive = super.getKeepAliveDuration(response, context);  
	        
	        if (keepAlive == -1) {  
	            keepAlive = 5000;  
	        }  
	        return keepAlive;  
	    }  
	  
	};
	private HttpUtilManager() {
//		client = HttpClients.custom().setConnectionManager(cm).setKeepAliveStrategy(keepAliveStrat).build();
        client = HttpClients.createDefault();
	}

    public static void IdleConnectionMonitor(){
		
		if(System.currentTimeMillis()-startTime>30000){
			 startTime = System.currentTimeMillis();
			 cm.closeExpiredConnections();  
             cm.closeIdleConnections(30, TimeUnit.SECONDS);
		}
	}
	 
	private static RequestConfig requestConfig = RequestConfig.custom()
	        .setSocketTimeout(20000)
	        .setConnectTimeout(20000)
	        .setConnectionRequestTimeout(20000)
	        .build();
	
	
	public static HttpUtilManager getInstance() {
		return instance;
	}

	public HttpClient getHttpClient() {
		return client;
	}

	private HttpPost httpPostMethod(String url) {
		return new HttpPost(url);
	}

	private  HttpRequestBase httpGetMethod(String url) {
		return new  HttpGet(url);
	}

    public String requestHttpGet(String url_prex,String url,Map<String,String> params) throws HttpException, IOException{

        IdleConnectionMonitor();
        String time = params.get("time");
        String key = params.get("key");
        String signature = params.get("signature");

        url=url_prex+url;

        HttpRequestBase method = this.httpGetMethod(url);
        method.setConfig(requestConfig);
        method.addHeader("FC-ACCESS-KEY",key);
        method.addHeader("FC-ACCESS-SIGNATURE",signature);
        method.addHeader("FC-ACCESS-TIMESTAMP", time);

        HttpResponse response = client.execute(method);
        HttpEntity entity =  response.getEntity();
        if(entity == null){
            return "";
        }
        InputStream is = null;
        String responseData = "";
        try{
            is = entity.getContent();
            responseData = IOUtils.toString(is, "UTF-8");
        }finally{
            if(is!=null){
                is.close();
            }
        }
		logger.info("responseData{}:",responseData);
        return responseData;
    }

	public String requestHttpGet(String fullUrl,Map<String,String> params) throws HttpException, IOException{
        IdleConnectionMonitor();
        String time = params.get("time");
        String key = params.get("key");
        String signature = params.get("signature");

        HttpRequestBase method = this.httpGetMethod(fullUrl);
        method.setConfig(requestConfig);
        method.addHeader("FC-ACCESS-KEY",key);
        method.addHeader("FC-ACCESS-SIGNATURE",signature);
        method.addHeader("FC-ACCESS-TIMESTAMP", time);

        HttpResponse response = client.execute(method);
        HttpEntity entity =  response.getEntity();
        if(entity == null){
            return "";
        }
        InputStream is = null;
        String responseData = "";
        try{
            is = entity.getContent();
            responseData = IOUtils.toString(is, "UTF-8");
        }finally{
            if(is!=null){
                is.close();
            }
        }
		logger.info("responseData{}:",responseData);
        return responseData;
    }

	public String requestHttpGet(String url_prex,String url,String param) throws HttpException, IOException{
		
		IdleConnectionMonitor();
		url=url_prex+url;
		if(param!=null && !param.equals("")){
		        if(url.endsWith("?")){
			    url = url+param;
			}else{
			    url = url+"?"+param;
			}
		}
		HttpRequestBase method = this.httpGetMethod(url);
		method.setConfig(requestConfig);

		HttpResponse response = client.execute(method);
		HttpEntity entity =  response.getEntity();
		if(entity == null){
			return "";
		}
		InputStream is = null;
		String responseData = "";
		try{
		    is = entity.getContent();
		    responseData = IOUtils.toString(is, "UTF-8");
		}finally{
			if(is!=null){
			    is.close();
			}
		}
		logger.info("responseData:{}",responseData);
		return responseData;
	}
	
	public String requestHttpPost(String url_prex,String url,Map<String,String> params) throws HttpException, IOException{
		IdleConnectionMonitor();
		String time = params.get("time");
		String key = params.get("key");
		String signature = params.get("signature");

		url=url_prex+url;
		HttpPost method = this.httpPostMethod(url);
		List<NameValuePair> valuePairs = this.convertMap2PostParams(params);
//		UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(valuePairs, Consts.UTF_8);
//		method.setEntity(urlEncodedFormEntity);

		params.remove("time");
		params.remove("key");
		params.remove("signature");
		StringEntity en = new StringEntity(JSONUtils.toJSONString(params));
		en.setContentType("application/json;charset=UTF-8");
		method.setConfig(requestConfig);
		method.addHeader("FC-ACCESS-KEY",key);
		method.addHeader("FC-ACCESS-SIGNATURE",signature);
		method.addHeader("FC-ACCESS-TIMESTAMP", time);
		method.addHeader("content-type", "application/json;charset=UTF-8");
		method.setEntity(en);

		HttpResponse response = client.execute(method);
		HttpEntity entity =  response.getEntity();
		if(entity == null){
			return "";
		}
		InputStream is = null;
		String responseData = "";
		try{
		    is = entity.getContent();
		    responseData = IOUtils.toString(is, "UTF-8");
		}finally{
			if(is!=null){
			    is.close();
			}
		}
		return responseData;
		
	}
	
	private List<NameValuePair> convertMap2PostParams(Map<String,String> params){
		List<String> keys = new ArrayList<String>(params.keySet());
		if(keys.isEmpty()){
			return null;
		}
		int keySize = keys.size();
		List<NameValuePair>  data = new LinkedList<NameValuePair>() ;
		for(int i=0;i<keySize;i++){
			String key = keys.get(i);
			String value = params.get(key);
			data.add(new BasicNameValuePair(key,value));
		}
		return data;
	}

}


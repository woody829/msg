package common.util;

import common.Constants;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * HttpClient帮助类
 * 
 * @author vanceinfo
 *
 */
public class HttpClientHelper {

    private static Logger logger = Logger.getLogger(HttpClientHelper.class);

    public static String post(String url, Map<String, String> params) throws Exception {
        String body = null;
        logger.debug("create HttpPost: " + url);
        logger.debug("create HttpPost: " + url);
        try {
            body = postForm(url, params);
        } catch (Exception e) {
            logger.error("error", e);
            e.printStackTrace();
            throw e;
        }

        return body;
    }

    public static String get(String url) throws Exception {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        //请求超时
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, Constants.HTTP_TIMEOUT);
        //读取超时
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, Constants.HTTP_TIMEOUT);
        String body = null;
        logger.debug("create HttpGet: " + url);
        try {
            HttpGet get = new HttpGet(url);
            body = invoke(httpClient, get);
        } catch (Exception e) {
            logger.error("error", e);
            throw e;
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return body;
    }

    /**
     * 
     * <p>
     * header中带参数请求
     * </p>
     * @param
     * @return
     * @throws
     * @see
     * @since %I%
     */
    public static String getInHeader(String url, Map<String, String> params) throws Exception {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        //请求超时
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, Constants.HTTP_TIMEOUT);
        //读取超时
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, Constants.HTTP_TIMEOUT);
        String body = null;

        logger.debug("create HttpGet: " + url);
        try {
            HttpGet get = new HttpGet(url);
            for (Entry<String, String> entry : params.entrySet()) {
                get.setHeader(entry.getKey(), entry.getValue());
            }
            body = invoke(httpClient, get);
        } catch (Exception e) {
            logger.error("error", e);
            throw e;
        } finally {
            httpClient.getConnectionManager().shutdown();
        }

        return body;
    }

    /**
     * 
     * <p>
     * 行为描述:带参数的get请求
     * </p>
     * @param
     * @return
     * @throws
     * @see
     * @since %I%
     */
    public static String get(String url, Map<String, String> params) throws Exception {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        //请求超时
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, Constants.HTTP_TIMEOUT);
        //读取超时
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, Constants.HTTP_TIMEOUT);
        String body = null;

        try {
            StringBuffer sb = new StringBuffer(url + "?");
            for (Entry<String, String> entry : params.entrySet()) {
                sb.append(entry.getKey() + "=" + entry.getValue() + "&");
            }
            url = sb.toString();
            logger.debug("create HttpGet: " + url.substring(0, url.length() - 1));
            HttpGet get = new HttpGet(url);
            body = invoke(httpClient, get);
        } catch (Exception e) {
            logger.error("error", e);
            throw e;
        } finally {
            httpClient.getConnectionManager().shutdown();
        }

        return body;
    }

    private static String invoke(DefaultHttpClient httpClient, HttpUriRequest httpPost) throws ClientProtocolException,
            IOException {

        HttpResponse response = sendRequest(httpClient, httpPost);
        String body = paseResponse(response);

        return body;
    }

    private static String paseResponse(HttpResponse response) throws ParseException, IOException {
        logger.debug("get response from http server..");
        HttpEntity entity = response.getEntity();

        logger.debug("response status: " + response.getStatusLine());
        String charset = EntityUtils.getContentCharSet(entity);
        logger.debug("response encoding: " + charset);

        String body = EntityUtils.toString(entity, HTTP.UTF_8);
        //logger.debug("response data: " + body);

        return body;
    }

    private static HttpResponse sendRequest(DefaultHttpClient httpClient, HttpUriRequest httpPost)
            throws ClientProtocolException, IOException {

        logger.debug("execute request...");

        return httpClient.execute(httpPost);

    }

    private static String postForm(String url, Map<String, String> params) throws Exception {

        HttpPost httpPost = new HttpPost(url);
        //请求超时
        httpPost.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, Constants.HTTP_TIMEOUT);
        //读取超时
        httpPost.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, Constants.HTTP_TIMEOUT);

        String result = "";
        logger.debug("request url: " + httpPost.getRequestLine());
        //logger.debug("request data: " + Json2JavaUtil.java2Json(params, null));

        if (null == params) {
            return result;
        }
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        Set<String> keySet = params.keySet();
        for (String key : keySet) {
            nvps.add(new BasicNameValuePair(key, params.get(key)));
        }
        for (int i = 1; i < 2; i++) {
            result = doPost(url, nvps);
            if (result != null) {
                //logger.debug("return json= " +res);
                logger.debug("try " + i + " and success" + "result-->" + result);
                break;
            } else {
                logger.debug("try " + i + " and failure");
            }
        }
        return result;
    }

    /**
     * Do post.
     *
     * @param list the list
     * @return the string
     */
    private static String doPost(String url, List<NameValuePair> list) {
        //生成一个http客户端对象
        HttpClient httpClient = new DefaultHttpClient();
        HttpEntity httpEntity = null;
        try {
            String result = "";
            //将列表添加到HttpEntity中
            //HttpEntity requesthttpEntity = new UrlEncodedFormEntity(list, "UTF-8");
            //生成一个请求对象
            HttpPost httpPost = new HttpPost(url);
            //httpPost设置Entity ==把数据添加到了httpPost中
            //httpPost.setEntity(requesthttpEntity);
            // 请求超时
            httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 20000);
            // 读取超时
            httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 20000);
            for (NameValuePair nvp : list) {
                httpClient.getParams().setParameter(nvp.getName(), nvp.getValue());
            }
            logger.debug(httpPost.getURI());
            StringBuffer sb = new StringBuffer();
            for (NameValuePair p : list) {
                if (p.getName().toLowerCase().indexOf("password") == -1
                        && p.getName().toLowerCase().indexOf("check") == -1)
                    sb.append("&" + p.getName() + "=" + p.getValue());
                else
                    sb.append("&" + p.getName() + "=*******");
            }
            logger.debug("param=" + sb.toString());
            HttpResponse httpResponse = httpClient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() != 200) {
                throw new Exception("Failed : HTTP error code : " + httpResponse.getStatusLine().getStatusCode());
            }
            httpEntity = httpResponse.getEntity();
            InputStream inputStream = httpEntity.getContent();
            //BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                result = result + line;
            }
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        } finally {
            try {
                EntityUtils.consume(httpEntity);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                logger.error(e.getMessage(), e);
            }
        }

    }
}

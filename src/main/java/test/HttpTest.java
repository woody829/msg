package test;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by shengjk1  on 2016/5/23.
 */
public class HttpTest {

    public void requestByPostMethod() {
        CloseableHttpClient httpClient = getHttpClient();
        try {
            HttpPost post = new HttpPost("http://localhost:8888/match?idCard=1234567890"); //这里用上本机的某个工程做测试



//            //创建参数列表
//            List<NameValuePair> list = new ArrayList<NameValuePair>();
//            list.add(new BasicNameValuePair("idCard", "1234567890"));
//            list.add(new BasicNameValuePair("name", "张三1"));
//            list.add(new BasicNameValuePair("cardType", "ID"));
//            list.add(new BasicNameValuePair("invoker", "RI"));
//            list.add(new BasicNameValuePair("period", "240"));

            //url格式编码
//            UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(list, "UTF-8");
//            post.setEntity(uefEntity);
            System.out.println("POST 请求...." + post.getURI());
            //执行请求
            CloseableHttpResponse httpResponse = httpClient.execute(post);
            try {
                HttpEntity entity = httpResponse.getEntity();
                if (null != entity) {
                    System.out.println("-------------------------------------------------------");
                    System.out.println(EntityUtils.toString(entity));
                    System.out.println("-------------------------------------------------------");
                }
            } finally {
                httpResponse.close();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                closeHttpClient(httpClient);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private CloseableHttpClient getHttpClient() {
        return HttpClients.createDefault();
    }

    private void closeHttpClient(CloseableHttpClient client) throws IOException {
        if (client != null) {
            client.close();
        }
    }

    public static void main(String[] args) {
        HttpTest ht = new HttpTest();
        ht.requestByPostMethod();
    }
}

package cb.util;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.transform.TransformerException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by woody on 2018/6/29.
 */
public class HmacUtil {
    private static final String HMAC_MD5_NAME = "HmacMD5";
//    private static final String key = "ff3007ce1906cb05b2789a6726c6d952ffd7b533";
    private static final String data = "[{\"cmd\":\"user/userInfo\",\"body\":{}}]";




    public static byte[] encode(byte[] data,String key) throws Exception {
        SecretKeySpec sk = new SecretKeySpec(key.getBytes("UTF-8"), HMAC_MD5_NAME);
        Mac mac;
        try {
            mac = Mac.getInstance(HMAC_MD5_NAME);
            mac.init(sk);
            return mac.doFinal(data);
        } catch (NoSuchAlgorithmException e) {
            throw new TransformerException(e);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public static void main(String[] args) throws Exception {
        String key = "7b58254791ada6c0194e6341953f862aff9a91b5";
        byte[] doFinal = encode(data.getBytes("UTF-8"),key);
        byte[] hexB = new Hex().encode(doFinal);
        String checksum = new String(hexB);
        System.out.println(checksum);


        String s = "{\"body\":{},\"cmd\":\"transfer/assets\"}";
    }

    public static String encode(String data,String key) throws Exception {
        return new String(new Hex().encode(encode(data.getBytes("UTF-8"),key)));
    }
}

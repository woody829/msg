package common.util.crypto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by jevon on 2015/12/22.
 */
public class AESUtils {
    private static final Logger LOG = LoggerFactory.getLogger(AESUtils.class);
    /**
     * 密钥算法
     */
    private static final String KEY_ALGORITHM = "AES";
    private static final int KEY_SIZE = 128;
    /**
     * 加密/解密算法/工作模式/填充方法
     */
    public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    /**
     * 获取密钥
     *
     * @return
     * @throws Exception
     */
    public static byte[] getKey() {
        try {
            KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
            kg.init(KEY_SIZE);
            SecretKey secretKey = kg.generateKey();
            return secretKey.getEncoded();
        } catch (Exception ex) {
            LOG.error("error getKey", ex);
        }
        return null;
    }

    /**
     * 加密
     *
     * @param text
     * @param key
     * @return
     */
    public static byte[] encrypt(byte[] text, byte[] key) {
        try {
            SecretKeySpec aesKey = new SecretKeySpec(key, KEY_ALGORITHM);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            return cipher.doFinal(text);
        } catch (Exception ex) {
            LOG.error("error encrypt", ex);
        }
        return null;
    }

    /**
     * 解密
     *
     * @param text
     * @param key
     * @return
     */
    public static byte[] decrypt(byte[] text, byte[] key) {
        try {
            SecretKeySpec aesKey = new SecretKeySpec(key, KEY_ALGORITHM);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            return cipher.doFinal(text);
        } catch (Exception ex) {
            LOG.error("error decrypt", ex);
        }
        return null;
    }
}

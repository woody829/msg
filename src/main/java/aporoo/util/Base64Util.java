package aporoo.util;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Base64;

/**
 * Created by woody on 2018/6/9.
 */
public class Base64Util {

    private static final Logger LOG = LoggerFactory.getLogger(Base64Util.class);
    public static byte[] hexToByte(String value) {
        try {
            return Hex.decodeHex(value.toCharArray());
        } catch (DecoderException dex) {
            LOG.error("DecoderException: {}", dex);
        }
        return null;
    }

    /**
     * 转换byte为base64字符串
     *
     * @param value
     * @return
     */
    public static String base64Encoder(byte[] value) {
        return Base64.getEncoder().encodeToString(value);
    }


    //二行制转字符串
    public static String byte2hex(byte[] b)
    {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b!=null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1)
                hs.append('0');
            hs.append(stmp);
        }
        return hs.toString().toUpperCase();
    }
}

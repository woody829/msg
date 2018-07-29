package common.util.crypto;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.CharEncoding;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jevon on 2015/12/15.
 */
public class CgMessageUtils {
    private static final Logger LOG = LoggerFactory.getLogger(CgMessageUtils.class);
    public static final String HUNDSUN_P2P_MSG_VERSION = "v1.0"; // 版本号
    public static final String HUNDSUN_P2P_MSG_SYSTEM_TYPE_4 = "4"; // P2P托管系统
    public static final String HUNDSUN_P2P_MSG_INSTR_CODE_46701 = "46701"; // 客户注册
    public static final String HUNDSUN_P2P_MSG_INSTR_CODE_46702 = "46702"; // 绑卡-必须先验卡，并且传入验卡的交易流水号
    public static final String HUNDSUN_P2P_MSG_INSTR_CODE_48702 = "48702"; // 验卡
    public static final String HUNDSUN_P2P_MSG_INSTR_CODE_48706 = "48706"; // 充值
    public static final String HUNDSUN_P2P_MSG_INSTR_CODE_42701 = "42701"; // 建标
    public static final String HUNDSUN_P2P_MSG_INSTR_CODE_42702 = "42702"; // 撤标
    public static final String HUNDSUN_P2P_MSG_INSTR_CODE_42705 = "42705"; // 开标确认-将钱转入建标人的存管帐户
    public static final String HUNDSUN_P2P_MSG_INSTR_CODE_42700 = "42700"; // 结标
    public static final String HUNDSUN_P2P_MSG_INSTR_CODE_42703 = "42703"; // 投标
    public static final String HUNDSUN_P2P_MSG_INSTR_CODE_42706 = "42706"; // 债权受让
    public static final String HUNDSUN_P2P_MSG_INSTR_CODE_42707 = "42707"; // 借款人还款
    public static final String HUNDSUN_P2P_MSG_INSTR_CODE_44946 = "44946"; // 还款兑付(还款到投资人)
    public static final String HUNDSUN_P2P_MSG_INSTR_CODE_44702 = "44702"; // 出金-将钱转入银行卡
    public static final String HUNDSUN_P2P_MSG_INSTR_CODE_46720 = "46720"; // 管理帐户余额查询
    public static final String HUNDSUN_P2P_MSG_INSTR_CODE_46703 = "46703"; // 撤销客户绑定的银行卡
    public static final String HUNDSUN_P2P_MSG_INSTR_CODE_44711 = "44711"; // 交易结果查询
    public static final String HUNDSUN_P2P_MSG_INSTR_CODE_46723 = "46723"; // 市场管理余额查询
    public static final String HUNDSUN_P2P_MSG_INSTR_CODE_44705 = "44705"; // 收益划出
    public static final String HUNDSUN_P2P_MSG_INSTR_CODE_44709 = "44709"; // 客户资金提前划入通知-暂无此业务
    public static final String HUNDSUN_P2P_MSG_INSTR_CODE_48709 = "48709"; // 客户佣金派送
    public static final String HUNDSUN_P2P_MSG_INSTITUTION_ID = "P0002000"; // 机构标识

    public static final String MSG_SEPARATOR = "|"; //发送内容分割符

    private static Map<String, String> KEY_CONTAINER = new HashMap<String, String>();

    public static final String HUNDSUN_RSA_PUBLIC_KEY = "hundsunRSAPublicKey";
    public static final String HUNDSUN_RSA_PRIVATE_KEY = "hundsunRSAPrivateKey";
    public static final String P2P_RSA_PUBLIC_KEY = "p2pRSAPublicKey";
    public static final String P2P_RSA_PRIVATE_KEY = "p2pRSAPrivateKey";

    private static final Map<String, String> KEY_FILE_PATH = new HashMap<String, String>();//密钥文件集合

    static {
        try {
            KEY_FILE_PATH.put(HUNDSUN_RSA_PUBLIC_KEY, Thread.currentThread().getContextClassLoader().getResource("key/huarui_qguanzi_public.pem").toURI().getPath());
            KEY_FILE_PATH.put(HUNDSUN_RSA_PRIVATE_KEY, Thread.currentThread().getContextClassLoader().getResource("key/huarui_qguanzi_private.pem").toURI().getPath());
            KEY_FILE_PATH.put(P2P_RSA_PUBLIC_KEY, Thread.currentThread().getContextClassLoader().getResource("key/qguanzi_public.pem").toURI().getPath());
            KEY_FILE_PATH.put(P2P_RSA_PRIVATE_KEY, Thread.currentThread().getContextClassLoader().getResource("key/qguanzi_private.pem").toURI().getPath());
        } catch (Exception e){
            LOG.error("密钥读取失败：{}",e.getMessage());
        }
    }

    /**
     * 通讯数据分为四部分：
     * 1.商户ID
     * 2.接收方RSA公钥加密后的本次会话的AES密钥
     * 3.发送方RSA私钥签的本次业务数据的签名
     * 4.AES加密的本次业务数据
     * 2、3、4部分由于是二进制结果，为了便于用文本描述，再加一层 Base64 编码。
     * 1、2、3、4四部分最后以竖线“|”分隔，组成完整的报文。每次通讯时均需随机生成 AES 密钥。
     * /
     *
     * @param xmlContent
     * @return
     */
    public static String getEncryptContent(String xmlContent) {
        String aesKey = makeAESKey();
        LOG.debug("key value: {}", aesKey);
        StringBuilder result = new StringBuilder();
        result.setLength(0);
        result.append(HUNDSUN_P2P_MSG_INSTITUTION_ID + MSG_SEPARATOR);//商户号
        result.append(encryptAESKeyByRSA(aesKey) + MSG_SEPARATOR);//加密AES加密密钥
        result.append(signXmlContent(xmlContent) + MSG_SEPARATOR);//发送内容签名
        result.append(encryptContentByAES(xmlContent, aesKey));//发送内容加密

        return result.toString();
    }

    /**
     * 解密，返回数据由4部分组成，数据结构与发送数据一致。
     *
     * @param encryptContent
     * @return
     */
    public static String getDecryptContent(String encryptContent) {
        String[] encrypt = encryptContent.split("\\|");
        String aesKey = decryptAESKeyByRSA(encrypt[1]);//解密AES加密密钥
        LOG.debug("return aesKey: {}, decrypt result: {}", encrypt[1], aesKey);
        String xmlContent = CgMessageUtils.decryptContentByAES(encrypt[3], aesKey);//使用AES密钥解密发送内容
        LOG.debug("decrypt : result {}", xmlContent);
        if (verifySign(xmlContent, encrypt[2])) {//验签
            LOG.debug("decrypt xmlContent: result {}", xmlContent);
            return xmlContent;
        }
        return StringUtils.EMPTY;
    }

    /**
     * 获取密钥字符串
     *
     * @param keyName
     * @return
     */
    public static String getKey(String keyName) {
        if (StringUtils.isEmpty(KEY_CONTAINER.get(keyName))) {
            initKey(keyName, KEY_FILE_PATH.get(keyName));
        }
        return KEY_CONTAINER.get(keyName);
    }

    /**
     * 初始化密钥
     *
     * @param keyName
     * @param keyFilePath
     */
    private static void initKey(String keyName, String keyFilePath) {
        KEY_CONTAINER.put(keyName, RSAUtils.getKeyString(keyFilePath));
    }

    /**
     * 传输内容进行签名
     *
     * @param xmlContent
     * @return
     */
    public static String signXmlContent(String xmlContent) {
        return RSAUtils.sign(getUTF8Byte(xmlContent), getKey(P2P_RSA_PRIVATE_KEY));
    }

    /**
     * 验签
     *
     * @param xmlContent
     * @return
     */
    public static boolean verifySign(String xmlContent, String sign) {
        return RSAUtils.verify(xmlContent.getBytes(), getKey(P2P_RSA_PUBLIC_KEY), sign);
    }

    /**
     * 使用RSA算法对AES密钥进行加密
     *
     * @param key
     * @return
     */
    public static String encryptAESKeyByRSA(String key) {
        return base64Encoder(RSAUtils.encryptByPublicKey(hexToByte(key), getKey(HUNDSUN_RSA_PUBLIC_KEY)));
    }

    /**
     * AES密钥解密
     *
     * @param encryptKey
     * @return
     */
    public static String decryptAESKeyByRSA(String encryptKey) {
        return Hex.encodeHexString(RSAUtils.decryptByPrivateKey(base64Decode(encryptKey), getKey(HUNDSUN_RSA_PRIVATE_KEY)));
    }

    private static byte[] getUTF8Byte(String value) {
        return value.getBytes(Charset.forName(CharEncoding.UTF_8));
    }

    private static String getUTF8String(byte[] value) {
    	return new String(value, Charset.forName(CharEncoding.UTF_8));
    }

    /**
     * 生成AES加密的密钥
     *
     * @return
     */
    public static String makeAESKey() {
        return Hex.encodeHexString(AESUtils.getKey());
    }

    /**
     * AES加密
     *
     * @param text
     * @param key
     * @return
     */
    public static String encryptContentByAES(String text, String key) {
        return base64Encoder(AESUtils.encrypt(text.getBytes(), hexToByte(key)));
    }


    /**
     * AES解密
     *
     * @param text
     * @param key
     * @return
     */
    public static String decryptContentByAES(String text, String key) {
        return getUTF8String(AESUtils.decrypt(base64Decode(text), hexToByte(key)));
    }

    private static byte[] hexToByte(String value) {
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
        return Base64.encodeBase64String(value);
    }

    /**
     * base64解码
     *
     * @param value
     * @return
     */
    public static byte[] base64Decode(String value) {
        return Base64.decodeBase64(value);
    }

    /**
     * 获取交易流水号，必须保持唯一。
     *
     * @return
     */
    public static String getRequestReferenceID() {
        return DateTime.now().toString("yyyyMMddHHmmss") + "0001";// 交易流水号，日期+4位序列，这里先固定为“0001”
    }

    /**
     * 获取xml消息头
     *
     * @param instructionCode
     * @return
     */
    public static String getMessageHeader(String instructionCode, String requestReference) {
        StringBuilder msgHdr = new StringBuilder();
        msgHdr.setLength(0);
        msgHdr.append("<Ver>" + HUNDSUN_P2P_MSG_VERSION + "</Ver>");
        msgHdr.append("<SysType>" + HUNDSUN_P2P_MSG_SYSTEM_TYPE_4 + "</SysType>");
        msgHdr.append("<InstrCd>" + instructionCode + "</InstrCd>");
        msgHdr.append("<TradSrc>0</TradSrc>");
        msgHdr.append("<SvInst>");
        msgHdr.append("<InstType>0</InstType>");
        msgHdr.append("<InstId>" + HUNDSUN_P2P_MSG_INSTITUTION_ID + "</InstId>");
        msgHdr.append("</SvInst>");
        msgHdr.append("<Date>" + DateTime.now().toString("yyyyMMdd") + "</Date>");
        msgHdr.append("<Time>" + DateTime.now().toString("HHmmss") + "</Time>");
        msgHdr.append("<RqRef>");
        msgHdr.append("<Ref>" + requestReference + "</Ref>");
        msgHdr.append("</RqRef>");
        msgHdr.append("<LstFrag>Y</LstFrag>");

        return msgHdr.toString();
    }

    public static String getMessageHeader(String instructionCode) {
        return getMessageHeader(instructionCode, getRequestReferenceID());
    }

    /**
     * 获取客户xml结构
     *
     * @param customerName
     * @param certId
     * @return
     */
    public static String getCustomer(String customerName, String certId) {
        StringBuilder customer = new StringBuilder();
        customer.setLength(0);
        customer.append("<Name>" + customerName + "</Name>");
        customer.append("<CertType>1</CertType>");// 身份证
        customer.append("<CertId>" + certId + "</CertId>");
        customer.append("<Type>0</Type>");//个人
        customer.append("<Mobile/>");

        return customer.toString();
    }

    /**
     * 获取银行帐号xml结构
     *
     * @param accountId
     * @param bankCode
     * @param bankName
     * @param telephoneNo
     * @return
     */
    public static String getBankAccount(String accountId, String bankCode, String bankName, String telephoneNo) {
        StringBuilder bankAccount = new StringBuilder();
        bankAccount.setLength(0);
        bankAccount.append("<Id>" + accountId + "</Id>");
        bankAccount.append("<BkCode>" + bankCode + "</BkCode>");
        bankAccount.append("<BkName>" + bankName + "</BkName>");
        bankAccount.append("<TelNo>" + telephoneNo + "</TelNo>");
        bankAccount.append("<Name></Name><Type></Type><BkFlag></BkFlag><CardType></CardType>");

        return bankAccount.toString();
    }

    /**
     * 获取存款帐户xml结构
     *
     * @param accountId
     * @return
     */
    public static String getTranManagerAccount(String accountId) {
        StringBuilder tranManagerAccount = new StringBuilder();
        tranManagerAccount.setLength(0);
        tranManagerAccount.append("<Id>" + accountId + "</Id>");

        return tranManagerAccount.toString();
    }

    /**
     * 获取标的信息xml结构
     *
     * @return
     */
    public static String getBidInfo(
            String bidNo, String bidType, String accountId, String bidTotBala, String annualRate, String raiseBeginDate, String raiseEndDate, String payMethod) {
        StringBuilder bidInfo = new StringBuilder();
        bidInfo.setLength(0);
        bidInfo.append("<BidNo>" + bidNo + "</BidNo>");
        bidInfo.append("<BidType>" + bidType + "</BidType>");
        bidInfo.append("<MgeAcct><Id>" + accountId + "</Id><Name></Name></MgeAcct>");
        bidInfo.append("<BidTotBala>" + bidTotBala + "</BidTotBala>");
        bidInfo.append("<AnnuRate>" + annualRate + "</AnnuRate>");
        bidInfo.append("<RaiseBeginDate>" + raiseBeginDate + "</RaiseBeginDate>");
        bidInfo.append("<RaiseEndDate>" + raiseEndDate + "</RaiseEndDate>");
        bidInfo.append("<PayMeth>" + payMethod + "</PayMeth>");

        return bidInfo.toString();
    }

    /**
     * 获取还款兑付xml结构
     *
     * @return
     */
    public static String getRepayBillInfo(
            String bidNo, String bidType, String repayMethod, String acctId, String curRepayPrcpl, String curRepayPro, String repayPeriod) {
        StringBuilder repayBillInfo = new StringBuilder();
        repayBillInfo.setLength(0);
        repayBillInfo.append("<RepayBillSeril>" + getRequestReferenceID() + "</RepayBillSeril>");
        repayBillInfo.append("<BidNo>" + bidNo + "</BidNo>");
        repayBillInfo.append("<BidType>" + bidType + "</BidType>");
        repayBillInfo.append("<RepayMethod>" + repayMethod + "</RepayMethod>");//0-客户还款1-风险准备金赔付2-担保金赔付
        repayBillInfo.append("<AcctId>" + acctId + "</AcctId>");
        repayBillInfo.append("<CurRepayPrcpl>" + curRepayPrcpl + "</CurRepayPrcpl>");
        repayBillInfo.append("<CurRepayPro>" + curRepayPro + "</CurRepayPro>");
        repayBillInfo.append("<FeeAmt>0.00</FeeAmt>");
        repayBillInfo.append("<RepayPeriod>" + repayPeriod + "</RepayPeriod>");

        return repayBillInfo.toString();
    }

}

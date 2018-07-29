package tx.request;

import base.model.MsgDTO;
import common.util.XmlUtil;
import common.util.crypto.P2PMessageUtils;
import org.dom4j.Document;

/**
 * Created by woody on 2017/3/24.
 */
public abstract class TxBaseRequest {
    protected String txCode;
    protected MsgDTO msgDTO;
    protected String originText;
    protected String prettyText;

    protected String requestMessage;

    public TxBaseRequest() {
    }

    public String getTxCode() {
        return txCode;
    }

    public void setTxCode(String txCode) {
        this.txCode = txCode;
    }

    public MsgDTO getMsgDTO() {
        return msgDTO;
    }

    public void setMsgDTO(MsgDTO msgDTO) {
        this.msgDTO = msgDTO;
    }

    public String getOriginText() {
        return originText;
    }

    public void setOriginText(String originText) {
        this.originText = originText;
    }

    public String getPrettyText() {
        return prettyText;
    }

    public void setPrettyText(String prettyText) {
        this.prettyText = prettyText;
    }

    public String getRequestMessage() {
        return requestMessage;
    }

    public void setRequestMessage(String requestMessage) {
        this.requestMessage = requestMessage;
    }

    public abstract void process(MsgDTO msgDTO) throws Exception;

    protected void postProcess(MsgDTO msgDTO) throws Exception {
        Document doc = msgDTO.getDoc();
        msgDTO.setOriginText(doc.asXML());

        this.prettyText = XmlUtil.createPrettyFormat(doc, "UTF-8").trim();
        msgDTO.setPrettyText(prettyText);

        String requestMessage = P2PMessageUtils.getEncryptContent(msgDTO.getOriginText());
        msgDTO.setRequestMessage(requestMessage);
    }
}

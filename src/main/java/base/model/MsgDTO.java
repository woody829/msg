package base.model;

import org.dom4j.Document;

/**
 * Created by woody on 2017/3/29.
 */
public class MsgDTO extends BaseDTO {
    private String txCode;
    private String txName;

    private String bkInst;
    private String Date;
    private String time;
    private String instrCd;
    private String lstFrag;
    private String ref;
    private String instId;
    private String instType;
    private String sysType;
    private String tradSrc;
    private String ver;
    private String busType;
    private String ccy;
    private String certId;
    private String certType;
    private String name;
    private String type;
    private String id;
    private String oPFlag;

    private Document doc;
    private String originText;
    private String prettyText;
    private String requestMessage;


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

    public String getTxCode() {
        return txCode;
    }

    public void setTxCode(String txCode) {
        this.txCode = txCode;
    }

    public String getBkInst() {
        return bkInst;
    }

    public void setBkInst(String bkInst) {
        this.bkInst = bkInst;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public Document getDoc() {
        return doc;
    }

    public void setDoc(Document doc) {
        this.doc = doc;
    }

    public String getTxName() {
        return txName;
    }

    public void setTxName(String txName) {
        this.txName = txName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getInstrCd() {
        return instrCd;
    }

    public void setInstrCd(String instrCd) {
        this.instrCd = instrCd;
    }

    public String getLstFrag() {
        return lstFrag;
    }

    public void setLstFrag(String lstFrag) {
        this.lstFrag = lstFrag;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getInstId() {
        return instId;
    }

    public void setInstId(String instId) {
        this.instId = instId;
    }

    public String getInstType() {
        return instType;
    }

    public void setInstType(String instType) {
        this.instType = instType;
    }

    public String getSysType() {
        return sysType;
    }

    public void setSysType(String sysType) {
        this.sysType = sysType;
    }

    public String getTradSrc() {
        return tradSrc;
    }

    public void setTradSrc(String tradSrc) {
        this.tradSrc = tradSrc;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getCertId() {
        return certId;
    }

    public void setCertId(String certId) {
        this.certId = certId;
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getoPFlag() {
        return oPFlag;
    }

    public void setoPFlag(String oPFlag) {
        this.oPFlag = oPFlag;
    }
}

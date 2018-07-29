package tx.request;

import base.model.MsgDTO;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.tree.BaseElement;
import org.slf4j.Logger;

/**
 * Created by woody on 2017/3/23.
 */
public class Tx46701Request extends TxBaseRequest {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(Tx46701Request.class);
    String MsgHdr;
    String BkInst;
    String Date;
    String RqRef;
    String Ref;
    MsgDTO msgDTO;

    public void process(MsgDTO msgDTO) throws Exception {
        logger.debug("46701页面元素:msgDTO: {}", msgDTO);
        //创建一篇文档
        Document doc = DocumentHelper.createDocument();

        //添加一个元素
        Element root = doc.addElement("MsgText");

        /****MsgHdr开始****/
        //创建元素MsgHdr
        Element MsgHdr = new BaseElement("MsgHdr");
        root.add(MsgHdr);

        //创建元素BkInst
        Element BkInst = new BaseElement("BkInst");
        BkInst.setText(msgDTO.getBkInst());
        MsgHdr.add(BkInst);

        //创建元素Date
        Element date = new BaseElement("Date");
        date.setText(msgDTO.getDate());
        MsgHdr.add(date);

        //创建元素Time
        Element time = new BaseElement("Time");
        time.setText(msgDTO.getTime());
        MsgHdr.add(time);

        //创建元素InstrCd
        Element instrCd = new BaseElement("InstrCd");
        instrCd.setText(msgDTO.getInstrCd());
        MsgHdr.add(instrCd);

        //创建元素LstFrag
        Element lstFrag = new BaseElement("LstFrag");
        lstFrag.setText(msgDTO.getLstFrag());
        MsgHdr.add(lstFrag);


        //创建元素RqRef
        Element rqRef = new BaseElement("RqRef");
        rqRef.addElement("Ref").addText(msgDTO.getRef());
        MsgHdr.add(rqRef);

        //创建元素SvInst
        Element svInst = new BaseElement("SvInst");
        svInst.addElement("InstId").addText(msgDTO.getInstId());
        svInst.addElement("InstType").addText(msgDTO.getInstType());
        MsgHdr.add(svInst);

        //创建元素SysType
        Element sysType = new BaseElement("SysType");
        sysType.setText(msgDTO.getSysType());
        MsgHdr.add(sysType);

        //创建元素TradSrc
        Element tradSrc = new BaseElement("TradSrc");
        tradSrc.setText(msgDTO.getTradSrc());
        MsgHdr.add(tradSrc);

        //创建元素Ver
        Element ver = new BaseElement("Ver");
        ver.setText(msgDTO.getVer());
        MsgHdr.add(ver);

        /****MsgHdr结束****/

        //创建元素BusType
        Element busType = new BaseElement("BusType");
        busType.setText(msgDTO.getBusType());
        root.add(busType);

        //创建元素Ccy
        Element ccy = new BaseElement("Ccy");
        ccy.setText(msgDTO.getCcy());
        root.add(ccy);


        //创建元素Cust
        Element cust = new BaseElement("Cust");
        cust.addElement("CertId").addText("420684198612066412");
        cust.addElement("CertType").addText("1");
        cust.addElement("Name").addText("阿凡达");
        cust.addElement("Type").addText("0");
        root.add(cust);

        //创建元素MgeAcct
        Element mgeAcct = new BaseElement("MgeAcct");
        mgeAcct.addElement("Id").addText("20170208003");
        root.add(mgeAcct);

        //创建元素OPFlag
        Element oPFlag = new BaseElement("OPFlag");
        oPFlag.setText("0");
        root.add(oPFlag);

        doc.setXMLEncoding("GB2312");

        logger.debug("origin xml: {}", doc.asXML());
        msgDTO.setDoc(doc);
        this.postProcess(msgDTO);
    }

    public static void main(String[] args) throws Exception {
        Tx46701Request t = new Tx46701Request();
        MsgDTO msgDTO = null;
        t.process(msgDTO);
    }
}

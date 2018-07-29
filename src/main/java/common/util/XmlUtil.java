package common.util;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringWriter;

/**
 * Created by woody on 2017/3/30.
 */
public class XmlUtil {
    private static final Logger logger = LoggerFactory.getLogger(XmlUtil.class);

    public XmlUtil() {
    }

    public static String createPrettyFormat(Document doc, String encoding) throws Exception {
        StringWriter writer = new StringWriter();
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding(encoding);

        XMLWriter xmlwriter = new XMLWriter(writer, format);
        xmlwriter.write(doc);
        return writer.toString();
    }

    public static String createPrettyFormat(String xmlResult, String encoding) {
        int pos = xmlResult.indexOf("xml version");
        try {
            if (pos < 0) {
                logger.debug("非xml标准格式，美化失败: {}", xmlResult);
                return xmlResult;
            }
            String head = xmlResult.substring(0, pos - 2);
            String end = xmlResult.substring(pos - 2);
            Document document = DocumentHelper.parseText(end);
            return head + "\r\n" + createPrettyFormat(document, encoding);
        } catch (Exception e) {
            logger.error("xml格式化失败: {}" + e.getMessage());
        }
        return xmlResult;
    }
}
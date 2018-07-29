package okcoin.websocket.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.stereotype.Service;

import java.net.URL;

/**
 * Created by woody on 2018/4/28.
 */
@Service("test")
public class Test {
//    private static final Logger logger = LoggerFactory.getLogger(Test.class);

    private static Log log = LogFactory.getLog(Test.class);
    public static void main(String[] args) throws Exception {

//        PropertyConfigurator.configure("classpath:properties/log4j.properties");
//
//        PropertyConfigurator.configure("classpath:properties/log4j.properties");
//        Thread.currentThread().getClassLoader().getResource("conf/log4j.properties);
               URL url = Thread.currentThread().getContextClassLoader().getResource("properties/log4j.properties");

//        Test.class.getClassLoader().getResource("basic/log4j.properties");
        PropertyConfigurator.configure(url);
        log.debug("abc");


    }
}

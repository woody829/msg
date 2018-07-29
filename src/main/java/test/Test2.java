package test;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;


public class Test2 {
    public static void main(String[] args)  {
        double d = 50000000.01d;
        DecimalFormat kk = new DecimalFormat("#.0000");
        System.out.println(kk.format(d));


        float f = 50000000.01f;
        double ss = f;
        System.out.println(ss);



       String s ="{\"return_head\":{\"status\":\"0\",\"errorReason\":\"\"},\"return_body\":{\"applicationId\":\"BA2018070500000003\",\"customerScore\":300,\"cusScoMatDate\":\"20991231\",\"creditFacility\":50000000.01}}";
       JsonConfig jsonConfig = new JsonConfig();
       jsonConfig.registerJsonValueProcessor(Float.class, new JsonValueProcessor() {
		DecimalFormat df = new DecimalFormat("#.0000");
		public Object processObjectValue(String arg0, Object num, JsonConfig arg2) {
		
			return df.format(num);
		}
		
		public Object processArrayValue(Object num, JsonConfig arg1) {
		
			return df.format(num);
		}
	});
       JSONObject js = JSONObject.fromObject(s, jsonConfig); 
       JSONObject js1 = JSONObject.fromObject(s);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("date", 50000000.01d);
        JSONObject js2 = JSONObject.fromObject(map, jsonConfig);
        System.out.println(js2);



       System.out.println(js.toString());
       System.out.println(js1.toString());
    }
}

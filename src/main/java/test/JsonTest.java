package test;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by woody on 2018/7/1.
 */
public class JsonTest {

    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("cmd", "transfer/assets");
        JSONObject innerObj = new JSONObject();
//		innerObj.put("select", new Integer(1));
        jsonObject.put("body", innerObj);
        System.out.println("jsonObject:"+jsonObject.toJSONString());
        System.out.println("jsonObject:"+jsonObject.toString());
    }
}

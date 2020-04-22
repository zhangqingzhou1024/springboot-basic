package com.liziyuan.hope.common.utils.jslt;

/**
 * jslt测试类
 *
 * @author zqz
 * @version 1.0
 * @date 2020-04-22 17:19
 */
public class JsltTest {


    public static void main(String[] args) {

        String jsltTransform = "{\n" +
                "    \"time\": round(parse-time(.published, \"yyyy-MM-dd HH:mm:ss\") * 1000),\n" +
                "    \"device_manufacturer\": .device.manufacturer,\n" +
                "    \"os_name\": .device.osType,\n" +
                "    \"new_age\": parseInt(parseLong(.age)),\n" +
                "    \"os_version\": .device.osVersion,\n" +
                "    \"platform\": .device.platformType\n" +

                "}";


        String input = "{\"age\":\"20\",\"device\":{\"manufacturer\":\"aaa\",\"osType\":\"mysql\",\"osVersion\":\"1\",\"platformType\":\"1\"},\"published\":\"2019-08-09 10:10:11\"}";


        String output = JsltTransformUtils.transformOutput(input, jsltTransform);

        System.out.println("input => " + input);
        System.out.println("jsltTransform => " + jsltTransform);
        System.out.println("output =>" + output);


    }
}

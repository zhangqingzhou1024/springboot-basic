package com.liziyuan.hope.common.utils;

import com.api.jsonata4java.expressions.Expressions;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liziyuan.hope.common.utils.jslt.JsltTransformUtils;
import com.schibsted.spt.data.jslt.Parser;
import org.apache.avro.Schema;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * 常用字段验证方法类
 *
 * @author zqz
 * @version 1.0
 * @date 2020-04-14 21:55
 */
public class FieldValueUtils {
    static ObjectMapper mapper = new ObjectMapper();
    /**
     * 正则表达式 域名验证-不严格，范围广
     * 字母数字，每个域名级别字符疮毒不超过63个
     */
    private static final String DOMAIN_COMMON_REGEX = "^(?=^.{3,255}$)[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+$";

    /**
     * 判断是否为空的形式
     *
     * @param object Object 对象
     * @return 是否为空
     */
    public static boolean isNullType(Object object) {
        if (null == object) {
            return true;
        }
        // 如果是字符串，要判断长度是否为空
        return (object instanceof String && ((String) object).trim().length() == 0);
    }

    /**
     * 去除空格
     *
     * @param source 源字符串
     * @return 去除两侧空格后的字符串
     */
    public static String trim(String source) {
        if (StringUtils.isEmpty(source)) {
            return null;
        }
        String trim = source.trim();
        if (trim.length() == 0) {
            return null;
        }
        return trim;
    }

    /**
     * 判断某个字符串是否超过某个长度
     *
     * @param source 源字符串
     * @param length 指定长度
     * @return 是否超过某个知道长度
     */
    public static boolean isOverSpecifiedLength(String source, int length) {
        if (length < 0) {
            throw new IllegalArgumentException("the String length must > 0, now set " + length);
        }
        if (isNullType(source)) {
            return false;
        }
        return source.trim().length() > length;
    }


    /**
     * 判断字符是否满足正则表达式
     *
     * @param source 源字符串
     * @param regex  正则表达式
     * @return 是否满足正则表达式
     */
    public static boolean isMatchRegex(String source, String regex) {
        return Pattern.matches(regex, source);
    }

    /***
     * 获取总页数
     * @param totalResults 总命中数
     * @param pageSize pageSize
     * @return 获取总页数
     */
    public static long getPageTotal(long totalResults, int pageSize) {
        if (0 == pageSize) {
            return 0L;
        }

        if (totalResults % pageSize == 0) {
            return totalResults / pageSize;
        }
        return totalResults / pageSize + 1;

    }

    /**
     * 判断字符是不是IP
     *
     * @param str 源字符串
     * @return 是否为IP
     */
    public static boolean isIpLegal(String str) {
        //检查ip是否为空
        if (isNullType(str)) {
            return false;
        }
        //检查ip长度，最短x.x.x.x（7位），最长为xxx.xxx.xxx.xxx（15位）
        if (str.length() < 7 || str.length() > 15) {
            return false;
        }
        //对输入字符串的首末字符判断，如果是“.”，则是非法ip
        if (str.charAt(0) == '.' || str.charAt(str.length() - 1) == '.') {
            return false;
        }
        //按"."分割字符串，并判断分割出来的个数，如果不是4个，则是非法ip
        String[] arr = str.split("\\.");
        if (arr.length != 4) {
            return false;
        }
        //对分割出来的字符串进行单独判断
        for (String s : arr) {
            //如果每个字符串不是一位字符，且以'0'开头，则是非法ip,如01.02.03.004
            if (s.length() > 1 && s.charAt(0) == '0') {
                return false;
            }
            //对每个字符串的每个字符进行逐一判断，如果不是数字0-9则是非法ip
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) < '0' || s.charAt(j) > '9') {
                    return false;
                }
            }
        }
        //对拆分的每一个字符串进行转换成数字，并判断是否在0-255
        for (int i = 0; i < arr.length; i++) {
            int temp = Integer.parseInt(arr[i]);
            if (i == 0) {
                if (temp < 1 || temp > 255) {
                    return false;
                }
            } else {
                if (temp < 0 || temp > 255) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 是否为端口
     *
     * @param obj 端口值
     * @return 是否为端口
     */
    public static boolean isPortVal(Object obj) {
        if (FieldValueUtils.isNullType(obj)) {
            return false;
        }

        int port;
        try {
            port = Integer.parseInt(obj.toString().trim());
        } catch (Exception e) {
            return false;
        }

        // 端口范围
        return port >= 0 && port < 65535;
    }

    /**
     * 验证是否为IP:port 格式
     * 1.1.1.1 (将使用默认端口)
     * 1.1.1.1:8080
     * 1.1.1.1:8080,1.1.1.2:9000
     *
     * @param sourceHost 原始内容
     */
    public static boolean validateHostPortPattern(String sourceHost) {
        if (FieldValueUtils.isNullType(sourceHost)) {
            return false;
        }

        String[] hostPortArr = sourceHost.split(",");
        for (String hostPort : hostPortArr) {
            String[] hostAndPort = hostPort.split(":");
            int length = hostAndPort.length;
            if (length > 2) {
                return false;

            }

            String host = hostAndPort[0];
            boolean isIp = isIpLegal(host);
            if (!isIp) {
                return false;
            }
            if (length == 2) {
                boolean isPort = isPortVal(hostAndPort[1]);
                if (!isPort) {
                    return false;
                }
            }

        }

        return true;
    }


    /**
     * 判断字符是不是域名
     *
     * @param source 源字符串
     * @return 是否为域名
     */
    public static boolean isDomainLegal(String source) {
        if (isNullType(source)) {
            return false;
        }

        return isMatchRegex(source, DOMAIN_COMMON_REGEX);
    }

    /**
     * 是否为 JSONata 格式
     *
     * @param source 字符传
     */
    public static boolean isJSONataPattern(String source) {
        if (FieldValueUtils.isNullType(source)) {
            return false;
        }
        String expression = "$sum(c)";
        try {
            JsonNode jsonObj = mapper.readTree(source);
            Expressions expr = Expressions.parse(expression);
            expr.evaluate(jsonObj);
        } catch (Exception e) {
            return false;
        }

        return true;
    }


    /**
     * 验证 字符串是否为 jslt-transform 格式
     *
     * @param transform 字符串
     * @return 是否满足 jslt-transform 格式
     */
    public static boolean isJsltTransformPattern(String transform) {
        if (FieldValueUtils.isNullType(transform)) {
            return false;
        }

        try {
            Parser.compileString(transform, JsltTransformUtils.allExtFunctions);
        } catch (Exception e) {
            return false;
        }

        return true;
    }


    /**
     * 验证是否符合 avro schema
     *
     * @param schema json字符串
     * @return 是否符合avro schema
     */
    public static boolean isAvroSchemaPattern(String schema) {
        if (FieldValueUtils.isNullType(schema)) {
            return false;
        }

        try {
            Schema.Parser parser = new Schema.Parser();
            parser.parse(schema);
        } catch (Exception e) {
            return false;
        }

        return true;
    }


}

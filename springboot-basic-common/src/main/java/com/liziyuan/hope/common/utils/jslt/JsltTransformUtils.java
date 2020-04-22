package com.liziyuan.hope.common.utils.jslt;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liziyuan.hope.common.utils.jslt.extension.ParseIntFunction;
import com.liziyuan.hope.common.utils.jslt.extension.ParseLongFunction;
import com.schibsted.spt.data.jslt.Expression;
import com.schibsted.spt.data.jslt.Function;
import com.schibsted.spt.data.jslt.Parser;

import java.util.ArrayList;
import java.util.List;

/**
 * JsltTransformUtils
 *
 * @author zqz
 * @version 1.0
 * @date 2020-04-22 17:50
 */
public class JsltTransformUtils {
    static ObjectMapper mapper = new ObjectMapper();
    public static List<Function> allExtFunctions;

    static {
        allExtFunctions = getAllExtFunctions();
    }

    /**
     * 获取全部自定义function
     *
     * @return 全部自定义functions
     */
    public static List<Function> getAllExtFunctions() {
        List<Function> functions = new ArrayList<>();
        functions.add(new ParseIntFunction());
        functions.add(new ParseLongFunction());

        return functions;
    }

    /**
     * 数据转换
     *
     * @param inputJsonStr json 格式的输入
     * @param transform    transform
     * @return output 转换后的数据
     */
    public static String transformOutput(String inputJsonStr, String transform) {
        Expression expr = Parser.compileString(transform, allExtFunctions);
        JsonNode input;
        try {
            input = mapper.readTree(inputJsonStr);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
        return expr.apply(input).toString();
    }
}

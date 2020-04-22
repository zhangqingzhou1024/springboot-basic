package com.liziyuan.hope.common.utils.jslt.extension;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.LongNode;
import com.schibsted.spt.data.jslt.Function;

/**
 * ParseLongFunction extension
 * <p>
 * 从 string 类型到 long
 *
 * @author zqz
 * @version 1.0
 * @date 2020-04-22 17:07
 */
public class ParseLongFunction implements Function {
    @Override
    public String getName() {
        return "parseLong";
    }

    @Override
    public int getMinArguments() {
        return 1;
    }

    @Override
    public int getMaxArguments() {
        return 1;
    }

    @Override
    public JsonNode call(JsonNode jsonNode, JsonNode[] params) {


        return new LongNode(params[0].asLong());
    }

}

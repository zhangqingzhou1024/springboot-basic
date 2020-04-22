package com.liziyuan.hope.common.utils.jslt.extension;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.schibsted.spt.data.jslt.Function;

/**
 * ParseIntFunction
 * 从 string 类型到 long
 *
 * @author zqz
 * @version 1.0
 * @date 2020-04-22 17:15
 */
public class ParseIntFunction implements Function {
    @Override
    public String getName() {
        return "parseInt";
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
        return new IntNode(params[0].asInt());
    }
}

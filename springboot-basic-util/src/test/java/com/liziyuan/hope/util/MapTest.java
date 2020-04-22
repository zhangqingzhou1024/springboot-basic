package com.liziyuan.hope.util;

import java.util.HashMap;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-04-11 16:40
 */
public class MapTest {
    public static void main(String[] args) {
        HashMap<Object, Object> test = new HashMap<>();
        test.put("a", 444);
        test.put("b", 555);

        HashMap<Object, Object> domain = new HashMap<>();
        domain.put("a", 666);

        test.putAll(domain);


        System.out.println(test.get("a"));
    }
}

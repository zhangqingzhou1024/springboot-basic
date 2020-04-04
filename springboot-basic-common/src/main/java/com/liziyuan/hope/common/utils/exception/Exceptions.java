package com.liziyuan.hope.common.utils.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Exceptions {
    public Exceptions() {
    }

    public static RuntimeException unchecked(Exception e) {
        return e instanceof RuntimeException ? (RuntimeException) e : new RuntimeException(e);
    }

    public static String getStackTraceAsString(Exception e) {
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
}

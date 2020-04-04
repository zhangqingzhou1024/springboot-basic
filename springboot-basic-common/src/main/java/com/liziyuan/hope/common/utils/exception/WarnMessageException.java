package com.liziyuan.hope.common.utils.exception;

/**
 * 封装一个异常
 *
 * @author zhangqingzhou
 */
public class WarnMessageException extends RuntimeException {
    private static final long serialVersionUID = -3746207040950467080L;
    private int code;

    public WarnMessageException(String msg) {
        this(500, msg);
    }

    public WarnMessageException(int code, String msg) {
        super(msg);
        this.code = 500;
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}

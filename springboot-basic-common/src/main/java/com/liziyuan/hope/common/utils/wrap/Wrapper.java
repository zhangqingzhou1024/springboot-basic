
package com.liziyuan.hope.common.utils.wrap;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class Wrapper<T> implements Serializable {
    private static final long serialVersionUID = 4893280118017319089L;
    public static final int ILLEGAL_ARGUMENT_CODE = 100;
    public static final String ILLEGAL_ARGUMENT_MESSAGE = "参数非法";
    public static final int TIME_OUT_CODE = 110;
    public static final String TIME_OUT_MESSAGE = "安全认证失败，时间过期";
    public static final int SECURE_FAIL_CODE = 120;
    public static final String SECURE_FAIL_MESSAGE = "安全认证失败，签名错误";
    public static final int SUCCESS_CODE = 200;
    public static final String SUCCESS_MESSAGE = "OK";
    public static final int ERROR_CODE = 500;
    public static final String ERROR_MESSAGE = "内部异常";
    private int code;
    private String message;
    private T result;
    private T data;

    public Wrapper() {
        this(200, "OK");
    }

    public Wrapper(int code, String message) {
        this(code, message, null);
    }

    public Wrapper(int code, String message, T result) {
        this.code(code).message(message).result(result);
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return this.result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public Wrapper<T> code(int code) {
        this.setCode(code);
        return this;
    }

    public Wrapper<T> message(String message) {
        this.setMessage(message);
        return this;
    }

    public Wrapper<T> result(T result) {
        this.setResult(result);
        return this;
    }

    @JsonIgnore(true)
    public boolean isSuccess() {
        return 200 == this.code;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

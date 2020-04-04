package com.liziyuan.hope.common.response;

import java.io.Serializable;

/**
 * @author zqz
 * @version V1.0
 * @Description:
 * @date 2019-05-18 22:34
 */
public class AbstractBaseResponse implements Serializable {

    private int code;
    private String message = "success";

    public AbstractBaseResponse() {
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
}

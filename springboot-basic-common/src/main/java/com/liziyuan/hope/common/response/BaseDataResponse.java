package com.liziyuan.hope.common.response;

/**
 * @author zqz
 * @version V1.0
 * @Description:
 * @date 2019-05-18 22:35
 */
public class BaseDataResponse<D> extends AbstractBaseResponse {
    private D data;

    public BaseDataResponse() {
    }

    public D getData() {
        return this.data;
    }

    public void setData(D data) {
        this.data = data;
    }
}

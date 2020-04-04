package com.liziyuan.hope.common.response;

import java.util.List;

/**
 * @author zqz
 * @version V1.0
 * @Description:
 * @date 2019-05-18 22:36
 */
public class ListDataResponse<D> extends AbstractBaseResponse {
    private List<D> list;

    public ListDataResponse() {
    }

    public List<D> getList() {
        return this.list;
    }

    public void setList(List<D> list) {
        this.list = list;
    }
}
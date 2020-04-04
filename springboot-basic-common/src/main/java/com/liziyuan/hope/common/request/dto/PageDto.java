package com.liziyuan.hope.common.request.dto;

import java.io.Serializable;

/**
 * @author zqz
 * @version V1.0
 * @Description:
 * @date 2019-05-18 22:26
 */
public class PageDto implements Serializable {

    private static final long serialVersionUID = 3755382994644821818L;
    private Integer pageSize;
    private Integer pageIndex;

    public PageDto() {
        this.pageSize = 10;
        this.pageIndex = 1;
    }

    public PageDto(Integer pageSize, Integer pageIndex) {
        this();
        this.setPageSize(pageSize);
        this.setPageIndex(pageIndex);
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        if (null != pageSize) {
            this.pageSize = pageSize;
        }

    }

    public Integer getPageIndex() {
        return this.pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        if (null != pageIndex) {
            this.pageIndex = pageIndex;
        }

    }
}

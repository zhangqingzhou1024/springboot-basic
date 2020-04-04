package com.liziyuan.hope.common.response;

import java.io.Serializable;

/**
 * @author zqz
 * @version V1.0
 * @Description:
 * @date 2019-05-18 22:33
 */
public class PageInfo implements Serializable {

    private Integer currentPage;
    private Integer pageSize;
    private Integer totalCount;

    public PageInfo() {
    }

    public Integer getCurrentPage() {
        return this.currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}

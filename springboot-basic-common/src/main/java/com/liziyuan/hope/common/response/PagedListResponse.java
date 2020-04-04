package com.liziyuan.hope.common.response;

import java.util.List;

/**
 * 返回结果依分页的形式返回
 *
 * @author zqz
 * @version V1.0
 * @Description:
 * @date 2019-05-18 22:36
 */
public class PagedListResponse<D> extends AbstractBaseResponse {
    private List<D> list;
    private PageInfo pageInfo;

    public PagedListResponse() {
    }

    public List<D> getList() {
        return this.list;
    }

    public void setList(List<D> list) {
        this.list = list;
    }

    public PageInfo getPageInfo() {
        return this.pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }
}

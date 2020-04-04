package com.liziyuan.hope.domain.query;


import com.liziyuan.hope.common.request.QueryWithOrder;
import lombok.Data;

import java.util.Date;

/**
 * 表:t_order_2的查询类
 *
 * @author chenlei
 * @version 1.0
 * @name com.liziyuan.search.answers.query.SearchOrderQuery.java
 * @Date 2019-6-2 22:49:21
 * @Description:
 */
@Data
public class SearchOrderQuery extends QueryWithOrder {

    /**
     * 序列化标识
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private Integer id;
    /**
     *
     */
    private Integer userId;
    /**
     *
     */
    private Integer orderId;
    /**
     *
     */
    private String orderNo;
    /**
     *
     */
    private Integer isactive;
    /**
     *
     */
    private Date inserttime;
    /**
     *
     */
    private Date updatetime;


}

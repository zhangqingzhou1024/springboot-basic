package com.liziyuan.hope.domain.query;


import com.liziyuan.hope.common.request.QueryWithOrder;
import lombok.Data;

import java.util.Date;

/**
 * 表:user的查询类
 *
 * @author chenlei
 * @version 1.0
 * @name com.liziyuan.search.answers.query.UserQuery.java
 * @Date 2019-5-18 22:46:35
 * @Description:
 */
@Data
public class UserQuery extends QueryWithOrder {

    /**
     * 序列化标识
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键自增
     */
    private Integer id;
    /**
     * 业务线
     */
    private Integer businessLine;
    /**
     * 人名称
     */
    private String name;
    /**
     * 模糊查询名称
     */
    private String fuzzyUserName;
    /**
     * 人名称
     */
    private String remark;
    /**
     * 是否删除
     */
    private Integer yn;
    /**
     * 版本
     */
    private Integer version;
    /**
     * 时间戳
     */
    private Date ts;


}

package com.liziyuan.hope.domain.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zqz
 * @version V1.0
 * @Description:
 * @date 2019-05-18 23:19
 */
@Data
public class UserDto implements Serializable {
    /** 主键自增 */
    private Integer id;
    /** 业务线 */
    private Integer businessLine;
    /** 人名称 */
    private String name;
    /** 人名称 */
    private String remark;
    /** 是否删除 */
    private Integer yn;
    /** 版本 */
    private Integer version;
    /** 时间戳 */
    private Date ts;
}

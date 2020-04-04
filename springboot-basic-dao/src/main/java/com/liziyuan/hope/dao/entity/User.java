package com.liziyuan.hope.dao.entity;

import java.util.Date;

/**
 * 表：user的实体类
 * 
 * @author zqz
 * @version 1.0
 * @name com.liziyuan.search.answers.entity.User.java
 * @Date 2019-5-18 18:47:25
 * @Description:
 * 
 */
public class User implements java.io.Serializable  {

    /** 序列化标识 */
	private static final long serialVersionUID = 1L;
	
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
    
    public Integer getId(){
        return id;
    }
        
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getBusinessLine(){
        return businessLine;
    }
        
    public void setBusinessLine(Integer businessLine) {
        this.businessLine = businessLine;
    }
    
    public String getName(){
        return name;
    }
        
    public void setName(String name) {
        this.name = name;
    }
    
    public String getRemark(){
        return remark;
    }
        
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    public Integer getYn(){
        return yn;
    }
        
    public void setYn(Integer yn) {
        this.yn = yn;
    }
    
    public Integer getVersion(){
        return version;
    }
        
    public void setVersion(Integer version) {
        this.version = version;
    }
    
    public Date getTs(){
        return ts;
    }
        
    public void setTs(Date ts) {
        this.ts = ts;
    }
}

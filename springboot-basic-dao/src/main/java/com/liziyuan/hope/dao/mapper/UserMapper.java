package com.liziyuan.hope.dao.mapper;


import com.liziyuan.hope.dao.entity.User;
import com.liziyuan.hope.domain.query.UserQuery;

import java.util.List;


/**
 * UserMapper接口
 * 对'用户表'表进行基本的操作
 *
 * @author zqz
 * @version 1.0
 * @Date 2019-5-18 22:46:35
 * @Description:
 */
public interface UserMapper {

    /**
     * 新增对象
     *
     * @param user
     * @return
     */
    boolean insert(User user);

    /**
     * 更新对象
     *
     * @param user
     * @return
     */
    boolean update(User user);

    /**
     * 根据查询Bean获取对象集合，不带翻页
     *
     * @param queryBean
     * @return
     */
    List<User> queryUserList(UserQuery queryBean);

    /**
     * 根据查询Bean获取总数
     *
     * @param queryBean
     * @return
     */
    int queryUserCount(UserQuery queryBean);

    /**
     * 根据查询Bean获取集合，带翻页
     *
     * @param queryBean
     * @return
     */
    List<User> queryUserListWithPage(UserQuery queryBean);

    /**
     * 删除记录
     *
     * @param user
     * @return
     */
    boolean delete(User user);

    /**
     * 根据主键获取对象
     *
     * @param id 主键字段
     * @return
     */
    User getUserById(Long id);

    /**
     * 判断是否存在
     *
     * @param user
     * @return
     */
    int exist(User user);

}

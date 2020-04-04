package com.liziyuan.hope.base;


import com.liziyuan.hope.common.response.PagedListResponse;
import com.liziyuan.hope.dao.entity.User;
import com.liziyuan.hope.domain.query.UserQuery;

import java.util.List;


/**
 * UserBaseService通用接口
 *
 * @author zqz
 * @version 1.0
 * @name com.liziyuan.search.answers.base.UserBaseService.java
 * @Date 2019-5-18 18:47:25
 * @Description:
 */
public interface UserBaseService {

    /**
     * 批量增加对象信息
     *
     * @param userList
     * @return
     */
    boolean insert(List<User> userList);

    /**
     * 单个增加对象信息
     *
     * @param user
     * @return
     */
    boolean insert(User user);

    /**
     * 更新 对象信息
     *
     * @param user 对象信息对象
     * @return false：失败 true：成功
     */
    boolean update(User user);

    /**
     * 根据查询Bean获取对象集合，无翻页
     *
     * @param queryBean
     * @return
     */
    List<User> queryUserList(UserQuery queryBean);

    /**
     * 分页查询供应商列表
     *
     * @param queryBean
     * @return
     */
    PagedListResponse<User> queryUserListWithPage(UserQuery queryBean);


    /**
     * 根据查询Bean获取对象信息总数
     *
     * @param queryBean 对象信息查询对象
     * @return 对象信息总数
     */
    int queryUserCount(UserQuery queryBean);

    /**
     * 根据主键删除对象信息，该处做的是逻辑删除
     *
     * @param user
     * @return
     */
    boolean delete(User user);

    /**
     * 根据主键获取对象信息
     *
     * @param id 主键字段
     * @return 对象信息
     */
    User getUserById(Long id);

    /**
     * 根据主键集合批量删除对象信息，该处做的是逻辑删除
     *
     * @param users User集合
     * @return
     */
    boolean delete(User[] users);

    /**
     * 判断是否存在
     *
     * @param user
     * @return
     */
    boolean exist(User user);


}

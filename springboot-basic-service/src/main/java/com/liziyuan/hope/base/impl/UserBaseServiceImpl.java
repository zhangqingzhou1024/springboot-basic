package com.liziyuan.hope.base.impl;


import com.liziyuan.hope.base.UserBaseService;
import com.liziyuan.hope.common.response.PagedListResponse;
import com.liziyuan.hope.common.response.ResponseUtil;
import com.liziyuan.hope.dao.entity.User;
import com.liziyuan.hope.dao.mapper.UserMapper;
import com.liziyuan.hope.domain.query.UserQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * UserBaseService接口的实现类
 *
 * @author zqz
 * @version 1.0
 * @name com.liziyuan.search.answers.base.impl.UserBaseServiceImpl.java
 * @Date 2019-5-18 18:47:25
 * @Description:
 */
@Service("userBaseService")
public class UserBaseServiceImpl implements UserBaseService {

    @Resource
    private UserMapper userMapper;


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean insert(final List<User> userList) {
        boolean resultFlag = false;
        if (null != userList && userList.size() > 0) {
            for (User user : userList) {
                user.setYn(1);
                resultFlag = userMapper.insert(user);
                if (!resultFlag) {
                    throw new RuntimeException("批量新增表信息异常");
                }
            }
        }

        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean insert(User user) {
        user.setYn(1);
        return userMapper.insert(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(final User user) {
        return userMapper.update(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> queryUserList(UserQuery queryBean) {
        return userMapper.queryUserList(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PagedListResponse<User> queryUserListWithPage(UserQuery queryBean) {
        if (null == queryBean) {
            queryBean = new UserQuery();
        }
        // 查询总数
        Integer totalCount = userMapper.queryUserCount(queryBean);
        List<User> userList = userMapper.queryUserListWithPage(queryBean);
        return ResponseUtil.getPagedResultListResponse(totalCount, queryBean.getPageSize(), queryBean.getCurrentPage(), userList);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public int queryUserCount(UserQuery queryBean) {
        return userMapper.queryUserCount(queryBean);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(User user) {
        return userMapper.delete(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getUserById(Long id) {
        return userMapper.getUserById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(final User[] users) {
        boolean resultFlag = false;
        if (null != users && users.length > 0) {
            for (int i = 0; i < users.length; i++) {
                resultFlag = delete(users[i]);
                if (!resultFlag) {
                    throw new RuntimeException("批量删除表信息异常!");
                }
            }
        }

        return resultFlag;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean exist(User user) {
        return userMapper.exist(user) > 0;
    }

}

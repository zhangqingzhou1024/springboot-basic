package com.liziyuan.hope.rpc.api.user;

import com.liziyuan.hope.common.response.ListDataResponse;
import com.liziyuan.hope.domain.dto.UserDto;
import com.liziyuan.hope.domain.query.UserQuery;

/**
 * 用户信息 对外公布接口
 *
 * @author zqz
 * @version 1.0
 * @date 2020-04-03 14:36
 */
public interface UserInfoRpcService {
    /**
     * 查询满足条件的用户信息列表
     *
     * @param userQuery 查询条件
     * @return 用户信息列表
     */
    ListDataResponse<UserDto> getUsers(UserQuery userQuery);
}

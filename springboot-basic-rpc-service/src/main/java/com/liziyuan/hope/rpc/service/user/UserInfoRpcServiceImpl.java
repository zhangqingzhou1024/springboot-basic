package com.liziyuan.hope.rpc.service.user;

import com.liziyuan.hope.base.UserFacadeService;
import com.liziyuan.hope.common.response.ListDataResponse;
import com.liziyuan.hope.domain.dto.UserDto;
import com.liziyuan.hope.domain.query.UserQuery;
import com.liziyuan.hope.rpc.api.user.UserInfoRpcService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-04-03 14:39
 */
@Service("userInfoRpcService")
public class UserInfoRpcServiceImpl implements UserInfoRpcService {
    @Resource
    private UserFacadeService userFacadeService;

    public ListDataResponse<UserDto> getUsers(UserQuery userQuery) {
        return userFacadeService.getUsers(userQuery);
    }
}

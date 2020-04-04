package com.liziyuan.hope.base;

import com.liziyuan.hope.common.response.ListDataResponse;
import com.liziyuan.hope.domain.dto.UserDto;
import com.liziyuan.hope.domain.query.UserQuery;
import org.springframework.stereotype.Service;

/**
 * @author zqz
 * @version V1.0
 * @Description:
 * @date 2019-05-18 23:23
 */
@Service
public interface UserFacadeService {

     ListDataResponse<UserDto> getUsers(UserQuery userQuery);
}

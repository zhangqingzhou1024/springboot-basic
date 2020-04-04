package com.liziyuan.hope.base.impl;


import com.liziyuan.hope.base.UserBaseService;
import com.liziyuan.hope.base.UserFacadeService;
import com.liziyuan.hope.common.response.ListDataResponse;
import com.liziyuan.hope.common.response.ResponseUtil;
import com.liziyuan.hope.common.utils.json.JsonHelper;
import com.liziyuan.hope.dao.entity.User;
import com.liziyuan.hope.domain.dto.UserDto;
import com.liziyuan.hope.domain.query.UserQuery;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author zqz
 * @version V1.0
 * @Description:
 * @date 2019-05-18 23:25
 */
@Service("userFacadeService")
public class UserFacadeServiceImpl implements UserFacadeService {
    @Resource
    private UserBaseService userBaseService;

    @Override
    public ListDataResponse<UserDto> getUsers(UserQuery userQuery) {

        if (null == userQuery) {
            userQuery = new UserQuery();
        }
        List<User> users = userBaseService.queryUserList(userQuery);
        if (CollectionUtils.isEmpty(users)) {

            return ResponseUtil.getSuccessResultListResponse(Collections.emptyList());
        }

        List<UserDto> dataList = new ArrayList<>();
        users.forEach(user -> {

            UserDto userDto = this.convertModelToDto(user);

            dataList.add(userDto);
        });
        return ResponseUtil.getSuccessResultListResponse(dataList);
    }


    /**
     * 实体类转换成dto
     *
     * @param model 实体类
     * @return dto
     */
    private UserDto convertModelToDto(User model) {
        UserDto dto = JsonHelper.toBean(JsonHelper.toJson(model), UserDto.class);
        return dto;
    }

}

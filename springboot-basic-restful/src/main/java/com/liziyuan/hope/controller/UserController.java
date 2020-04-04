package com.liziyuan.hope.controller;

import com.liziyuan.hope.base.UserFacadeService;
import com.liziyuan.hope.common.response.ListDataResponse;
import com.liziyuan.hope.domain.dto.UserDto;
import com.liziyuan.hope.domain.query.UserQuery;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * controller 层
 *
 * @author zqz
 * @version V1.0
 * @Description:
 * @date 2019-04-30 14:49
 */
@Api(value = "用户接口", tags = {"用户接口"}, description = "用户接口Restful接口")
@RestController
@RequestMapping(value = "/user", produces = "application/json; charset=utf-8")
public class UserController {

    @Resource
    private UserFacadeService userFacadeService;

    @GetMapping("/getUsers")
    public ListDataResponse<UserDto> getUsers(UserQuery userQuery) {
        return userFacadeService.getUsers(userQuery);

    }


}

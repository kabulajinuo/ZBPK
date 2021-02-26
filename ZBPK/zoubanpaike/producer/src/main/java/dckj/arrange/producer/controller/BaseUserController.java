package dckj.arrange.producer.controller;

import dckj.arrange.common.base.ApiBaseController;
import dckj.arrange.common.base.ApiResult;
import dckj.arrange.common.base.ErrorCode;
import dckj.arrange.common.entity.BaseUser;
import dckj.arrange.common.exception.CheckedException;
import dckj.arrange.common.model.response.BaseUserRes;
import dckj.arrange.producer.service.BaseUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname BaseUserController
 * @Description TODO
 * @Date 2019/8/13 16:47
 * @Created by JinPeng
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/baseUserController")
@Api(value = "/baseUserController", description = "用户资源Controller")
@Slf4j
public class BaseUserController extends ApiBaseController {


    @Autowired
    private BaseUserService baseUserService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "登录", notes = "登录", httpMethod = "POST")
    public ApiResult login(@RequestBody BaseUser user){
        try {
            BaseUserRes bu = baseUserService.login(user);
            return success("/baseUserController/login", ApiResult.SUCCESS_MESSAGE, bu);

        }catch (Exception e){
            log.info(e.getMessage());
            if (e instanceof CheckedException) {
                return error("/baseUserController/login", e.getMessage());
            }
        }
        return error("/baseUserController/login", ErrorCode.ERROR_SYSTEM);
    }


}

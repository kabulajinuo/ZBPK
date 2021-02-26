package dckj.arrange.auth.controller;

import dckj.arrange.auth.feign.TestService;
import dckj.arrange.common.base.ApiBaseController;
import dckj.arrange.common.base.ApiResult;
import dckj.arrange.common.entity.BaseUser;
import dckj.arrange.common.entity.PkClass;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname TestController
 * @Description 测试类
 * @Date 2019/8/8 9:29
 * @Created by JinPeng
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/testController")
@Api(value = "/testController", description = "测试")
@Slf4j
public class TestController extends ApiBaseController{

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/test",method = RequestMethod.POST)
    @ApiOperation(value = "测试", notes = "测试", httpMethod = "POST")
    public ApiResult test(@RequestBody BaseUser user, @RequestBody PkClass param) {

        ApiResult r = testService.test();

        return r;
    }

}

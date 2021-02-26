package dckj.arrange.producer.controller;

import dckj.arrange.common.base.ApiBaseController;
import dckj.arrange.common.base.ApiResult;
import dckj.arrange.common.base.ErrorCode;
import dckj.arrange.common.exception.CheckedException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname TestController
 * @Description TODO
 * @Date 2019/8/7 16:35
 * @Created by JinPeng
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/testController")
@Api(value = "/testController", description = "测试")
@Slf4j
public class TestController extends ApiBaseController {

    @RequestMapping(value = "/test",method = RequestMethod.POST)
    @ApiOperation(value = "测试", notes = "测试", httpMethod = "POST")
    public ApiResult test(){

        try {
            return success("/testController/test", ApiResult.SUCCESS_MESSAGE);

        }catch (Exception e){
            if (e instanceof CheckedException) {
                log.info(e.getMessage());
                return error("/testController/test", e.getMessage());
            }
        }
        return error("/testController/test", ErrorCode.ERROR_SYSTEM);
    }

}

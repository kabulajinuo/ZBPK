package dckj.arrange.auth.feign;

import dckj.arrange.auth.feign.fallback.BaseUserServiceImpl;
import dckj.arrange.common.base.ApiResult;
import dckj.arrange.common.entity.BaseUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Classname BaseUserService
 * @Description TODO
 * @Date 2019/8/13 17:57
 * @Created by JinPeng
 * @Version 1.0
 */
@FeignClient(name = "zbpk-producer-service", fallback = BaseUserServiceImpl.class)
public interface BaseUserService {

    @PostMapping("/baseUserController/login")
    ApiResult login(@RequestBody BaseUser user);

}

package dckj.arrange.auth.feign;

import dckj.arrange.auth.feign.fallback.TestServiceImpl;
import dckj.arrange.common.base.ApiResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Classname TestService
 * @Description TODO
 * @Date 2019/8/8 11:29
 * @Created by JinPeng
 * @Version 1.0
 */
@FeignClient(name = "zbpk-producer-service", fallback = TestServiceImpl.class)
public interface TestService {

    @PostMapping("/testController/test")
    ApiResult test();

}

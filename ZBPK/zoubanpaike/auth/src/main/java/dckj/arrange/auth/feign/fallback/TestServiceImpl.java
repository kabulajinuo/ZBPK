package dckj.arrange.auth.feign.fallback;

import dckj.arrange.auth.feign.TestService;
import dckj.arrange.common.base.ApiResult;
import org.springframework.stereotype.Service;

/**
 * @Classname TestServiceImpl
 * @Description TODO
 * @Date 2019/8/8 11:31
 * @Created by JinPeng
 * @Version 1.0
 */
@Service
public class TestServiceImpl implements TestService {

    @Override
    public ApiResult test() {
        return null;
    }
}

package dckj.arrange.auth.feign.fallback;

import dckj.arrange.auth.feign.BaseUserService;
import dckj.arrange.common.base.ApiResult;
import dckj.arrange.common.entity.BaseUser;
import org.springframework.stereotype.Service;

/**
 * @Classname BaseUserServiceImpl
 * @Description TODO
 * @Date 2019/8/13 17:57
 * @Created by JinPeng
 * @Version 1.0
 */
@Service
public class BaseUserServiceImpl implements BaseUserService {

    @Override
    public ApiResult login(BaseUser user) {
        return null;
    }

}

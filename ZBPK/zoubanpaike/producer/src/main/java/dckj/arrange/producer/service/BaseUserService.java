package dckj.arrange.producer.service;

import dckj.arrange.common.entity.BaseUser;
import dckj.arrange.common.model.response.BaseUserRes;

/**
 * @Classname BaseUserService
 * @Description TODO
 * @Date 2019/8/13 16:48
 * @Created by JinPeng
 * @Version 1.0
 */
public interface BaseUserService {

    BaseUserRes login(BaseUser user);
}

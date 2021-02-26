package dckj.arrange.common.model.response;

import dckj.arrange.common.entity.BaseUser;
import lombok.Data;

/**
 * @Classname BaseUserRes
 * @Description TODO
 * @Date 2019/8/18 16:17
 * @Created by JinPeng
 * @Version 1.0
 */
@Data
public class BaseUserRes extends BaseUser {

    private String schoolCode;

}

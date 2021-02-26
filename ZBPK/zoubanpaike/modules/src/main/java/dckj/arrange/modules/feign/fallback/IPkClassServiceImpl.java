package dckj.arrange.modules.feign.fallback;

import dckj.arrange.common.base.ApiResult;
import dckj.arrange.common.entity.PkClass;
import dckj.arrange.common.model.request.PkClassListReq;
import dckj.arrange.modules.feign.IPkClassService;
import org.springframework.stereotype.Service;

/**
 * @Classname IPkClassServiceImpl
 * @Description TODO
 * @Date 2019/8/17 13:21
 * @Created by JinPeng
 * @Version 1.0
 */
@Service
public class IPkClassServiceImpl implements IPkClassService {

    @Override
    public ApiResult saveClass(PkClass param) {
        return null;
    }

    @Override
    public ApiResult getClassList(PkClassListReq param) {
        return null;
    }

    @Override
    public ApiResult delClass(PkClass param) {
        return null;
    }

    @Override
    public ApiResult updateClass(PkClass param) {
        return null;
    }
}

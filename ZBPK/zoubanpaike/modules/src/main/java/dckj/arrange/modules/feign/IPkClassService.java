package dckj.arrange.modules.feign;

import dckj.arrange.common.base.ApiResult;
import dckj.arrange.common.entity.PkClass;
import dckj.arrange.common.model.request.PkClassListReq;
import dckj.arrange.modules.feign.fallback.IPkClassServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 班级管理模块
 */
@FeignClient(name = "zbpk-producer-service", fallback = IPkClassServiceImpl.class)
public interface IPkClassService {

    @PostMapping("/pkClass/saveClass")
    ApiResult saveClass(@RequestBody PkClass param);

    @PostMapping("/pkClass/getClassList")
    ApiResult getClassList(@RequestBody PkClassListReq param);

    @PostMapping("/pkClass/delClass")
    ApiResult delClass(@RequestBody PkClass param);

    @PostMapping("/pkClass/updateClass")
    ApiResult updateClass(@RequestBody PkClass param);

}

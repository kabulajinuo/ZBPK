package dckj.arrange.modules.feign;

import dckj.arrange.common.base.ApiResult;
import dckj.arrange.common.entity.PkCurriculumSet;
import dckj.arrange.common.model.request.BaseReq;
import dckj.arrange.modules.feign.fallback.PkCurriculumSetServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * <p>
 * 课程设置表 服务类
 * </p>
 *
 * @author Xiangjing
 * @since 2019-08-17
 */
@FeignClient(name = "zbpk-producer-service", fallback = PkCurriculumSetServiceImpl.class)
public interface IPkCurriculumSetService {

    @PostMapping("/pkCurriculumSet/getCurriculumSetList")
    ApiResult getCurriculumSetList(@RequestBody BaseReq param);

    @PostMapping("/pkCurriculumSet/setCurriculumSetList")
    ApiResult setCurriculumSetList(@RequestBody List<PkCurriculumSet> param);



}

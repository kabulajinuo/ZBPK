package dckj.arrange.modules.feign;

import dckj.arrange.common.base.ApiResult;
import dckj.arrange.common.entity.PkTeachingTask;
import dckj.arrange.common.model.request.BaseReq;
import dckj.arrange.common.model.request.PkTeachingTaskReq;
import dckj.arrange.modules.feign.fallback.PkTeachingTaskServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * <p>
 * 教学任务表 服务类
 * </p>
 *
 * @author Xiangjing
 * @since 2019-08-17
 */
@FeignClient(name = "zbpk-producer-service", fallback = PkTeachingTaskServiceImpl.class)
public interface IPkTeachingTaskService {

    @PostMapping("/pkTeachingTask/getTeachingTaskList")
    ApiResult getTeachingTaskList(@RequestBody BaseReq param);

    @PostMapping("/pkTeachingTask/addTeachingTask")
    ApiResult addTeachingTask(@RequestBody PkTeachingTaskReq param);

    @PostMapping("/pkTeachingTask/updateTeachingTask")
    ApiResult updateTeachingTask(@RequestBody List<PkTeachingTaskReq> param);

    @PostMapping("/pkTeachingTask/delTeachingTask")
    ApiResult delTeachingTask(@RequestBody PkTeachingTask param);

}

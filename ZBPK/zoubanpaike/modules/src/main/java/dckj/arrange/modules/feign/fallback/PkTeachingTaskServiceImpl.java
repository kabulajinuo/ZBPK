package dckj.arrange.modules.feign.fallback;

import dckj.arrange.common.base.ApiResult;
import dckj.arrange.common.entity.PkTeachingTask;
import dckj.arrange.common.model.request.BaseReq;
import dckj.arrange.common.model.request.PkTeachingTaskReq;
import dckj.arrange.modules.feign.IPkTeachingTaskService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 教学任务表 服务实现类
 * </p>
 *
 * @author Xiangjing
 * @since 2019-08-17
 */
@Service
public class PkTeachingTaskServiceImpl implements IPkTeachingTaskService {

    @Override
    public ApiResult getTeachingTaskList(BaseReq param) {
        return null;
    }

    @Override
    public ApiResult addTeachingTask(PkTeachingTaskReq param) {
        return null;
    }

    @Override
    public ApiResult updateTeachingTask(List<PkTeachingTaskReq> param) {
        return null;
    }

    @Override
    public ApiResult delTeachingTask(PkTeachingTask param) {
        return null;
    }
}

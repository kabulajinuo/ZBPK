package dckj.arrange.modules.feign.fallback;


import dckj.arrange.common.base.ApiResult;
import dckj.arrange.common.entity.PkTask;
import dckj.arrange.common.model.request.PkTaskCurriculumSetVo;
import dckj.arrange.common.model.request.PkTaskReq;
import dckj.arrange.modules.feign.IPkTaskService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Description: IPkTaskService
 * @Author: jiangyong
 * @CreateDate: 2019/8/17 17:30
 * @Version: 1.0
 */
@Service
public class IPkTaskServiceImpl  implements IPkTaskService {


    @Override
    public ApiResult InsertTask(PkTaskCurriculumSetVo pkTaskCurriculumSetVo) {
        return null;
    }

    @Override
    public ApiResult SelectPkTask(@RequestBody PkTaskReq pkTaskReq) {
        return null;
    }

    @Override
    public ApiResult deletePkTask(@RequestBody PkTask pkTask) {
        return null;
    }
}

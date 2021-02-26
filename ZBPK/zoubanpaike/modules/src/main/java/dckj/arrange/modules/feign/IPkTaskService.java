package dckj.arrange.modules.feign;


import dckj.arrange.common.base.ApiResult;
import dckj.arrange.common.entity.PkTask;
import dckj.arrange.common.model.request.PkTaskCurriculumSetVo;
import dckj.arrange.common.model.request.PkTaskReq;
import dckj.arrange.modules.feign.fallback.IPkTaskServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description: IPkTaskService
 * @Author: jiangyong
 * @CreateDate: 2019/8/17 17:31
 * @Version: 1.0
 */
@FeignClient(name = "zbpk-producer-service", fallback = IPkTaskServiceImpl.class)
public interface IPkTaskService {

    @RequestMapping(value="/pkTask/InsetPkTask",method ={RequestMethod.POST} )
    ApiResult InsertTask(@RequestBody PkTaskCurriculumSetVo pkTaskCurriculumSetVo);


    @RequestMapping(value="/pkTask/SelectPkTask",method ={RequestMethod.POST})
    ApiResult SelectPkTask(@RequestBody PkTaskReq pkTaskReq);

    @RequestMapping(value="/pkTask/deletePkTask",method ={RequestMethod.POST})
    ApiResult deletePkTask(@RequestBody PkTask pkTask) ;
}

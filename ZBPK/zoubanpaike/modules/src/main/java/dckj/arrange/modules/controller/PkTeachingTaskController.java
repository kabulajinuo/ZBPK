package dckj.arrange.modules.controller;


import dckj.arrange.common.base.ApiBaseController;
import dckj.arrange.common.base.ApiResult;
import dckj.arrange.common.base.ErrorCode;
import dckj.arrange.common.entity.PkTeachingTask;
import dckj.arrange.common.model.request.BaseReq;
import dckj.arrange.common.model.request.PkTeachingTaskReq;
import dckj.arrange.common.util.EmptyUtil;
import dckj.arrange.modules.feign.IPkTeachingTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 教学任务表 前端控制器
 * </p>
 *
 * @author Xiangjing
 * @since 2019-08-17
 */
@RestController
@RequestMapping("/pkTeachingTask")
@Api(value = "/pkTeachingTask", description = "教学任务表")
@Slf4j
public class PkTeachingTaskController extends ApiBaseController {

    @Autowired
    private IPkTeachingTaskService iPkTeachingTaskService;

    /**
     * 获取教学任务列表
     * @param param
     * @return
     */
    @RequestMapping(value = "/getTeachingTaskList",method = RequestMethod.POST)
    @ApiOperation(value = "获取教学任务列表", notes = "获取教学任务列表：subjectId（非必传）, taskId(任务id)", httpMethod = "POST")
    public ApiResult getTeachingTaskList(@RequestBody BaseReq param){
        if(EmptyUtil.isEmpty(param) || EmptyUtil.isEmpty(param.getSubjectId()) || EmptyUtil.isEmpty(param.getTaskId())){
            return error("/pkCurriculumSet/getTeachingTaskList", ErrorCode.ERROR_MISS_PARAM);
        }
        return iPkTeachingTaskService.getTeachingTaskList(param);
    }


    /**
     * 新增教学任务
     * @param param
     * @return
     */
    @RequestMapping(value = "/addTeachingTask",method = RequestMethod.POST)
    @ApiOperation(value = "新增教学任务", notes = "新增教学任务：classIds-->id, className", httpMethod = "POST")
    public ApiResult addTeachingTask(@RequestBody PkTeachingTaskReq param){
        if(EmptyUtil.isEmpty(param)){
            return error("/pkCurriculumSet/addTeachingTask", ErrorCode.ERROR_MISS_PARAM);
        }
        return iPkTeachingTaskService.addTeachingTask(param);
    }


    /**
     * 修改教学任务
     * @param param
     * @return
     */
    @RequestMapping(value = "/updateTeachingTask",method = RequestMethod.POST)
    @ApiOperation(value = "修改教学任务", notes = "修改教学任务：id，taskId, teacherId， teacherName， mustNumber， choiceNumber, classIds", httpMethod = "POST")
    public ApiResult updateTeachingTask(@RequestBody List<PkTeachingTaskReq> param){
        if(EmptyUtil.isEmpty(param)){
            return error("/pkCurriculumSet/updateTeachingTask", ErrorCode.ERROR_MISS_PARAM);
        }
        return iPkTeachingTaskService.updateTeachingTask(param);
    }


    /**
     * 删除教学任务
     * @param param
     * @return
     */
    @RequestMapping(value = "/delTeachingTask",method = RequestMethod.POST)
    @ApiOperation(value = "删除教学任务", notes = "删除教学任务：id", httpMethod = "POST")
    public ApiResult delTeachingTask(@RequestBody PkTeachingTask param){
        if(EmptyUtil.isEmpty(param)){
            return error("/pkCurriculumSet/delTeachingTask", ErrorCode.ERROR_MISS_PARAM);
        }
        return iPkTeachingTaskService.delTeachingTask(param);
    }

}


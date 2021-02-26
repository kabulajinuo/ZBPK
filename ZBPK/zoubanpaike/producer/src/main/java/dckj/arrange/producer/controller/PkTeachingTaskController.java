package dckj.arrange.producer.controller;


import dckj.arrange.common.base.ApiBaseController;
import dckj.arrange.common.base.ApiResult;
import dckj.arrange.common.base.ErrorCode;
import dckj.arrange.common.entity.PkTeachingTask;
import dckj.arrange.common.exception.CheckedException;
import dckj.arrange.common.model.request.BaseReq;
import dckj.arrange.common.model.request.PkTeachingTaskReq;
import dckj.arrange.common.model.request.TeachingTaskListReq;
import dckj.arrange.producer.service.IPkTeachingTaskService;
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
    @ApiOperation(value = "获取教学任务列表", notes = "获取教学任务列表： subjectId,taskId(任务id)", httpMethod = "POST")
    public ApiResult getTeachingTaskList(@RequestBody BaseReq param){

        try {
            TeachingTaskListReq list = iPkTeachingTaskService.getTeachingTaskList(param);

            return success("/pkTeachingTask/getTeachingTaskList", ApiResult.SUCCESS_MESSAGE, list);

        }catch (Exception e){
            log.info(e.getMessage());
            if (e instanceof CheckedException) {
                return error("/pkTeachingTask/getTeachingTaskList", e.getMessage());
            }
        }
        return error("/pkTeachingTask/getTeachingTaskList", ErrorCode.ERROR_SYSTEM);
    }


    /**
     * 新增教学任务
     * @param param
     * @return
     */
    @RequestMapping(value = "/addTeachingTask",method = RequestMethod.POST)
    @ApiOperation(value = "新增教学任务", notes = "新增教学任务：", httpMethod = "POST")
    public ApiResult addTeachingTask(@RequestBody PkTeachingTaskReq param){

        try {
            iPkTeachingTaskService.addTeachingTask(param);

            return success("/pkTeachingTask/addTeachingTask", ApiResult.SUCCESS_MESSAGE);

        }catch (Exception e){
            log.info(e.getMessage());
            if (e instanceof CheckedException) {
                return error("/pkTeachingTask/addTeachingTask", e.getMessage());
            }
        }
        return error("/pkTeachingTask/addTeachingTask", ErrorCode.ERROR_SYSTEM);
    }


    /**
     * 修改教学任务
     * @param param
     * @return
     */
    @RequestMapping(value = "/updateTeachingTask",method = RequestMethod.POST)
    @ApiOperation(value = "修改教学任务", notes = "修改教学任务：id， teacherId， teacherName， mustNumber， choiceNumber, classIds", httpMethod = "POST")
    public ApiResult updateTeachingTask(@RequestBody List<PkTeachingTaskReq> param){

        try {
            iPkTeachingTaskService.updateTeachingTask(param);

            return success("/pkTeachingTask/updateTeachingTask", ApiResult.SUCCESS_MESSAGE);

        }catch (Exception e){
            log.info(e.getMessage());
            if (e instanceof CheckedException) {
                return error("/pkTeachingTask/updateTeachingTask", e.getMessage());
            }
        }
        return error("/pkTeachingTask/updateTeachingTask", ErrorCode.ERROR_SYSTEM);
    }


    /**
     * 删除教学任务
     * @param param
     * @return
     */
    @RequestMapping(value = "/delTeachingTask",method = RequestMethod.POST)
    @ApiOperation(value = "删除教学任务", notes = "删除教学任务：id", httpMethod = "POST")
    public ApiResult delTeachingTask(@RequestBody PkTeachingTask param){
        try {
            iPkTeachingTaskService.delTeachingTask(param);

            return success("/pkTeachingTask/delTeachingTask", ApiResult.SUCCESS_MESSAGE);

        }catch (Exception e){
            log.info(e.getMessage());
            if (e instanceof CheckedException) {
                return error("/pkTeachingTask/delTeachingTask", e.getMessage());
            }
        }
        return error("/pkTeachingTask/delTeachingTask", ErrorCode.ERROR_SYSTEM);
    }


}


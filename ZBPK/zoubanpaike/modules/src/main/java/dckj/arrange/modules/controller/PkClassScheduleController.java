package dckj.arrange.modules.controller;

import dckj.arrange.common.base.ApiBaseController;
import dckj.arrange.common.base.ApiResult;
import dckj.arrange.common.base.ErrorCode;
import dckj.arrange.common.entity.PkClassSchedule;
import dckj.arrange.common.model.request.BaseReq;
import dckj.arrange.common.model.request.PkClassSchedulereq;
import dckj.arrange.common.model.request.SelectClassSubjectTeaReq;
import dckj.arrange.common.util.EmptyUtil;
import dckj.arrange.modules.feign.IPkClassScheduleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 课程预设
 * @Author: jiangyong
 * @CreateDate: 2019/8/19 20:41
 * @Version: 1.0
 */

@RestController
@RequestMapping(value = "/classSchedule")
@Slf4j
@Api(value = "/classSchedule", description = "课程预设")
public class PkClassScheduleController extends ApiBaseController {

    @Autowired
    private IPkClassScheduleService iPkClassScheduleService;


    @RequestMapping(value = "/selectClassSubjectTea",method = RequestMethod.POST)
    @ApiOperation(value = "查询左侧菜单 相关信息",notes = "查询左侧菜单 相关信息 传入taskId，subjectName")
    public ApiResult selectClassSubjectTea(@RequestBody SelectClassSubjectTeaReq selectClassSubjectTeaReq){
        Map<String,Object> map=new HashMap<>();
        map.put("taskId",selectClassSubjectTeaReq.getTaskId());
        map.put("subjectName",selectClassSubjectTeaReq.getSubjectName());
        ApiResult result = iPkClassScheduleService.selectClassSubjectTea(map);
        return  result;
    }


    @RequestMapping(value = "/insertClassSchedule",method = RequestMethod.POST)
    @ApiOperation(value = "新增课程预设相关信息",notes = "我TM 就坐你旁边 参数不懂就问啊")
    public ApiResult insertClassSchedule(@RequestBody List<PkClassSchedulereq> pkClassSchedules){
        ApiResult result = iPkClassScheduleService.insertClassSchedule(pkClassSchedules);
        return result;
    }


    @RequestMapping(value = "/deleteClassSchedule",method = RequestMethod.POST)
    @ApiOperation(value = "删除课程预设相关信息",notes = "我TM 就坐你旁边 参数不懂就问啊")
    public ApiResult deleteClassSchedule(@RequestBody PkClassSchedulereq pkClassSchedules){
        ApiResult result = iPkClassScheduleService.deleteClassSchedule(pkClassSchedules);
        return result;
    }



    @RequestMapping(value = "/selectClassSchedule",method = RequestMethod.POST)
    @ApiOperation(value = "查询课程预设",notes = "查询课程预设 传入taskId 其他参数干掉")
    public ApiResult selectClassSchedule(@RequestBody PkClassSchedule pkClassSchedules) {
        ApiResult result = iPkClassScheduleService.selectClassSchedule(pkClassSchedules);
        return result;
    }

    @RequestMapping(value = "/selectCurriculumNum",method = RequestMethod.POST)
    @ApiOperation(value = "查询课程节次",notes = "查询课程预设 传入taskId 其他参数干掉")
    public ApiResult selectCurriculumNum(@RequestBody PkClassSchedule pkClassSchedules) {
        ApiResult result = iPkClassScheduleService.selectCurriculumNum(pkClassSchedules);
        return result;
    }


    /**
     * 生成课表
     * @param param
     * @return
     */
    @RequestMapping(value = "/createClassSchedule",method = RequestMethod.POST)
    @ApiOperation(value = "生成课表", notes = "生成课表：taskId(任务id)", httpMethod = "POST")
    public ApiResult createClassSchedule(@RequestBody BaseReq param){
        if (EmptyUtil.isEmpty(param) || EmptyUtil.isEmpty(param.getTaskId())) {
            return error("/classSchedule/createClassSchedule", ErrorCode.ERROR_MISS_PARAM);
        }

        return iPkClassScheduleService.createClassSchedule(param);
    }


    /**
     * 获取总课表
     * @param param
     * @return
     */
    @RequestMapping(value = "/getAllSchedule",method = RequestMethod.POST)
    @ApiOperation(value = "获取总课表", notes = "获取总课表：taskId(任务id)", httpMethod = "POST")
    public ApiResult getAllSchedule(@RequestBody BaseReq param){
        if (EmptyUtil.isEmpty(param) || EmptyUtil.isEmpty(param.getTaskId())) {
            return error("/classSchedule/getAllSchedule", ErrorCode.ERROR_MISS_PARAM);
        }
        ApiResult r = new ApiResult();
        try {
            r = iPkClassScheduleService.getAllSchedule(param);
            return r;
        }catch (Exception e) {
            log.info(e.getMessage());
        }
        return r;
    }


    /**
     * 获取班级课表
     * @param param
     * @return
     */
    @RequestMapping(value = "/getClassSchedule",method = RequestMethod.POST)
    @ApiOperation(value = "获取班级课表", notes = "获取班级课表：taskId(任务id)，classId", httpMethod = "POST")
    public ApiResult getClassSchedule(@RequestBody BaseReq param){
        if (EmptyUtil.isEmpty(param) || EmptyUtil.isEmpty(param.getTaskId()) || EmptyUtil.isEmpty(param.getClassId())) {
            return error("/classSchedule/getClassSchedule", ErrorCode.ERROR_MISS_PARAM);
        }
        return iPkClassScheduleService.getClassSchedule(param);
    }
}

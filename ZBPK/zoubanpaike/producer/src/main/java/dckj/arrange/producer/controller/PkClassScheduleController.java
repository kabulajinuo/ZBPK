package dckj.arrange.producer.controller;

import dckj.arrange.common.base.ApiBaseController;
import dckj.arrange.common.base.ApiResult;
import dckj.arrange.common.base.ErrorCode;
import dckj.arrange.common.entity.PkClassSchedule;
import dckj.arrange.common.entity.PkCurriculumPreset;
import dckj.arrange.common.exception.CheckedException;
import dckj.arrange.common.model.request.BaseReq;
import dckj.arrange.common.model.request.PkClassSchedulereq;
import dckj.arrange.common.model.request.SelectClassSubjectTea;
import dckj.arrange.common.model.request.SelectClassSubjectTeaReq;
import dckj.arrange.common.model.response.AllClassScheduleRes;
import dckj.arrange.common.model.response.ClassScheduleRes;
import dckj.arrange.producer.service.IPkClassScheduleService;
import dckj.arrange.producer.service.IPkCurriculumPresetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.LinkedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

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

    @Autowired
    private IPkCurriculumPresetService iPkCurriculumPresetService;

    @RequestMapping(value = "/selectClassSubjectTea",method = RequestMethod.POST)
    public ApiResult selectClassSubjectTea(@RequestBody SelectClassSubjectTeaReq selectClassSubjectTeaReq){
        Map<String,Object> map=new HashMap<>();
        map.put("taskId",selectClassSubjectTeaReq.getTaskId());
        map.put("subjectName",selectClassSubjectTeaReq.getSubjectName());
        Map<String,Object> objectMap =new HashMap<>();
        objectMap.put("task_id",selectClassSubjectTeaReq.getTaskId());
        objectMap.put("subject_name",selectClassSubjectTeaReq.getSubjectName());
        List<SelectClassSubjectTea> selectClassSubjectTea = iPkClassScheduleService.selectClassSubjectTea(map);
        for (SelectClassSubjectTea tea : selectClassSubjectTea) {
            Map<String,Object> m=new HashMap<>();
            m.put("id",tea.getId());
            List<PkCurriculumPreset> pkCurriculumPresetss = iPkCurriculumPresetService.selectByMap(m);
                if (pkCurriculumPresetss.size()==0) {
                    Map<String, Object> map1 = new HashMap<>();
                    map1.put("teacherId", tea.getTeacherId());
                    map1.put("taskId", map.get("taskId"));
                    map1.put("subjectName", map.get("subjectName"));
                    List<SelectClassSubjectTea> selectClassSubjectTeas1 = iPkClassScheduleService.selectTeacherNum(map1);
                    tea.setTeacheaNum(selectClassSubjectTeas1.size());
                    tea.setPCountClassSkNum(0);
                    tea.setPTeacheaNum(0);
                    PkCurriculumPreset pkCurriculumPreset = new PkCurriculumPreset();
                    pkCurriculumPreset.setId(tea.getId());
                    pkCurriculumPreset.setTaskId(tea.getTaskId());
                    pkCurriculumPreset.setSubjectId(tea.getSubjectId());
                    pkCurriculumPreset.setSubjectName(tea.getSubjectName());
                    pkCurriculumPreset.setTeacherId(tea.getTeacherId());
                    pkCurriculumPreset.setTeacherName(tea.getTeacherName());
                    pkCurriculumPreset.setClassId(tea.getClassId());
                    pkCurriculumPreset.setClassName(tea.getClassName());
                    pkCurriculumPreset.setGreadeId(tea.getGradeId());
                    pkCurriculumPreset.setGreadeName(tea.getGradeName());
                    pkCurriculumPreset.setCountClassSkNum(tea.getCountClassSkNum());
                    pkCurriculumPreset.setpCountClassSkNum(tea.getPCountClassSkNum());
                    pkCurriculumPreset.setTeacheaNum(tea.getTeacheaNum());
                    pkCurriculumPreset.setpTeacheaNum(tea.getPTeacheaNum());
                    pkCurriculumPreset.setCreateTime(new Date());
                    iPkCurriculumPresetService.insert(pkCurriculumPreset);
                }
        }
        List<PkCurriculumPreset> pkCurriculumPresets1 = iPkCurriculumPresetService.selectByMap(objectMap);
        return success("/classSchedule/selectClassSubjectTea",ApiResult.SUCCESS_MESSAGE,pkCurriculumPresets1);
    }


    @RequestMapping(value = "/insertClassSchedule",method = RequestMethod.POST)
    public ApiResult insertClassSchedule(@RequestBody List<PkClassSchedulereq> pkClassSchedules){
        try {
            iPkClassScheduleService.insertClassSchedule(pkClassSchedules);
            return  success("/classSchedule/insertClassSchedule",ApiResult.SUCCESS_MESSAGE);
        }catch (Exception e){
            if (e instanceof CheckedException) {
                return error("/classSchedule/insertClassSchedule", e.getMessage());
            }
        }
        return  error("/classSchedule/insertClassSchedule",ErrorCode.ERROR_SYSTEM);
    }


    @RequestMapping(value = "/deleteClassSchedule",method = RequestMethod.POST)
    public ApiResult deleteClassSchedule(@RequestBody PkClassSchedulereq pkClassSchedules){
        try {
            iPkClassScheduleService.deleteClassSchedule(pkClassSchedules);
            return  success("/classSchedule/deleteClassSchedule",ApiResult.SUCCESS_MESSAGE);
        }catch (Exception e){
            if (e instanceof CheckedException) {
                return error("/classSchedule/deleteClassSchedule", e.getMessage());
            }
        }
        return  error("/classSchedule/deleteClassSchedule",ErrorCode.ERROR_SYSTEM);
    }



    @RequestMapping(value = "/selectClassSchedule",method = RequestMethod.POST)
    public ApiResult selectClassSchedule(@RequestBody PkClassSchedule pkClassSchedules) {
        Map<String, Object> map = new HashMap<>();
        map.put("taskId", pkClassSchedules.getTaskId());
        List<PkClassSchedule> pkClassSchedules1 = iPkClassScheduleService.selectClassSchedule(map);

        if (pkClassSchedules1.size() == 0) {
            return error("/classSchedule/selectClassSchedule", "查询数据为空  请自行创建！");
        }
        LinkedMap<Object, LinkedMap<Object,List<Object>>> maps = new LinkedMap<>();
        for (PkClassSchedule s : pkClassSchedules1){
            // map是否包含此key，若已经包含则添加一个新的数组到对应value集合中
            if (maps.containsKey(s.getSection())){
                if(maps.get(s.getSection()).containsKey(s.getWeek())){
                    maps.get(s.getSection()).get(s.getWeek()).add(s);
                }else{
                    List<Object> scoreList = new ArrayList<>();
                    scoreList.add(s);
                    maps.get(s.getSection()).put(s.getWeek(),scoreList);
                }
            }else {
                // map不包含此key，则重新创建一个新集合，并把这个数组添加进集合
                // ，再把集合放到map中
                LinkedMap<Object,List<Object>> mapSchool = new LinkedMap<>();
                List<Object> scoreList = new ArrayList<>();
                scoreList.add(s.getWeek());
                mapSchool.put(s.getWeek(),scoreList);
                maps.put(s.getSection(), mapSchool);
            }
        }
        return success("/classSchedule/selectClassSchedule",ApiResult.SUCCESS_MESSAGE,maps);
    }

    @RequestMapping(value = "/selectCurriculumNum",method = RequestMethod.POST)
    public ApiResult selectCurriculumNum(@RequestBody PkClassSchedule pkClassSchedules) {
        Map<String, Object> map = new HashMap<>();
        map.put("taskId", pkClassSchedules.getTaskId());
        List<PkClassSchedule> pkClassSchedules1 = iPkClassScheduleService.selectCurriculumNum(map);
        return  success("/classSchedule/deleteClassSchedule",ApiResult.SUCCESS_MESSAGE,pkClassSchedules1);

    }


    /**
     * 生成课表
     * @param param
     * @return
     */
    @RequestMapping(value = "/createClassSchedule",method = RequestMethod.POST)
    @ApiOperation(value = "生成课表", notes = "生成课表：taskId(任务id)", httpMethod = "POST")
    public ApiResult createClassSchedule(@RequestBody BaseReq param){

        try {

            iPkClassScheduleService.createClassSchedule(param);

            return success("/pkTeachingTask/getTeachingTaskList", ApiResult.SUCCESS_MESSAGE);

        }catch (Exception e){
            log.info(e.getMessage());
            if (e instanceof CheckedException) {
                return error("/pkTeachingTask/getTeachingTaskList", e.getMessage());
            }
        }
        return error("/pkTeachingTask/getTeachingTaskList", ErrorCode.ERROR_SYSTEM);
    }

    /**
     * 获取总课表
     * @param param
     * @return
     */
    @RequestMapping(value = "/getAllSchedule",method = RequestMethod.POST)
    @ApiOperation(value = "获取总课表", notes = "获取总课表：taskId(任务id)", httpMethod = "POST")
    public ApiResult getAllSchedule(@RequestBody BaseReq param){

        try {

            AllClassScheduleRes acsr = iPkClassScheduleService.getAllSchedule(param);


            return success("/classSchedule/getAllSchedule", ApiResult.SUCCESS_MESSAGE, acsr);

        }catch (Exception e){
            log.info(e.getMessage());
            if (e instanceof CheckedException) {
                return error("/classSchedule/getAllSchedule", e.getMessage());
            }
        }
        return error("/classSchedule/getAllSchedule", ErrorCode.ERROR_SYSTEM);
    }


    /**
     * 获取班级课表
     * @param param
     * @return
     */
    @RequestMapping(value = "/getClassSchedule",method = RequestMethod.POST)
    @ApiOperation(value = "获取班级课表", notes = "获取班级课表：taskId(任务id)，classId或者roomId", httpMethod = "POST")
    public ApiResult getClassSchedule(@RequestBody BaseReq param){

        try {

            List<ClassScheduleRes> acsr = iPkClassScheduleService.getClassSchedule(param);


            return success("/classSchedule/getAllSchedule", ApiResult.SUCCESS_MESSAGE, acsr);

        }catch (Exception e){
            log.info(e.getMessage());
            if (e instanceof CheckedException) {
                return error("/classSchedule/getAllSchedule", e.getMessage());
            }
        }
        return error("/classSchedule/getAllSchedule", ErrorCode.ERROR_SYSTEM);
    }


    /**
     * 获取教室课表
     * @param param
     * @return
     */
    @RequestMapping(value = "/getRoomSchedule",method = RequestMethod.POST)
    @ApiOperation(value = "获取教室课表", notes = "获取教室课表：taskId(任务id)，classId", httpMethod = "POST")
    public ApiResult getRoomSchedule(@RequestBody BaseReq param){

        try {

            List<ClassScheduleRes> acsr = iPkClassScheduleService.getClassSchedule(param);


            return success("/classSchedule/getAllSchedule", ApiResult.SUCCESS_MESSAGE, acsr);

        }catch (Exception e){
            log.info(e.getMessage());
            if (e instanceof CheckedException) {
                return error("/classSchedule/getAllSchedule", e.getMessage());
            }
        }
        return error("/classSchedule/getAllSchedule", ErrorCode.ERROR_SYSTEM);
    }

}

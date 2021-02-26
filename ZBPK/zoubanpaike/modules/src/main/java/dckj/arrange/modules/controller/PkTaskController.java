package dckj.arrange.modules.controller;


import dckj.arrange.common.base.ApiBaseController;
import dckj.arrange.common.base.ApiResult;
import dckj.arrange.common.entity.PkTask;
import dckj.arrange.common.model.request.PkTaskCurriculumSetVo;
import dckj.arrange.common.model.request.PkTaskReq;
import dckj.arrange.modules.feign.IPkTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 排课任务表 前端控制器
 * </p>
 *
 * @author jiangyong
 * @since 2019-08-17
 */
@RestController
@RequestMapping("/pkTask")
@Slf4j
@Api(value = "/pkTask", description = "排课任务管理")
public class PkTaskController extends ApiBaseController {

    @Autowired
    private IPkTaskService iPkTaskService;

    @RequestMapping(value = "/InsertTask" ,method = RequestMethod.POST)
    @ApiOperation(value = "创建排课任务",
            notes = "taskName " +
                    "排课名称 Y" +
                    "    "+
                    "gradeId " +
                    "年级Id  Y" +
                    "    "+
                    "gradeName" +
                    "年级名称 Y" +
                    "    "+
                    "classNumber" +
                    " 排课班级总数 N" +
                    "    "+
                    "schoolYear " +
                    "学年 Y " +
                    "    "+
                    "semesterType " +
                    "学期类型 0-上学期 1-下学期 Y" +
                    "    "+
                    "pkType" +
                    "走班类型 0-不走班 1-小走班 2大走班 3全走班 Y" +
                    "    "+
                    "xkMode " +
                    "选课模式 0-常规排课 1-“3+1+2模式” 2- “6选3模式 Y" +
                    "    "+
                    "schoolCode " +
                    "学校编号 Y" +
                    "    "+
                    "type" +
                    "0-未发布 1-调整中 2-已完结 3-已发布 Y 发布类型" +
                    "    "+
                    "userId" +
                    "操作人id（登录人权限账号id）" +
                    "    "+
                    "Y必传 " +
                    " N可不传  " +
                    "未标参数不传",
                    httpMethod = "POST")
    public ApiResult InsertTask(@RequestBody PkTaskCurriculumSetVo pkTaskCurriculumSetVo){
        ApiResult result = iPkTaskService.InsertTask(pkTaskCurriculumSetVo);
        return result;
    }

    @RequestMapping(value = "/SelectPkTask", method = RequestMethod.POST)
    @ApiOperation(value = "查询排课任务",notes = "查询排课任务 所有参数必传")
    public ApiResult SelectPkTask(@RequestBody PkTaskReq pkTaskReq) {
            ApiResult result = iPkTaskService.SelectPkTask(pkTaskReq);
            return  result;
    }

    @RequestMapping(value = "/deletePkTask", method = RequestMethod.POST)
    @ApiOperation(value = "根据id删除排课任务",notes = "根据id删除排课任务 传入id")
    public ApiResult deletePkTask(@RequestBody PkTask pkTask) {
        ApiResult result = iPkTaskService.deletePkTask(pkTask);
        return  result;
    }
}


package dckj.arrange.producer.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dckj.arrange.common.base.ApiBaseController;
import dckj.arrange.common.base.ApiResult;
import dckj.arrange.common.base.ErrorCode;
import dckj.arrange.common.entity.PkTask;
import dckj.arrange.common.exception.CheckedException;
import dckj.arrange.common.model.request.PkTaskCurriculumSetVo;
import dckj.arrange.common.model.request.PkTaskReq;
import dckj.arrange.producer.service.impl.PkTaskServiceImpl;
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
public class PkTaskController  extends ApiBaseController {

    @Autowired
    private PkTaskServiceImpl pkTaskService;


    @RequestMapping(value = "/InsetPkTask", method = RequestMethod.POST)
    public ApiResult  InsetPkTask(@RequestBody PkTaskCurriculumSetVo pkTaskCurriculumSetVo) {

        try {
             pkTaskService.InsertPkTask(pkTaskCurriculumSetVo);
            return success("/pkTask/InsetPkTask", ApiResult.SUCCESS_MESSAGE);
        } catch (Exception e) {
            if (e instanceof CheckedException) {
                return error("/pkTask/InsetPkTask", e.getMessage());
            }
        }
        return error("/pkTask/InsetPkTask", ErrorCode.ERROR_SYSTEM);
    }


    @RequestMapping(value = "/SelectPkTask", method = RequestMethod.POST)
    public ApiResult SelectPkTask(@RequestBody PkTaskReq pkTaskReq) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("schoolCode",pkTaskReq.getSchoolCode());
            map.put("userId",pkTaskReq.getUserId());
            map.put("pageIndex",pkTaskReq.getPageIndex());
            map.put("pageSize",pkTaskReq.getPageSize());
            PageHelper.startPage(Integer.parseInt(map.get("pageIndex").toString()), Integer.parseInt(map.get("pageSize").toString()));
            List<PkTask> pkTasks = pkTaskService.selectPktask(map);
            PageInfo<PkTask> info = new PageInfo<>(pkTasks);
            return success("/pkTask/SelectPkTask",ApiResult.SUCCESS_MESSAGE,info);
        }catch (Exception e){
            if (e instanceof CheckedException) {
                return error("/pkTask/SelectPkTask", e.getMessage());
            }
        }
        return error("/pkTask/SelectPkTask", ErrorCode.ERROR_SYSTEM);
    }

    @RequestMapping(value = "/deletePkTask", method = RequestMethod.POST)
    public ApiResult deletePkTask(@RequestBody PkTask pkTask) {
        try {
            pkTaskService.deletePkTask(pkTask);
            return success("/pkTask/deletePkTask",ApiResult.SUCCESS_MESSAGE);
        }catch (Exception e){
            if (e instanceof CheckedException) {
                return error("/pkTask/SelectPkTask", e.getMessage());
            }
        }
        return error("/pkTask/SelectPkTask", ErrorCode.ERROR_SYSTEM);
    }
}


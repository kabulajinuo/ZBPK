package dckj.arrange.producer.controller;

import dckj.arrange.common.base.ApiBaseController;
import dckj.arrange.common.base.ApiResult;
import dckj.arrange.common.base.ErrorCode;
import dckj.arrange.common.model.request.BaseLessonReq;
import dckj.arrange.common.util.EmptyUtil;
import dckj.arrange.producer.service.BaseLessonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("lesson/base")
@Slf4j
@Api(value = "lesson/base", description = "基础课时设置")
public class BaseLessonController extends ApiBaseController {

    @Autowired
    private BaseLessonService baseLessonService;

    @RequestMapping(value = "toInsertBaseLesson", method = {RequestMethod.POST})
    @ApiOperation(value = "增加课时信息", notes = "pkLessonDetailList  [\n" +
            "      {\n" +
            "        \"taskId\": 任务ID,\n" +
            "        \"lessonName\": \"课节名称\",\n" +
            "        \"startTime\": \"开始时间 存时钟数\",\n" +
            "        \"endTime\": \"结束时间\",\n" +
            "        \"detailDuration\": 每节课时长,\n" +
            "        \"freeTime\": 课间休息时间,\n" +
            "        \"importance\": 重要程度(1,2,3),\n" +
            "        \"schoolCode\": 学校编号,\n" +
            "      }," +
            "(课时详情) taskId startDay(如果是星期一就写1) endDay(如果是星期6 就写6) morning(上午) afternoon(下午) duration(每节课的时长) schoolCode(学校编号)", httpMethod = "POST")
    public ApiResult toInsertBaseLesson(@RequestBody @Valid BaseLessonReq baseLessonReq, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return error("lesson/base/toInsertBaseLesson", ApiResult.SYSTEM_FAILED, bindingResult.getFieldError().getDefaultMessage());
        }
        try {
            baseLessonService.toInsertLesson(baseLessonReq);
            return success("lesson/base/toInsertBaseLesson", ApiResult.SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.error("【增加课时】错误：" + e.getMessage());
            return error("lesson/base/toInsertBaseLesson", ApiResult.SYSTEM_FAILED, e.getMessage());
        }
    }

    @RequestMapping(value = "toGetBaseLesson", method = {RequestMethod.POST})
    @ApiOperation(value = "查询 课时信息", notes = "{taskId：任务ID, schoolCode:学校编号}", httpMethod = "POST")
    public ApiResult toGetBaseLesson(@RequestBody BaseLessonReq baseLessonReq) {
        if (EmptyUtil.isEmpty(baseLessonReq.getTaskId()) || EmptyUtil.isEmpty(baseLessonReq.getSchoolCode())) {
            return error("lesson/base/toGetBaseLesson", ErrorCode.ERROR_MISS_PARAM);
        }
        try {
            BaseLessonReq baseLesson = baseLessonService.toGetLesson(baseLessonReq);
            return success("lesson/base/toGetBaseLesson", ApiResult.SUCCESS_MESSAGE, baseLesson);
        } catch (Exception e) {
            log.error("【查询 基础课时信息】 错误：" + e.getMessage());
            return error("lesson/base/toGetBaseLesson", ErrorCode.ERROR_SYSTEM);
        }
    }

    @RequestMapping(value = "toUpdateBaseLesson", method = {RequestMethod.POST})
    @ApiOperation(value = "修改课时信息", notes = "pkLessonDetailList  [\n" +
            "      {\n" +
            "        \"taskId\": 任务ID,\n" +
            "        \"lessonName\": \"课节名称\",\n" +
            "        \"startTime\": \"开始时间 存时钟数\",\n" +
            "        \"endTime\": \"结束时间\",\n" +
            "        \"detailDuration\": 每节课时长,\n" +
            "        \"freeTime\": 课间休息时间,\n" +
            "        \"importance\": 重要程度,\n" +
            "        \"schoolCode\": 学校编号,\n" +
            "      }," +
            "(课时详情) taskId startDay(如果是星期一就写1) endDay(如果是星期6 就写6) morning(上午) afternoon(下午) duration(每节课的时长) schoolCode(学校编号)", httpMethod = "POST")
    public ApiResult toUpdateBaseLesson(@RequestBody @Valid BaseLessonReq baseLessonReq, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return error("lesson/base/toUpdateBaseLesson", ApiResult.SYSTEM_FAILED, bindingResult.getFieldError().getDefaultMessage());
        }
        try {
            baseLessonService.toUpdateLesson(baseLessonReq);
            return success("grade/toUpdateBaseLesson", ApiResult.SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.error("【修改课时信息】错误：" + e.getMessage());
            return error("grade/toUpdateBaseLesson", ErrorCode.ERROR_SYSTEM);
        }
    }

}

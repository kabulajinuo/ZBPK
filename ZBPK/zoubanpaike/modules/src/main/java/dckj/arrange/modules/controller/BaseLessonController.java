package dckj.arrange.modules.controller;

import dckj.arrange.common.base.ApiBaseController;
import dckj.arrange.common.base.ApiResult;
import dckj.arrange.common.model.request.BaseLessonReq;
import dckj.arrange.modules.feign.FeginService;
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
    private FeginService feginService;

    @RequestMapping(value = "toInsertBaseLesson", method = {RequestMethod.POST})
    @ApiOperation(value = "增加课时信息", notes = "pkLessonDetailList  [\n" +
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
    public ApiResult toInsertBaseLesson(@RequestBody @Valid BaseLessonReq baseLessonReq, BindingResult bindingResult) {

        ApiResult apiResult = feginService.toInsertLesson(baseLessonReq);
        return apiResult;
    }

    @RequestMapping(value = "toGetBaseLesson", method = {RequestMethod.POST})
    @ApiOperation(value = "查询 课时信息", notes = "{taskId：任务ID, schoolCode:学校编号}", httpMethod = "POST")
    public ApiResult toGetBaseLesson(@RequestBody BaseLessonReq baseLessonReq) {
        ApiResult apiResult = feginService.toGetLesson(baseLessonReq);
        return apiResult;
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
        ApiResult apiResult = feginService.toUpdateLesson(baseLessonReq);
        return apiResult;
    }
}

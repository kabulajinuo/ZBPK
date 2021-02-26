package dckj.arrange.producer.controller;

import com.github.pagehelper.PageInfo;
import dckj.arrange.common.base.ApiBaseController;
import dckj.arrange.common.base.ApiResult;
import dckj.arrange.common.base.ErrorCode;
import dckj.arrange.common.model.request.GradeVo;
import dckj.arrange.common.util.EmptyUtil;
import dckj.arrange.producer.service.GradeService;
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
@RequestMapping("grade")
@Api(value = "grade", description = "年级相关")
@Slf4j
public class GradeController extends ApiBaseController {
    @Autowired
    private GradeService gradeService;

    @RequestMapping(value = "toInsertGrade", method = {RequestMethod.POST})
    @ApiOperation(value = "增加年级信息", notes = "{\n" +
            "  \"gradeManagers\": \"管理者ids\",\n" +
            "  \"gradeName\": \"年级名称\",\n" +
            "  \"schoolCode\": \"学校编号\",\n" +
            "}", httpMethod = "POST")
    public ApiResult toInsertGrade(@RequestBody @Valid GradeVo gradeVo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return error("grade/toGetRoomInfo", ApiResult.SYSTEM_FAILED,bindingResult.getFieldError().getDefaultMessage());
        }
        try {
            int i = gradeService.toInsertGrade(gradeVo);
            if (i < 0) {
                return error("grade/toInsertGrade", ErrorCode.ERROR_SYSTEM);
            }
            return success("grade/toInsertGrade", ApiResult.SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.error("【增加 年级信息 】错误：" + e.getMessage());
            return error("grade/toInsertGrade", ApiResult.SYSTEM_FAILED, e.getMessage());
        }
    }

    @RequestMapping(value = "toGetGrade", method = {RequestMethod.POST})
    @ApiOperation(value = "查询年级信息", notes = "{\n" +
            "  \"pageFilterVo\": {\n" +
            "    \"pageNumber\": 1,\n" +
            "    \"pageSize\": 10,\n" +
            "  \"schoolCode\": \"学校编号\",\n" +
            "}", httpMethod = "POST")
    public ApiResult toGetGrade(@RequestBody  GradeVo gradeVo) {
        if (EmptyUtil.isEmpty(gradeVo.getPageFilterVo().getPageNumber()) || EmptyUtil.isEmpty(gradeVo.getPageFilterVo().getPageSize()) || EmptyUtil.isEmpty(gradeVo.getSchoolCode() )) {
            return error("grade/toGetGrade", ErrorCode.ERROR_MISS_PARAM);
        }
        try {
            PageInfo<GradeVo> gradeVoPageInfo = gradeService.toGetGrade(gradeVo);
            return success("grade/toGetGrade", ApiResult.SUCCESS_MESSAGE, gradeVoPageInfo);
        } catch (Exception e) {
            log.error("【查询 年级信息】 错误：" + e.getMessage());
            return error("grade/toGetGrade", ErrorCode.ERROR_SYSTEM);
        }
    }

    @RequestMapping(value = "toDeleteGrade", method = {RequestMethod.POST})
    @ApiOperation(value = "删除年级信息", notes = "gradeId年级主键", httpMethod = "POST")
    public ApiResult toDeleteGrade(@RequestBody GradeVo gradeVo) {
        if (EmptyUtil.isEmpty(gradeVo.getGradeId())) {
            return error("grade/toDeleteGrade", ErrorCode.ERROR_MISS_PARAM);
        }
        try {
            int i = gradeService.toDeleteGrade(gradeVo);
            if (i < 0) {
                return error("grade/toDeleteGrade", ErrorCode.ERROR_SYSTEM);
            }
            return success("grade/toDeleteGrade", ApiResult.SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.error("【删除 年级信息 】错误：" + e.getMessage());
            return error("grade/toDeleteGrade", ErrorCode.ERROR_SYSTEM);
        }
    }

    @RequestMapping(value = "toUpdateGrade", method = {RequestMethod.POST})
    @ApiOperation(value = "修改 年级信息", notes = "{\n" +
            "  \"gradeId\": \"主键\",\n" +
            "  \"gradeManagers\": \"年级管理者\",\n" +
            "  \"gradeName\": \"年级名称\",\n" +
            "  \"schoolCode\": \"学校编号\",\n" +
            "}", httpMethod = "POST")
    public ApiResult toUpdateGrade(@RequestBody GradeVo gradeVo) {
        if (EmptyUtil.isEmpty(gradeVo.getGradeId())) {
            return error("grade/toUpdateGrade", ErrorCode.ERROR_MISS_PARAM);
        }
        try {
            int i = gradeService.toUpdateGrade(gradeVo);
            if (i < 0) {
                return error("grade/toUpdateGrade", ErrorCode.ERROR_SYSTEM);
            }
            return success("grade/toUpdateGrade", ApiResult.SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.error("【修改 年级信息 】错误：" + e.getMessage());
            return error("grade/toUpdateGrade", ErrorCode.ERROR_SYSTEM);
        }
    }
}

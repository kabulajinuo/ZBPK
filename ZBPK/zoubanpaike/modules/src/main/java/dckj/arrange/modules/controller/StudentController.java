package dckj.arrange.modules.controller;

import dckj.arrange.common.base.ApiBaseController;
import dckj.arrange.common.base.ApiResult;
import dckj.arrange.common.model.request.StudentVo;
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
import java.util.List;

@RestController
@RequestMapping("student")
@Api(value = "student", description = "学生管理相关")
@Slf4j
public class StudentController extends ApiBaseController {
    @Autowired
    private FeginService feginService;

    @RequestMapping(value = "toInsertBatchStudent", method = {RequestMethod.POST})
    @ApiOperation(value = "批量增加 学生", notes = "gradeId classId stuNo  stuName stuSex readingWay startTime isGraduate schoolCode", httpMethod = "POST")
    public ApiResult toInsertBatchStudent(@RequestBody @Valid List<StudentVo> studentVoList) {

        ApiResult apiResult = feginService.toInsertBatchStudent(studentVoList);
        return apiResult;
    }

    @RequestMapping(value = "toInsertStudent", method = {RequestMethod.POST})
    @ApiOperation(value = "增加学生", notes = "gradeId classId stuNo  stuName stuSex readingWay startTime isGraduate schoolCode", httpMethod = "POST")
    public ApiResult toInsertStudent(@RequestBody @Valid StudentVo studentVo) {
        ApiResult apiResult = feginService.toInsertStudent(studentVo);
        return apiResult;
    }

    @RequestMapping(value = "toGetStudent", method = {RequestMethod.POST})
    @ApiOperation(value = "查询学生", notes = "PageFilterVo gradeId classId stuNo stuName schoolCode(必传)", httpMethod = "POST")
    public ApiResult toGetStudent(@RequestBody StudentVo studentVo) {
        ApiResult apiResult = feginService.toGetStudent(studentVo);
        return apiResult;
    }

    @RequestMapping(value = "toDeleteStudent", method = {RequestMethod.POST})
    @ApiOperation(value = "删除学生", notes = "userId", httpMethod = "POST")
    public ApiResult toDeleteStudent(@RequestBody StudentVo studentVo) {
        ApiResult apiResult = feginService.toDeleteStudent(studentVo);
        return apiResult;
    }

    @RequestMapping(value = "toDeleteBatchStudent", method = {RequestMethod.POST})
    @ApiOperation(value = "批量删除学生", notes = "userId", httpMethod = "POST")
    public ApiResult toDeleteBatchStudent(@RequestBody List<StudentVo> studentVoList) {

        ApiResult apiResult = feginService.toDeleteBatchStudent(studentVoList);
        return apiResult;
    }


    @RequestMapping(value = "toUpdateStudent", method = {RequestMethod.POST})
    @ApiOperation(value = "修改学生", notes = "userId postIds,subjectIds(如有多个请以逗号分隔),jobNo,teacherName,schoolCode,password,gender,phone", httpMethod = "POST")
    public ApiResult toUpdateStudent(@RequestBody @Valid StudentVo studentVo, BindingResult bindingResult) {

        ApiResult apiResult = feginService.toUpdateStudent(studentVo);
        return apiResult;
    }
}

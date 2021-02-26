package dckj.arrange.producer.controller;

import com.github.pagehelper.PageInfo;
import dckj.arrange.common.base.ApiBaseController;
import dckj.arrange.common.base.ApiResult;
import dckj.arrange.common.base.ErrorCode;
import dckj.arrange.common.enums.FileEnum;
import dckj.arrange.common.model.request.StudentVo;
import dckj.arrange.common.util.EmptyUtil;
import dckj.arrange.common.util.poi.ExcelUtil;
import dckj.arrange.producer.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("student")
@Api(value = "student", description = "学生管理相关")
@Slf4j
public class StudentController extends ApiBaseController {
    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "toInsertBatchStudent", method = {RequestMethod.POST})
    @ApiOperation(value = "批量增加 学生", notes = "gradeId classId stuNo  stuName stuSex readingWay startTime isGraduate schoolCode", httpMethod = "POST")
    public ApiResult toInsertBatchStudent(@RequestBody @Valid List<StudentVo> studentVoList) {
        try {
            //校验数据
            EmptyUtil.validate(studentVoList);
            studentService.toInsertBatchStudent(studentVoList);
            return success("student/toInsertBatchStudent", ApiResult.SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.error("【批量增加学生】 错误：" + e.getMessage());
            return error("student/toInsertBatchStudent", ApiResult.SYSTEM_FAILED, e.getMessage());
        }
    }

    @RequestMapping(value = "toInsertStudent", method = {RequestMethod.POST})
    @ApiOperation(value = "增加学生", notes = "gradeId：年级ID classId：班级ID stuNo:学号 stuName：学生名字 stuSex：学生性别（0男，1女）" +
            " readingWay：就读方式（0住校，1外住）" +
            " startTime（入学年份） isGraduate（0在读，1已毕业） schoolCode", httpMethod = "POST")
    public ApiResult toInsertStudent(@RequestBody @Valid StudentVo studentVo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return error("student/toUpdateGrade", ApiResult.SYSTEM_FAILED, bindingResult.getFieldError().getDefaultMessage());
        }
        try {
            studentService.toInsertStudent(studentVo);
            return success("student/toInsertStudent", ApiResult.SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.error("【增加学生】 错误：" + e.getMessage());
            return error("student/toInsertStudent", ApiResult.SYSTEM_FAILED, e.getMessage());
        }
    }

    @RequestMapping(value = "toGetStudent", method = {RequestMethod.POST})
    @ApiOperation(value = "查询学生", notes = "PageFilterVo（分页参数） gradeId:年级ID classId:班级ID stuNo:学号 stuName：学生名字" +
            " schoolCode(必传)", httpMethod = "POST")
    public ApiResult toGetStudent(@RequestBody StudentVo studentVo) {
        if (EmptyUtil.isEmpty(studentVo.getPageFilterVo().getPageNumber()) || EmptyUtil.isEmpty(studentVo.getPageFilterVo().getPageSize()) || EmptyUtil.isEmpty(studentVo.getSchoolCode())) {
            return error("student/toGetStudent", ErrorCode.ERROR_MISS_PARAM);
        }
        try {
            PageInfo<StudentVo> studentVoPageInfo = studentService.toGetStudent(studentVo);
            return success("student/toGetStudent", ApiResult.SUCCESS_MESSAGE, studentVoPageInfo);
        } catch (Exception e) {
            log.error("【查询学生】 错误：" + e.getMessage());
            return error("student/toGetStudent", ErrorCode.ERROR_SYSTEM);
        }
    }

    @RequestMapping(value = "toImportStudentData", method = {RequestMethod.POST})
    @ApiOperation(value = "导入学生信息", notes = "保证数据正确", httpMethod = "POST")
    public ApiResult toImportStudentData(MultipartFile file) {
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        if (!suffix.startsWith(FileEnum.Excels.getSuffix())) {
            return error("student/toImportStudentData", ApiResult.SYSTEM_FAILED, "只能导入Excel表格");
        }
        if (!ExcelUtil.checkFileSize(file)) {
            return error("student/toImportStudentData", ApiResult.SYSTEM_FAILED, "文件大小超出范围");
        }
        try {
            ExcelUtil<StudentVo> util = new ExcelUtil<StudentVo>(StudentVo.class);
            List<StudentVo> studentVoList = util.importExcel(file.getInputStream());
            EmptyUtil.validate(studentVoList);
            studentService.toImportStudent(studentVoList);
            return success("student/toImportStudentData", ApiResult.SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.error("【导入学生信息】 错误：" + e.getMessage());
            return error("student/toImportStudentData", ApiResult.SYSTEM_FAILED, e.getMessage());
        }

    }

    @RequestMapping(value = "toDeleteStudent", method = {RequestMethod.POST})
    @ApiOperation(value = "删除学生", notes = "userId：主键", httpMethod = "POST")
    public ApiResult toDeleteStudent(@RequestBody StudentVo studentVo) {
        if (EmptyUtil.isEmpty(studentVo.getUserId())) {
            return error("student/toDeleteStudent", ErrorCode.ERROR_MISS_PARAM);
        }
        try {
            studentService.toDeleteStudent(studentVo);
            return success("student/toDeleteStudent", ApiResult.SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.error("【删除学生】错误：" + e.getMessage());
            return error("student/toDeleteStudent", ErrorCode.ERROR_SYSTEM);
        }
    }

    @RequestMapping(value = "toDeleteBatchStudent", method = {RequestMethod.POST})
    @ApiOperation(value = "批量删除学生", notes = "userId主键", httpMethod = "POST")
    public ApiResult toDeleteBatchStudent(@RequestBody List<StudentVo> studentVoList) {
        if (studentVoList.size() < 0) {
            return error("student/toDeleteBatchStudent", ErrorCode.ERROR_MISS_PARAM);
        }
        try {
            studentService.toDeleteBatchStudent(studentVoList);
            return success("student/toDeleteBatchStudent", ApiResult.SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.error("【批量删除学生】错误：" + e.getMessage());
            return error("student/toDeleteBatchStudent", ErrorCode.ERROR_SYSTEM);
        }
    }


    @RequestMapping(value = "toUpdateStudent", method = {RequestMethod.POST})
    @ApiOperation(value = "修改学生", notes = "{\n" +
            "  \"classId\": \"班级ID\",\n" +
            "  \"gradeId\": \"年级ID\",\n" +
            "  \"isGraduate\": \"是否毕业\",\n" +
            "  \"readingWay\": \"走读方式\",\n" +
            "  \"schoolCode\": \"学校编号\",\n" +
            "  \"startTime\": \"入学年份\",\n" +
            "  \"stuName\": \"学生姓名\",\n" +
            "  \"stuNo\": \"学号\",\n" +
            "  \"stuSex\": \"学生性别\",\n" +
            "  \"userId\": \"主键\"\n" +
            "}", httpMethod = "POST")
    public ApiResult toUpdateStudent(@RequestBody @Valid StudentVo studentVo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return error("student/toUpdateGrade", ApiResult.SYSTEM_FAILED, bindingResult.getFieldError().getDefaultMessage());
        }
        if (EmptyUtil.isEmpty(studentVo.getUserId())) {
            return error("student/toUpdateGrade", ErrorCode.ERROR_MISS_PARAM);
        }
        try {
            studentService.toUpdateStudent(studentVo);
            return success("student/toUpdateGrade", ApiResult.SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.error("【修改学生】错误：" + e.getMessage());
            return error("student/toUpdateGrade", ApiResult.SYSTEM_FAILED, e.getMessage());
        }
    }
}

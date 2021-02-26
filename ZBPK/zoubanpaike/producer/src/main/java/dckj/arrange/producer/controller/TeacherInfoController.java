package dckj.arrange.producer.controller;

import com.github.pagehelper.PageInfo;
import dckj.arrange.common.base.ApiBaseController;
import dckj.arrange.common.base.ApiResult;
import dckj.arrange.common.base.ErrorCode;
import dckj.arrange.common.entity.PkTeacherInfo;
import dckj.arrange.common.enums.FileEnum;
import dckj.arrange.common.exception.CheckedException;
import dckj.arrange.common.model.request.BaseReq;
import dckj.arrange.common.model.request.TeacherInfoVo;
import dckj.arrange.common.util.DownloadUtil;
import dckj.arrange.common.util.EmptyUtil;
import dckj.arrange.common.util.poi.ExcelUtil;
import dckj.arrange.producer.service.TeacherInfoService;
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

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("teacher/info")
@Api(value = "teacher/info", description = "教师信息相关")
@Slf4j
public class TeacherInfoController extends ApiBaseController {

    @Autowired
    private TeacherInfoService teacherInfoService;

    @RequestMapping(value = "toInsertBatchTeacherInfo", method = {RequestMethod.POST})
    @ApiOperation(value = "批量增加老师信息", notes = "postIds：职务ID,subjectIds:学科ID(如有多个请以逗号分隔),jobNo:工号,teacherName：教师名字," +
            "schoolCode,password：密码,gender（0男，1女）,phone：电话号码", httpMethod = "POST")
    public ApiResult toInsertBatchTeacherInfo(@RequestBody @Valid List<TeacherInfoVo> teacherInfoVoList) {
        try {
            //校验数据
            EmptyUtil.validate(teacherInfoVoList);
            teacherInfoService.toInsertBatchTeacherInfo(teacherInfoVoList);
            return success("teacher/info/toInsertTeacherPosition", ApiResult.SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.error("批量增加老师信息 错误：" + e.getMessage());
            return error("teacher/info/toInsertTeacherPosition", ApiResult.SYSTEM_FAILED,e.getMessage());
        }
    }

    @RequestMapping(value = "toInsertTeacherInfo", method = {RequestMethod.POST})
    @ApiOperation(value = "增加老师信息", notes = "postIds：职务ID,subjectIds:学科ID(如有多个请以逗号分隔),jobNo:工号,teacherName：教师名字,\" +\n" +
            "            \"schoolCode,password：密码,gender（0男，1女）,phone：电话号码", httpMethod = "POST")
    public ApiResult toInsertTeacherInfo(@RequestBody @Valid TeacherInfoVo teacherInfoVo) {
        try {
            teacherInfoService.toInsertTeacherInfo(teacherInfoVo);
            return success("teacher/info/toInsertTeacherInfo", ApiResult.SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.error("增加老师信息 错误：" + e.getMessage());
            return error("teacher/info/toInsertTeacherInfo", ApiResult.SYSTEM_FAILED,e.getMessage());
        }
    }

    @RequestMapping(value = "toGetTeacherInfo", method = {RequestMethod.POST})
    @ApiOperation(value = "查询老师信息", notes = "PageFilterVo:分页参数 jobNo：工号 teacherName：教师名字 schoolCode phone：手机号码 postIds：职务ID subjectIds:学科ID", httpMethod = "POST")
    public ApiResult toGetTeacherInfo(@RequestBody TeacherInfoVo teacherInfoVo) {
        if (EmptyUtil.isEmpty(teacherInfoVo.getPageFilterVo().getPageNumber()) || EmptyUtil.isEmpty(teacherInfoVo.getPageFilterVo().getPageSize()) || EmptyUtil.isEmpty(teacherInfoVo.getSchoolCode() )) {
            return error("teacher/info/toGetTeacherInfo", ErrorCode.ERROR_MISS_PARAM);
        }
        try {
            PageInfo<TeacherInfoVo> teacherInfoVoPageInfo = teacherInfoService.toGetTeacherInfo(teacherInfoVo);
            return success("teacher/info/toGetTeacherInfo", ApiResult.SUCCESS_MESSAGE, teacherInfoVoPageInfo);
        } catch (Exception e) {
            log.error("【查询老师信息】 错误：" + e.getMessage());
            return error("teacher/info/toGetTeacherInfo", ErrorCode.ERROR_SYSTEM);
        }
    }

    @RequestMapping(value = "toImportTeacherInfoData",method = {RequestMethod.POST})
    @ApiOperation(value = "导入教师信息", notes = "保证 数据 正确", httpMethod = "POST")
    public ApiResult toImportTeacherInfoData(MultipartFile file){
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        if(!suffix.startsWith(FileEnum.Excels.getSuffix())){
            return error("teacher/info/toImportTeacherInfoData",ApiResult.SYSTEM_FAILED,"只能导入Excel表格");
        }
        if(!ExcelUtil.checkFileSize(file)){
            return error("teacher/info/toImportTeacherInfoData",ApiResult.SYSTEM_FAILED,"文件大小超出范围");
        }
        try {
            ExcelUtil<TeacherInfoVo> util = new ExcelUtil<TeacherInfoVo>(TeacherInfoVo.class);
            List<TeacherInfoVo> teacherInfoVoList = util.importExcel(file.getInputStream());
            EmptyUtil.validate(teacherInfoVoList);
            teacherInfoService.toImportTeacherInfo(teacherInfoVoList);
            return success("teacher/info/toImportTeacherInfoData",ApiResult.SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.error("【导入教师信息】 错误：" + e.getMessage());
            return error("teacher/info/toImportTeacherInfoData", ApiResult.SYSTEM_FAILED,e.getMessage());
        }
    }
    @RequestMapping(value = "toDownloadTeacherInfoFile",method = {RequestMethod.POST})
    @ApiOperation(value = "下载教师信息模板", notes = "保证 数据 正确", httpMethod = "POST")
    public void toDownloadTeacherInfoFile(HttpServletResponse response) {
        boolean flag = DownloadUtil.download(response, "教师信息表");
        if(!flag){
            log.info("下载教师信息模板失败");
        }
    }

    @RequestMapping(value = "toDeleteTeacherInfo", method = {RequestMethod.POST})
    @ApiOperation(value = "删除教师信息", notes = "userId：主键", httpMethod = "POST")
    public ApiResult toDeleteTeacherInfo(@RequestBody TeacherInfoVo teacherInfoVo) {
        if (EmptyUtil.isEmpty(teacherInfoVo.getUserId())) {
            return error("teacher/info/toDeleteGrade", ErrorCode.ERROR_MISS_PARAM);
        }
        try {
            teacherInfoService.toDeleteTeacherInfo(teacherInfoVo);
            return success("teacher/info/toDeleteGrade", ApiResult.SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.error("【删除教师信息】错误：" + e.getMessage());
            return error("teacher/info/toDeleteGrade", ErrorCode.ERROR_SYSTEM);
        }
    }

    @RequestMapping(value = "toUpdateTeacherInfo", method = {RequestMethod.POST})
    @ApiOperation(value = "修改教师信息", notes = "userId：主键 postIds：职务ID,subjectIds:学科ID(如有多个请以逗号分隔),jobNo:工号,teacherName：教师名字,\" +\n" +
            "            \"schoolCode,password：密码,gender（0男，1女）,phone：电话号码", httpMethod = "POST")
    public ApiResult toUpdateTeacherInfo(@RequestBody @Valid TeacherInfoVo teacherInfoVo,BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return error("teacher/info/toUpdateGrade", ApiResult.SYSTEM_FAILED,bindingResult.getFieldError().getDefaultMessage());
        }
        if (EmptyUtil.isEmpty(teacherInfoVo.getUserId())) {
            return error("teacher/info/toUpdateGrade", ErrorCode.ERROR_MISS_PARAM);
        }
        try {
            teacherInfoService.toUpdateTeacherInfo(teacherInfoVo);
            return success("teacher/info/toUpdateGrade", ApiResult.SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.error("【修改教师信息】错误：" + e.getMessage());
            return error("teacher/info/toUpdateGrade", ApiResult.SYSTEM_FAILED,e.getMessage());
        }
    }


    /**
     * 查询教师
     * @param param
     * @return
     */
    @RequestMapping(value = "/searchTeacher",method = RequestMethod.POST)
    @ApiOperation(value = "查询教师", notes = "查询教师", httpMethod = "POST")
    public ApiResult searchTeacher(@RequestBody BaseReq param){

        try {
            List<PkTeacherInfo> ptiList = teacherInfoService.searchTeacher(param);

            return success("/pkClass/saveClass", ApiResult.SUCCESS_MESSAGE, ptiList);

        }catch (Exception e){
            if (e instanceof CheckedException) {
                log.info(e.getMessage());
                return error("/pkClass/saveClass", e.getMessage());
            }
        }
        return error("/pkClass/saveClass", ErrorCode.ERROR_SYSTEM);
    }

}

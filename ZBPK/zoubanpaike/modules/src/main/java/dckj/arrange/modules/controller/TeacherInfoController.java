package dckj.arrange.modules.controller;

import dckj.arrange.common.base.ApiBaseController;
import dckj.arrange.common.base.ApiResult;
import dckj.arrange.common.base.ErrorCode;
import dckj.arrange.common.model.request.BaseReq;
import dckj.arrange.common.model.request.TeacherInfoVo;
import dckj.arrange.common.util.EmptyUtil;
import dckj.arrange.modules.feign.FeginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("teacher/info")
@Api(value = "teacher/info", description = "教师信息相关")
@Slf4j
public class TeacherInfoController extends ApiBaseController {
    @Autowired
    private FeginService feginService;

    @RequestMapping(value = "toInsertBatchTeacherInfo", method = {RequestMethod.POST})
    @ApiOperation(value = "批量增加老师信息", notes = "postIds,subjectIds(如有多个请以逗号分隔),jobNo,teacherName,schoolCode,password,gender,phone", httpMethod = "POST")
    public ApiResult toInsertBatchTeacherInfo(@RequestBody @Valid List<TeacherInfoVo> teacherInfoVoList) {
        ApiResult apiResult = feginService.toInsertBatchTeacherInfo(teacherInfoVoList);
        return apiResult;
    }

    @RequestMapping(value = "toInsertTeacherInfo", method = {RequestMethod.POST})
    @ApiOperation(value = "增加老师信息", notes = "postIds,subjectIds(如有多个请以逗号分隔),jobNo,teacherName,schoolCode,password,gender,phone", httpMethod = "POST")
    public ApiResult toInsertTeacherInfo(@RequestBody @Valid TeacherInfoVo teacherInfoVo) {
        ApiResult apiResult = feginService.toInsertTeacherInfo(teacherInfoVo);
        return apiResult;
    }

    @RequestMapping(value = "toGetTeacherInfo", method = {RequestMethod.POST})
    @ApiOperation(value = "查询老师信息", notes = "PageFilterVo jobNo teacherName schoolCode phone postIds subjectIds", httpMethod = "POST")
    public ApiResult toGetTeacherInfo(@RequestBody TeacherInfoVo teacherInfoVo) {
        ApiResult apiResult = feginService.toGetTeacherInfo(teacherInfoVo);
        return apiResult;
    }

    @RequestMapping(value = "toDeleteTeacherInfo", method = {RequestMethod.POST})
    @ApiOperation(value = "删除年级信息", notes = "userId", httpMethod = "POST")
    public ApiResult toDeleteTeacherInfo(@RequestBody TeacherInfoVo teacherInfoVo) {

        ApiResult apiResult = feginService.toDeleteTeacherInfo(teacherInfoVo);
        return apiResult;
    }

    @RequestMapping(value = "toUpdateTeacherInfo", method = {RequestMethod.POST})
    @ApiOperation(value = "修改 年级信息", notes = "userId postIds,subjectIds(如有多个请以逗号分隔),jobNo,teacherName,schoolCode,password,gender,phone", httpMethod = "POST")
    public ApiResult toUpdateTeacherInfo(@RequestBody TeacherInfoVo teacherInfoVo) {

        ApiResult apiResult = feginService.toUpdateTeacherInfo(teacherInfoVo);
        return apiResult;

    }

    /**
     * 查询教师
     * @param param
     * @return
     */
    @RequestMapping(value = "/searchTeacher",method = RequestMethod.POST)
    @ApiOperation(value = "查询教师", notes = "查询教师: name", httpMethod = "POST")
    public ApiResult searchTeacher(@RequestBody BaseReq param){
        if (EmptyUtil.isEmpty(param) || EmptyUtil.isEmpty(param.getName())) {
            return error("/teacher/info/searchTeacher", ErrorCode.ERROR_MISS_PARAM);
        }

        return feginService.searchTeacher(param);
    }

}

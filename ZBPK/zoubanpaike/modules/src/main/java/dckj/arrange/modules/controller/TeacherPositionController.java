package dckj.arrange.modules.controller;

import dckj.arrange.common.base.ApiBaseController;
import dckj.arrange.common.base.ApiResult;
import dckj.arrange.common.model.request.TeacherPositionVo;
import dckj.arrange.modules.feign.FeginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/teacher/position")
@Api(value = "teacher/position", description = "教师职务相关")
@Slf4j
public class TeacherPositionController extends ApiBaseController {

    @Autowired
    private FeginService feginService;

    @RequestMapping(value = "toInsertTeacherPosition", method = {RequestMethod.POST})
    @ApiOperation(value = "增加老师职务", notes = "只需填写职务名称", httpMethod = "POST")
    public ApiResult toInsertTeacherPosition(@RequestBody TeacherPositionVo teacherPositionVo) {
        ApiResult r = feginService.toInsertTeacherPosition(teacherPositionVo);
        return r;
    }

    @RequestMapping(value = "toGetTeacherPosition", method = {RequestMethod.POST})
    @ApiOperation(value = "查询教师职务", notes = "传入分页参数和学校code 即可查询想要的", httpMethod = "POST")
    //@RequestParam("schoolCode") String schoolCode,@RequestParam("pageNumber")Integer pageNumber,@RequestParam("pageSize")Integer pageSize
    public ApiResult toGetTeacherPosition(@RequestBody TeacherPositionVo teacherPositionVo) {
        ApiResult r = feginService.toGetTeacherPosition(teacherPositionVo);
        return r;
    }

    @RequestMapping(value = "toDeleteTeacherPosition", method = {RequestMethod.POST})
    @ApiOperation(value = "删除老师职务", notes = "传入单条记录的唯一ID即可删除", httpMethod = "POST")
    public ApiResult toDeleteTeacherPosition(@RequestBody TeacherPositionVo teacherPositionVo) {
        ApiResult r = feginService.toDeleteTeacherPosition(teacherPositionVo);
        return r;
    }

    @RequestMapping(value = "toUpdateTeacherPosition", method = {RequestMethod.POST})
    @ApiOperation(value = "修改老师职务",notes = "传入单条记录的唯一ID 以及修改参数 即可修改",httpMethod = "POST")
    public ApiResult toUpdateTeacherPosition(@RequestBody TeacherPositionVo teacherPositionVo) {
        ApiResult r = feginService.toUpdateTeacherPosition(teacherPositionVo);
        return r;
    }

}

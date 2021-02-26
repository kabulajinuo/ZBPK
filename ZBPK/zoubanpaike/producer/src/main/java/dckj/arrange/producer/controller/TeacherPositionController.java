package dckj.arrange.producer.controller;

import com.github.pagehelper.PageInfo;
import dckj.arrange.common.base.ApiBaseController;
import dckj.arrange.common.base.ApiResult;
import dckj.arrange.common.base.ErrorCode;
import dckj.arrange.common.entity.PkTeacherPosition;
import dckj.arrange.common.model.request.TeacherPositionVo;
import dckj.arrange.common.util.EmptyUtil;
import dckj.arrange.producer.service.TeacherPositionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("teacher/position")
@Api(value = "teacher/position", description = "教师职务相关")
@Slf4j
public class TeacherPositionController extends ApiBaseController {

    @Autowired
    private TeacherPositionService teacherPositionService;

    @RequestMapping(value = "toInsertTeacherPosition", method = {RequestMethod.POST})
    @ApiOperation(value = "增加老师职务",notes = "postName：职务名称",httpMethod = "POST")
    public ApiResult toInsertTeacherPosition(@RequestBody TeacherPositionVo teacherPositionVo) {
        if(EmptyUtil.isEmpty(teacherPositionVo)){
            return  error("teacher/positionto/InsertTeacherPosition",ErrorCode.ERROR_MISS_PARAM);
        }
        try {
            int i = teacherPositionService.toInsertTeacherPosition(teacherPositionVo);
            if (i < 0) {
                return error("teacher/position/toInsertTeacherPosition", ErrorCode.ERROR_SYSTEM);
            }
            return success("teacher/position/toInsertTeacherPosition", ApiResult.SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.error("增加教师职务错误：" + e.getMessage());
            return error("teacher/position/toInsertTeacherPosition", ErrorCode.ERROR_SYSTEM);
        }
    }

    @RequestMapping(value = "toGetTeacherPosition", method = {RequestMethod.POST})
    @ApiOperation(value = "查询教师职务",notes = "传入分页参数 以及学校编号 即可查询想要的",httpMethod = "POST")
    public ApiResult toGetTeacherPosition(@RequestBody TeacherPositionVo teacherPositionVo){
        if (EmptyUtil.isEmpty(teacherPositionVo.getPageFilterVo().getPageNumber()) || EmptyUtil.isEmpty(teacherPositionVo.getPageFilterVo().getPageSize()) || EmptyUtil.isEmpty(teacherPositionVo.getSchoolCode() )) {
            return  error("teacher/position/toGetTeacherPoosition",ErrorCode.ERROR_MISS_PARAM);
        }
        try {
            PageInfo<PkTeacherPosition> pkTeacherPositionPageInfo = teacherPositionService.toGetTeacherPosition(teacherPositionVo.getPageFilterVo(),teacherPositionVo);
            return  success("teacher/position/toGetTeacherPoosition",ApiResult.SUCCESS_MESSAGE,pkTeacherPositionPageInfo);
        } catch (Exception e) {
            log.error("查询教师职务错误："+e.getMessage());
            return error("teacher/positionto/toGetTeacherPoosition", ErrorCode.ERROR_SYSTEM);
        }
    }

    @RequestMapping(value = "toDeleteTeacherPosition", method = {RequestMethod.POST})
    @ApiOperation(value = "删除老师职务",notes = "postId 主键",httpMethod = "POST")
    public ApiResult toDeleteTeacherPosition( @RequestBody TeacherPositionVo teacherPositionVo) {
        if(EmptyUtil.isEmpty(teacherPositionVo)){
            return  error("teacher/position/toDeleteTeacherPosition",ErrorCode.ERROR_MISS_PARAM);
        }
        try {
            int i = teacherPositionService.toDeleteTeacherPosition(teacherPositionVo);
            if (i < 0) {
                return error("teacher/position/toDeleteTeacherPosition", ErrorCode.ERROR_SYSTEM);
            }
            return success("teacher/position/toDeleteTeacherPosition", ApiResult.SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.error("删除教师职务错误：" + e.getMessage());
            return error("teacher/positionto/toDeleteTeacherPosition", ErrorCode.ERROR_SYSTEM);
        }
    }

    @RequestMapping(value = "toUpdateTeacherPosition", method = {RequestMethod.POST})
    @ApiOperation(value = "修改老师职务",notes = "postId postName",httpMethod = "POST")
    public ApiResult toUpdateTeacherPosition(@RequestBody TeacherPositionVo teacherPositionVo) {
        if(EmptyUtil.isEmpty(teacherPositionVo)){
            return  error("teacher/positionto/toUpdateTeacherPosition",ErrorCode.ERROR_MISS_PARAM);
        }
        try {
            int i = teacherPositionService.toUpdateTeacherPosition(teacherPositionVo);
            if (i < 0) {
                return error("teacher/position/toUpdateTeacherPosition", ErrorCode.ERROR_SYSTEM);
            }
            return success("teacher/position/toUpdateTeacherPosition", ApiResult.SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.error("修改教师职务错误：" + e.getMessage());
            return error("teacher/positionto/toUpdateTeacherPosition", ErrorCode.ERROR_SYSTEM);
        }
    }

}

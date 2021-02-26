package dckj.arrange.modules.controller;


import dckj.arrange.common.base.ApiBaseController;
import dckj.arrange.common.base.ApiResult;
import dckj.arrange.common.base.ErrorCode;
import dckj.arrange.common.entity.PkClass;
import dckj.arrange.common.model.request.PkClassListReq;
import dckj.arrange.common.util.EmptyUtil;
import dckj.arrange.modules.feign.IPkClassService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 班级表 前端控制器
 * </p>
 *
 * @author Pengjin
 * @since 2019-08-14
 */
@RestController
@Api(value = "/pkClass", description = "班级管理")
@Slf4j
@RequestMapping("/pkClass")
public class PkClassController extends ApiBaseController {


    @Autowired
    private IPkClassService iPkClassService;

    @RequestMapping(value = "/saveClass",method = RequestMethod.POST)
    @ApiOperation(value = "新增班级", notes = "新增班级：className，schoolCode，gradeId，adviserId（班主任id）", httpMethod = "POST")
    public ApiResult saveClass(@RequestBody PkClass param){
        if(EmptyUtil.isEmpty(param) || EmptyUtil.isEmpty(param.getClassName()) || EmptyUtil.isEmpty(param.getSchoolCode())
                || EmptyUtil.isEmpty(param.getGradeId()) || EmptyUtil.isEmpty(param.getAdviserId())) {
            return error("/pkClass/saveClass", ErrorCode.ERROR_MISS_PARAM);
        }
        ApiResult r = iPkClassService.saveClass(param);
        return r;
    }


    /**
     * 查询班级列表
     * @param param
     * @return
     */
    @RequestMapping(value = "/getClassList",method = RequestMethod.POST)
    @ApiOperation(value = "查询班级列表", notes = "查询班级列表：className（非必传）， gradeId（非必传）, schoolCode", httpMethod = "POST")
    public ApiResult getClassList(@RequestBody PkClassListReq param){
        if(EmptyUtil.isEmpty(param) || EmptyUtil.isEmpty(param.getSchoolCode())) {
            return error("/pkClass/getClassList", ErrorCode.ERROR_MISS_PARAM);
        }
        ApiResult r = iPkClassService.getClassList(param);
        return r;
    }


    /**
     * 删除班级信息
     * @param param
     * @return
     */
    @RequestMapping(value = "/delClass",method = RequestMethod.POST)
    @ApiOperation(value = "删除班级信息", notes = "删除班级信息: id", httpMethod = "POST")
    public ApiResult delClass(@RequestBody PkClass param){
        if(EmptyUtil.isEmpty(param) || EmptyUtil.isEmpty(param.getId())) {
            return error("/pkClass/delClass", ErrorCode.ERROR_MISS_PARAM);
        }
        ApiResult r = iPkClassService.delClass(param);
        return r;
    }

    /**
     * 修改班级信息
     * @param param
     * @return
     */
    @RequestMapping(value = "/updateClass",method = RequestMethod.POST)
    @ApiOperation(value = "修改班级信息", notes = "修改班级信息", httpMethod = "POST")
    public ApiResult updateClass(@RequestBody PkClass param){
        if(EmptyUtil.isEmpty(param) || EmptyUtil.isEmpty(param.getClassName()) || EmptyUtil.isEmpty(param.getGradeId()) || EmptyUtil.isEmpty(param.getAdviserId())) {
            return error("/pkClass/updateClass", ErrorCode.ERROR_MISS_PARAM);
        }
        ApiResult r = iPkClassService.updateClass(param);
        return r;
    }


}

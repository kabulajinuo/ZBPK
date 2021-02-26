package dckj.arrange.modules.controller;

import dckj.arrange.common.base.ApiBaseController;
import dckj.arrange.common.base.ApiResult;
import dckj.arrange.common.model.request.GradeVo;
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
@RequestMapping("grade")
@Api(value = "grade", description = "年级相关")
@Slf4j
public class GradeController extends ApiBaseController {
    @Autowired
    private FeginService feginService;

    @RequestMapping(value = "toInsertGrade", method = {RequestMethod.POST})
    @ApiOperation(value = "增加年级信息", notes = "只需要传入相应参数即可不懂问技术人员", httpMethod = "POST")
    public ApiResult toInsertGrade(@RequestBody @Valid GradeVo gradeVo, BindingResult bindingResult) {

        ApiResult apiResult = feginService.toInsertGrade(gradeVo);
        return apiResult;
    }

    @RequestMapping(value = "toGetGrade", method = {RequestMethod.POST})
    @ApiOperation(value = "查询年级信息", notes = "传入分页参数 学校编码 即可查询想要的", httpMethod = "POST")
    public ApiResult toGetGrade(@RequestBody GradeVo gradeVo) {

        ApiResult apiResult = feginService.toGetGrade(gradeVo);
        return apiResult;
    }

    @RequestMapping(value = "toDeleteGrade", method = {RequestMethod.POST})
    @ApiOperation(value = "删除年级信息", notes = "传入单条记录的唯一ID即可删除", httpMethod = "POST")
    public ApiResult toDeleteGrade(@RequestBody GradeVo gradeVo) {

        ApiResult apiResult = feginService.toDeleteGrade(gradeVo);
        return apiResult;
    }

    @RequestMapping(value = "toUpdateGrade", method = {RequestMethod.POST})
    @ApiOperation(value = "修改 年级信息", notes = "传入单条记录的唯一ID 以及修改参数 即可修改", httpMethod = "POST")
    public ApiResult toUpdateGrade(@RequestBody GradeVo gradeVo) {

        ApiResult apiResult = feginService.toUpdateGrade(gradeVo);
        return apiResult;
    }
}

package dckj.arrange.modules.controller;

import dckj.arrange.common.base.ApiBaseController;
import dckj.arrange.common.base.ApiResult;
import dckj.arrange.common.model.request.SubjectVo;
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
@RequestMapping("subject")
@Api(value = "subject", description = "学科相关")
@Slf4j
public class SubjectController extends ApiBaseController {

    @Autowired
    private FeginService feginService;

    @RequestMapping(value = "toInsertSubject", method = {RequestMethod.POST})
    @ApiOperation(value = "增加学科", notes = "只需传入学科名字即可添加成功", httpMethod = "POST")
    public ApiResult toInsertSubject(@RequestBody SubjectVo subjectVo) {
        ApiResult apiResult = feginService.toInsertSubject(subjectVo);
        return apiResult;
    }

    @RequestMapping(value = "toGetSubject", method = {RequestMethod.POST})
    @ApiOperation(value = "查询学科", notes = "传入分页参数 学校code 即可查询想要的", httpMethod = "POST")
    public ApiResult toGetSubject(@RequestBody SubjectVo subjectVo) {
        ApiResult apiResult = feginService.toGetSubject(subjectVo);
        return apiResult;
    }

    @RequestMapping(value = "toDeleteSubject", method = {RequestMethod.POST})
    @ApiOperation(value = "删除学科", notes = "传入单条记录的唯一ID即可删除", httpMethod = "POST")
    public ApiResult toDeleteSubject(@RequestBody SubjectVo subjectVo) {
        ApiResult apiResult = feginService.toDeleteSubject(subjectVo);
        return apiResult;
    }

    @RequestMapping(value = "toUpdateSubject", method = {RequestMethod.POST})
    @ApiOperation(value = "修改老师职务", notes = "传入单条记录的唯一ID 以及修改参数 即可修改", httpMethod = "POST")
    public ApiResult toUpdateSubject(@RequestBody SubjectVo subjectVo) {
        ApiResult apiResult = feginService.toUpdateSubject(subjectVo);
        return apiResult;
    }
}

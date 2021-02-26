package dckj.arrange.producer.controller;

import com.github.pagehelper.PageInfo;
import dckj.arrange.common.base.ApiBaseController;
import dckj.arrange.common.base.ApiResult;
import dckj.arrange.common.base.ErrorCode;
import dckj.arrange.common.entity.PkSubject;
import dckj.arrange.common.model.request.SubjectVo;
import dckj.arrange.common.util.EmptyUtil;
import dckj.arrange.producer.service.SubjectService;
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
    private SubjectService subjectService;

    @RequestMapping(value = "toInsertSubject", method = {RequestMethod.POST})
    @ApiOperation(value = "增加学科", notes = "subjectName:学科名字", httpMethod = "POST")
    public ApiResult toInsertSubject(@RequestBody SubjectVo subjectVo) {
        if (EmptyUtil.isEmpty(subjectVo)) {
            return error("subject/toInsertSubject", ErrorCode.ERROR_MISS_PARAM);
        }
        try {
            int i = subjectService.toInsertSubject(subjectVo);
            if (i < 0) {
                return error("subject/toInsertSubject", ErrorCode.ERROR_SYSTEM);
            }
            return success("subject/toInsertSubject", ApiResult.SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.error("增加学科错误：" + e.getMessage());
            return error("subject/toInsertSubject", ErrorCode.ERROR_SYSTEM);
        }
    }

    @RequestMapping(value = "toGetSubject", method = {RequestMethod.POST})
    @ApiOperation(value = "查询学科", notes = "pageFilterVo分页参数 schoolCode:学校编号", httpMethod = "POST")
    public ApiResult toGetSubject(@RequestBody SubjectVo subjectVo) {
        if (EmptyUtil.isEmpty(subjectVo)) {
            return error("subject/toGetSubject", ErrorCode.ERROR_MISS_PARAM);
        }
        try {
            PageInfo<PkSubject> pkSubjectPageInfo = subjectService.toGetSubject(subjectVo,subjectVo.getPageFilterVo());
            return success("subject/toGetSubject", ApiResult.SUCCESS_MESSAGE, pkSubjectPageInfo);
        } catch (Exception e) {
            log.error("查询学科错误：" + e.getMessage());
            return error("subject/toGetSubject", ErrorCode.ERROR_SYSTEM);
        }
    }

    @RequestMapping(value = "toDeleteSubject", method = {RequestMethod.POST})
    @ApiOperation(value = "删除学科", notes = "subjectId:主键", httpMethod = "POST")
    public ApiResult toDeleteSubject(@RequestBody  SubjectVo subjectVo) {
        if (EmptyUtil.isEmpty(subjectVo)) {
            return error("subject/toDeleteSubject", ErrorCode.ERROR_MISS_PARAM);
        }
        try {
            int i = subjectService.toDeleteSubject(subjectVo);
            if (i < 0) {
                return error("subject/toDeleteSubject", ErrorCode.ERROR_SYSTEM);
            }
            return success("subject/toDeleteSubject", ApiResult.SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.error("删除 学科 错误：" + e.getMessage());
            return error("subject/toDeleteSubject", ErrorCode.ERROR_SYSTEM);
        }
    }

    @RequestMapping(value = "toUpdateSubject", method = {RequestMethod.POST})
    @ApiOperation(value = "修改学科", notes = "subjectId:主键 subjectName:名称", httpMethod = "POST")
    public ApiResult toUpdateSubject(@RequestBody SubjectVo subjectVo) {
        if (EmptyUtil.isEmpty(subjectVo)) {
            return error("subject/toUpdateSubject", ErrorCode.ERROR_MISS_PARAM);
        }
        try {
            int i = subjectService.toUpdateSubject(subjectVo);
            if (i < 0) {
                return error("subject/toUpdateSubject", ErrorCode.ERROR_SYSTEM);
            }
            return success("subject/toUpdateSubject", ApiResult.SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.error("修改 学科 错误：" + e.getMessage());
            return error("subject/toUpdateSubject", ErrorCode.ERROR_SYSTEM);
        }
    }
}

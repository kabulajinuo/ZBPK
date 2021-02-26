package dckj.arrange.producer.controller;


import dckj.arrange.common.base.ApiBaseController;
import dckj.arrange.common.base.ApiResult;
import dckj.arrange.common.base.ErrorCode;
import dckj.arrange.common.entity.PkCurriculumSet;
import dckj.arrange.common.exception.CheckedException;
import dckj.arrange.common.model.request.BaseReq;
import dckj.arrange.common.model.response.CurriculumSetRes;
import dckj.arrange.producer.service.IPkCurriculumSetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 课程设置表 前端控制器
 * </p>
 *
 * @author Xiangjing
 * @since 2019-08-17
 */
@RestController
@RequestMapping("/pkCurriculumSet")
@Api(value = "/pkCurriculumSet", description = "课程设置表")
@Slf4j
public class PkCurriculumSetController extends ApiBaseController {

    @Autowired
    private IPkCurriculumSetService iPkCurriculumSetService;

    /**
     * 获取课程设置列表
     * @param param
     * @return
     */
    @RequestMapping(value = "/getCurriculumSetList",method = RequestMethod.POST)
    @ApiOperation(value = "获取课程设置列表", notes = "获取课程设置列表： taskId", httpMethod = "POST")
    public ApiResult getCurriculumSetList(@RequestBody BaseReq param){

        try {
            CurriculumSetRes list =  iPkCurriculumSetService.getCurriculumSetList(param);

            return success("/pkCurriculumSet/getCurriculumSetList", ApiResult.SUCCESS_MESSAGE, list);

        }catch (Exception e){
            log.info(e.getMessage());
            if (e instanceof CheckedException) {
                return error("/pkCurriculumSet/getCurriculumSetList", e.getMessage());
            }
        }
        return error("/pkCurriculumSet/getCurriculumSetList", ErrorCode.ERROR_SYSTEM);
    }


    /**
     * 设置课程设置列表
     * @param param
     * @return
     */
    @RequestMapping(value = "/setCurriculumSetList",method = RequestMethod.POST)
    @ApiOperation(value = "设置课程设置列表", notes = "设置课程设置列表： list-->id, mustNumber, choiceNumber, level", httpMethod = "POST")
    public ApiResult setCurriculumSetList(@RequestBody List<PkCurriculumSet> param){

        try {
            iPkCurriculumSetService.setCurriculumSetList(param);

            return success("/pkCurriculumSet/setCurriculumSetList", ApiResult.SUCCESS_MESSAGE);

        }catch (Exception e){
            log.info(e.getMessage());
            if (e instanceof CheckedException) {
                return error("/pkCurriculumSet/setCurriculumSetList", e.getMessage());
            }
        }
        return error("/pkCurriculumSet/setCurriculumSetList", ErrorCode.ERROR_SYSTEM);
    }

}


package dckj.arrange.modules.controller;


import dckj.arrange.common.base.ApiBaseController;
import dckj.arrange.common.base.ApiResult;
import dckj.arrange.common.base.ErrorCode;
import dckj.arrange.common.entity.PkCurriculumSet;
import dckj.arrange.common.model.request.BaseReq;
import dckj.arrange.common.util.EmptyUtil;
import dckj.arrange.modules.feign.IPkCurriculumSetService;
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
    @ApiOperation(value = "获取课程设置列表", notes = "获取课程设置列表：taskId", httpMethod = "POST")
    public ApiResult getCurriculumSetList(@RequestBody BaseReq param){
        if(EmptyUtil.isEmpty(param) || EmptyUtil.isEmpty(param.getTaskId())) {
            return error("/pkCurriculumSet/getCurriculumSetList", ErrorCode.ERROR_MISS_PARAM);
        }
        return iPkCurriculumSetService.getCurriculumSetList(param);
    }


    /**
     * 设置课程设置列表
     * @param param
     * @return
     */
    @RequestMapping(value = "/setCurriculumSetList",method = RequestMethod.POST)
    @ApiOperation(value = "设置课程设置列表", notes = "设置课程设置列表： list-->id, mustNumber, choiceNumber, level(重要程度(1.高，2.中，3.低))", httpMethod = "POST")
    public ApiResult setCurriculumSetList(@RequestBody List<PkCurriculumSet> param){
        if(EmptyUtil.isEmpty(param)){
            return error("/pkCurriculumSet/setCurriculumSetList", ErrorCode.ERROR_MISS_PARAM);
        }
        return iPkCurriculumSetService.setCurriculumSetList(param);
    }

}


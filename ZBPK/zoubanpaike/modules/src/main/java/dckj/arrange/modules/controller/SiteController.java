package dckj.arrange.modules.controller;

import dckj.arrange.common.base.ApiBaseController;
import dckj.arrange.common.base.ApiResult;
import dckj.arrange.common.model.request.SiteVo;
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
@RequestMapping("room/site")
@Api(value = "room/site", description = "教学场地相关")
@Slf4j
public class SiteController extends ApiBaseController {
    @Autowired
    private FeginService feginService;

    @RequestMapping(value = "toInsertSite", method = {RequestMethod.POST})
    @ApiOperation(value = "增加教学场地类型", notes = "只需要传入教学场地即可", httpMethod = "POST")
    public ApiResult toInsertSite(@RequestBody SiteVo siteVo) {
        ApiResult apiResult = feginService.toInsertSite(siteVo);
        return apiResult;
    }

    @RequestMapping(value = "toGetSite", method = {RequestMethod.POST})
    @ApiOperation(value = "查询 教学场地", notes = "传入分页参数 学校编码 即可查询想要的", httpMethod = "POST")
    public ApiResult toGetSite(@RequestBody SiteVo siteVo) {
        ApiResult apiResult = feginService.toGetSite(siteVo);
        return apiResult;
    }

    @RequestMapping(value = "toGetSiteWithOutPage", method = {RequestMethod.POST})
    @ApiOperation(value = "查询无分页的教学场地", notes = "schoolCode\": \"学校编号\",\n" +
            "}", httpMethod = "POST")
    public ApiResult toGetSiteWithOutPage(@RequestBody SiteVo siteVo) {
        ApiResult apiResult = feginService.toGetSiteWithOutPage(siteVo);
        return apiResult;
    }

    @RequestMapping(value = "toDeleteSite", method = {RequestMethod.POST})
    @ApiOperation(value = "删除教学场地", notes = "传入单条记录的唯一ID即可删除", httpMethod = "POST")
    public ApiResult toDeleteSite(@RequestBody SiteVo siteVo) {
        ApiResult apiResult = feginService.toDeleteSite(siteVo);
        return apiResult;
    }

    @RequestMapping(value = "toUpdateSite", method = {RequestMethod.POST})
    @ApiOperation(value = "修改 教学场地", notes = "传入单条记录的唯一ID 以及修改参数 即可修改", httpMethod = "POST")
    public ApiResult toUpdateSite(@RequestBody SiteVo siteVo) {
        ApiResult apiResult = feginService.toUpdateSite(siteVo);
        return apiResult;
    }
}

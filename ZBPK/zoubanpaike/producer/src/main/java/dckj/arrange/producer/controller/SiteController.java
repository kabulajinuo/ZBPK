package dckj.arrange.producer.controller;

import com.github.pagehelper.PageInfo;
import dckj.arrange.common.base.ApiBaseController;
import dckj.arrange.common.base.ApiResult;
import dckj.arrange.common.base.ErrorCode;
import dckj.arrange.common.entity.PkSite;
import dckj.arrange.common.model.request.SiteVo;
import dckj.arrange.common.util.EmptyUtil;
import dckj.arrange.producer.service.SiteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("room/site")
@Api(value = "room/site", description = "教学场地相关")
@Slf4j
public class SiteController extends ApiBaseController{

    @Autowired
    private SiteService siteService;

    @RequestMapping(value = "toInsertSite", method = {RequestMethod.POST})
    @ApiOperation(value = "增加教学场地类型", notes = "{\n" +
            "  \"schoolCode\": \"学校编号\",\n" +
            "  \"siteName\": \"场地名称\"\n" +
            "}", httpMethod = "POST")
    public ApiResult toInsertSite(@RequestBody SiteVo siteVo) {
        if (EmptyUtil.isEmpty(siteVo)) {
            return error("room/site/toInsertSite", ErrorCode.ERROR_MISS_PARAM);
        }
        try {
            int i = siteService.toInsertSite(siteVo);
            if (i < 0) {
                return error("room/site/toInsertSite", ErrorCode.ERROR_SYSTEM);
            }
            return success("room/site/toInsertSite", ApiResult.SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.error("增加 教学场地错误：" + e.getMessage());
            return error("room/site/toInsertSite", ErrorCode.ERROR_SYSTEM);
        }
    }

    @RequestMapping(value = "toGetSiteWithOutPage", method = {RequestMethod.POST})
    @ApiOperation(value = "查询无分页的教学场地", notes = "schoolCode\": \"学校编号\",\n" +
            "}", httpMethod = "POST")
    public ApiResult toGetSiteWithOutPage(@RequestBody SiteVo siteVo) {
        if (EmptyUtil.isEmpty(siteVo.getSchoolCode())) {
            return error("room/site/toGetSiteWithOutPage", ErrorCode.ERROR_MISS_PARAM);
        }
        try {
            List<PkSite> pkSites = siteService.toGetSiteWithOutPage(siteVo);
            return success("room/site/toGetSiteWithOutPage", ApiResult.SUCCESS_MESSAGE, pkSites);
        } catch (Exception e) {
            log.error("查询无分页的教学场地错误：" + e.getMessage());
            return error("room/site/toGetSiteWithOutPage", ErrorCode.ERROR_SYSTEM);
        }
    }

    @RequestMapping(value = "toGetSite", method = {RequestMethod.POST})
    @ApiOperation(value = "查询教学场地", notes = "{\n" +
            "  \"pageFilterVo\": {\n" +
            "    \"pageNumber\": 1,\n" +
            "    \"pageSize\": 10,\n" +
            "  },\n" +
            "  \"schoolCode\": \"学校编号\",\n" +
            "}", httpMethod = "POST")
    public ApiResult toGetSite(@RequestBody SiteVo siteVo) {
        if (EmptyUtil.isEmpty(siteVo.getPageFilterVo().getPageNumber()) || EmptyUtil.isEmpty(siteVo.getPageFilterVo().getPageSize()) || EmptyUtil.isEmpty(siteVo.getSchoolCode())) {
            return error("room/site/toGetSite", ErrorCode.ERROR_MISS_PARAM);
        }
        try {
            PageInfo<PkSite> pkSitePageInfo = siteService.toGetSite(siteVo);
            return success("room/site/toGetSite", ApiResult.SUCCESS_MESSAGE, pkSitePageInfo);
        } catch (Exception e) {
            log.error("【查询教学场地】错误：" + e.getMessage());
            return error("room/site/toGetSite", ErrorCode.ERROR_SYSTEM);
        }
    }

    @RequestMapping(value = "toDeleteSite", method = {RequestMethod.POST})
    @ApiOperation(value = "删除教学场地", notes = "siteId", httpMethod = "POST")
    public ApiResult toDeleteSite(@RequestBody SiteVo siteVo) {
        if (EmptyUtil.isEmpty(siteVo)) {
            return error("room/type/toDeleteSite", ErrorCode.ERROR_MISS_PARAM);
        }
        try {
            int i = siteService.toDeleteSite(siteVo);
            if (i < 0) {
                return error("room/type/toDeleteSite", ErrorCode.ERROR_SYSTEM);
            }
            return success("room/type/toDeleteSite", ApiResult.SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.error("【删除教室类型】错误：" + e.getMessage());
            return error("room/type/toDeleteSite", ErrorCode.ERROR_SYSTEM);
        }
    }

    @RequestMapping(value = "toUpdateSite", method = {RequestMethod.POST})
    @ApiOperation(value = "修改 教学场地", notes = "{\n" +
            "  \"schoolCode\": \"学校编号\",\n" +
            "  \"siteId\": \"主键\",\n" +
            "  \"siteName\": \"场地名称\"\n" +
            "}", httpMethod = "POST")
    public ApiResult toUpdateSite(@RequestBody SiteVo siteVo) {
        if (EmptyUtil.isEmpty(siteVo)) {
            return error("room/type/toUpdateSite", ErrorCode.ERROR_MISS_PARAM);
        }
        try {
            int i = siteService.toUpdateSite(siteVo);
            if (i < 0) {
                return error("room/type/toUpdateSite", ErrorCode.ERROR_SYSTEM);
            }
            return success("room/type/toUpdateSite", ApiResult.SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.error("【修改 教室类型】错误：" + e.getMessage());
            return error("room/type/toUpdateSite", ErrorCode.ERROR_SYSTEM);
        }
    }
}

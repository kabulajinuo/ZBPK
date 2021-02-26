package dckj.arrange.producer.controller;


import dckj.arrange.common.base.ApiBaseController;
import dckj.arrange.common.base.ApiResult;
import dckj.arrange.common.base.ErrorCode;
import dckj.arrange.common.entity.PkClass;
import dckj.arrange.common.exception.CheckedException;
import dckj.arrange.common.model.request.PkClassListReq;
import dckj.arrange.common.model.response.PkClassListRes;
import dckj.arrange.common.util.poi.BaseDownloadUtil;
import dckj.arrange.producer.service.IPkClassService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


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
    @ApiOperation(value = "新增班级", notes = "新增班级：", httpMethod = "POST")
    public ApiResult saveClass(@RequestBody PkClass param){

        try {
            iPkClassService.saveClass(param);

            return success("/pkClass/saveClass", ApiResult.SUCCESS_MESSAGE);

        }catch (Exception e){
            if (e instanceof CheckedException) {
                log.info(e.getMessage());
                return error("/pkClass/saveClass", e.getMessage());
            }
        }
        return error("/pkClass/saveClass", ErrorCode.ERROR_SYSTEM);
    }


    /**
     * 查询班级列表
     * @param param
     * @return
     */
    @RequestMapping(value = "/getClassList",method = RequestMethod.POST)
    @ApiOperation(value = "查询班级列表", notes = "查询班级列表：className（非必传）， gradeId（非必传）", httpMethod = "POST")
    public ApiResult getClassList(@RequestBody PkClassListReq param){

        try {
            List<PkClassListRes> list =  iPkClassService.getClassList(param);

            return success("/pkClass/getClassList", ApiResult.SUCCESS_MESSAGE, list);

        }catch (Exception e){
            log.info(e.getMessage());
            if (e instanceof CheckedException) {
                return error("/pkClass/getClassList", e.getMessage());
            }
        }
        return error("/pkClass/getClassList", ErrorCode.ERROR_SYSTEM);
    }


    /**
     * 删除班级信息
     * @param param
     * @return
     */
    @RequestMapping(value = "/delClass",method = RequestMethod.POST)
    @ApiOperation(value = "删除班级信息", notes = "删除班级信息", httpMethod = "POST")
    public ApiResult delClass(@RequestBody PkClass param){

        try {
            iPkClassService.delClass(param);

            return success("/pkClass/delClass", ApiResult.SUCCESS_MESSAGE);

        }catch (Exception e){
            log.info(e.getMessage());
            if (e instanceof CheckedException) {
                return error("/pkClass/delClass", e.getMessage());
            }
        }
        return error("/pkClass/delClass", ErrorCode.ERROR_SYSTEM);
    }

    /**
     * 修改班级信息
     * @param param
     * @return
     */
    @RequestMapping(value = "/updateClass",method = RequestMethod.POST)
    @ApiOperation(value = "修改班级信息", notes = "修改班级信息", httpMethod = "POST")
    public ApiResult updateClass(@RequestBody PkClass param){

        try {
            iPkClassService.updateClass(param);

            return success("/pkClass/updateClass", ApiResult.SUCCESS_MESSAGE);

        }catch (Exception e){
            log.info(e.getMessage());
            if (e instanceof CheckedException) {
                return error("/pkClass/updateClass", e.getMessage());
            }
        }
        return error("/pkClass/updateClass", ErrorCode.ERROR_SYSTEM);
    }


    @RequestMapping(value = "/exportClassTemplate",method = RequestMethod.POST)
    @ApiOperation(value = "导出班级模板", notes = "导出班级模板", httpMethod = "POST")
    public void exportTemplate(HttpServletResponse response){
        boolean flag = BaseDownloadUtil.download(response, "班级表");
        if(!flag){
            log.info("下载班级表模板失败");
        }else {
            log.info("下载班级表模板成功");
        }
    }


    @RequestMapping(value = "/importClass",method = RequestMethod.POST)
    @ApiOperation(value = "导入班级模板", notes = "导入班级模板", httpMethod = "POST")
    public ApiResult importClass(@RequestParam(value = "file") MultipartFile file, @RequestParam(value = "schoolCode") String schoolCode){

        try {
            StringBuffer sb = iPkClassService.importClass(file, schoolCode);

            return success("/pkClass/importClass", ApiResult.SUCCESS_MESSAGE, sb);

        }catch (Exception e){
            log.info(e.getMessage());
            if (e instanceof CheckedException) {
                return error("/pkClass/importClass", e.getMessage());
            }
        }
        return error("/pkClass/importClass", ErrorCode.ERROR_SYSTEM);
    }



}

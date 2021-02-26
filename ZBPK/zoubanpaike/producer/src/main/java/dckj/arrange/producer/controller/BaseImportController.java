package dckj.arrange.producer.controller;

import dckj.arrange.common.base.ApiBaseController;
import dckj.arrange.common.base.ApiResult;
import dckj.arrange.common.base.ErrorCode;
import dckj.arrange.common.exception.CheckedException;
import dckj.arrange.common.util.poi.BaseDownloadUtil;
import dckj.arrange.producer.service.BaseImportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @Classname BaseImportController
 * @Description TODO
 * @Date 2019/8/26 20:40
 * @Created by JinPeng
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/baseImportController")
@Api(value = "/baseImportController", description = "排课基础信息导入Controller")
@Slf4j
public class BaseImportController extends ApiBaseController {

    @Autowired
    private BaseImportService baseImportService;

    @RequestMapping(value = "/exportTemplate",method = RequestMethod.POST)
    @ApiOperation(value = "导出排课信息模板", notes = "导出排课信息模板", httpMethod = "POST")
    public void exportTemplate(HttpServletResponse response){
            boolean flag = BaseDownloadUtil.download(response, "排课基础表");
            if(!flag){
                log.info("下载排课基础表模板失败");
            }else {
                log.info("下载排课基础表模板成功");
            }
    }


    @RequestMapping(value = "/importData",method = RequestMethod.POST)
    @ApiOperation(value = "导入排课信息", notes = "导入排课信息：", httpMethod = "POST")
    public ApiResult importData(@RequestParam(value = "file") MultipartFile file, @RequestParam(value = "taskId") String taskId,
            @RequestParam(value = "schoolCode") String schoolCode){

        try {
            StringBuffer sb = baseImportService.importData(file, taskId, schoolCode);

            return success("/pkClass/saveClass", ApiResult.SUCCESS_MESSAGE, sb);

        }catch (Exception e){
            if (e instanceof CheckedException) {
                log.info(e.getMessage());
                return error("/pkClass/saveClass", e.getMessage());
            }
        }
        return error("/pkClass/saveClass", ErrorCode.ERROR_SYSTEM);
    }



}

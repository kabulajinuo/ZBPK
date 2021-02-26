package dckj.arrange.producer.controller;

import com.github.pagehelper.PageInfo;
import dckj.arrange.common.base.ApiBaseController;
import dckj.arrange.common.base.ApiResult;
import dckj.arrange.common.base.ErrorCode;
import dckj.arrange.common.entity.PkRoomInfo;
import dckj.arrange.common.enums.FileEnum;
import dckj.arrange.common.model.request.RoomInfoVo;
import dckj.arrange.common.util.EmptyUtil;
import dckj.arrange.common.util.poi.ExcelUtil;
import dckj.arrange.producer.service.RoomInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("room/info")
@Api(value = "room/info", description = "教室信息相关")
@Slf4j
public class RoomInfoController extends ApiBaseController {

    @Autowired
    private RoomInfoService roomInfoService;

    @RequestMapping(value = "toInsertRoomInfo", method = {RequestMethod.POST})
    @ApiOperation(value = "增加教室信息", notes = "{\n" +
            "  \"bestLarger\": 0,\n" +
            "  \"createTime\": \"2019-08-18T09:18:30.314Z\",\n" +
            "  \"manQuantity\": 0,\n" +
            "  \"pageFilterVo\": {\n" +
            "    \"order\": \"string\",\n" +
            "    \"pageNumber\": 0,\n" +
            "    \"pageSize\": 0,\n" +
            "    \"sort\": \"string\"\n" +
            "  },\n" +
            "  \"roomId\": \"string\",\n" +
            "  \"roomName\": \"string\",\n" +
            "  \"roomType\": 0,\n" +
            "  \"schoolCode\": \"string\",\n" +
            "  \"siteId\": \"string\",\n" +
            "  \"siteName\": \"string\",\n" +
            "  \"subjectIds\": \"string\",\n" +
            "  \"subjectNames\": \"string\",\n" +
            "  \"typeName\": \"string\"\n" +
            "}", httpMethod = "POST")
    public ApiResult toInsertRoomInfo(@RequestBody @Valid RoomInfoVo roomInfoVo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【新增教室信息】参数不正确,RoomInfoVo", roomInfoVo.toString());
            return error("room/info/toInsertRoomType", ApiResult.SYSTEM_FAILED, bindingResult.getFieldError().getDefaultMessage());
        }
        try {
            int i = roomInfoService.toInsertRoomInfo(roomInfoVo);
            if (i < 0) {
                return error("room/info/toInsertRoomType", ErrorCode.ERROR_SYSTEM);
            }
            return success("room/info/toInsertRoomType", ApiResult.SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.error("【增加 教室信息 】错误：" + e.getMessage());
            return error("room/info/toInsertRoomType", ApiResult.SYSTEM_FAILED, e.getMessage());
        }
    }

    @RequestMapping(value = "toImportRoomInfoData", method = {RequestMethod.POST})
    @ApiOperation(value = "导入教室信息", notes = "保证数据正确", httpMethod = "POST")
    public ApiResult toImportRoomInfoData(MultipartFile file) {
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        if (!suffix.startsWith(FileEnum.Excels.getSuffix())) {
            return error("room/info/toImportRoomInfoData", ApiResult.SYSTEM_FAILED, "只能导入Excel表格");
        }
        if (!ExcelUtil.checkFileSize(file)) {
            return error("room/info/toImportRoomInfoData", ApiResult.SYSTEM_FAILED, "文件大小超出范围");
        }
        try {
            ExcelUtil<RoomInfoVo> util = new ExcelUtil<RoomInfoVo>(RoomInfoVo.class);
            List<RoomInfoVo> roomInfoVoList = util.importExcel(file.getInputStream());
            EmptyUtil.validate(roomInfoVoList);
            roomInfoService.toImportRoomInfo(roomInfoVoList);
            return success("room/info/toImportRoomInfoData", ApiResult.SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.error("【导入教室信息】 错误：" + e.getMessage());
            return error("room/info/toImportRoomInfoData", ApiResult.SYSTEM_FAILED, e.getMessage());
        }

    }

    @RequestMapping(value = "toGetRoomInfo", method = {RequestMethod.POST})
    @ApiOperation(value = "查询教室信息", notes = "{\n" +
            "  \"pageFilterVo\": {\n" +
            "    \"pageNumber\": 0,\n" +
            "    \"pageSize\": 0,\n" +
            "  },\n" +
            "  \"schoolCode\": \"00007\",\n" +
            "}", httpMethod = "POST")
    public ApiResult toGetRoomInfo(@RequestBody RoomInfoVo roomInfoVo) {
        if (EmptyUtil.isEmpty(roomInfoVo.getPageFilterVo().getPageNumber()) || EmptyUtil.isEmpty(roomInfoVo.getPageFilterVo().getPageSize()) || EmptyUtil.isEmpty(roomInfoVo.getSchoolCode())) {
            return error("room/type/toGetRoomInfo", ErrorCode.ERROR_MISS_PARAM);
        }
        try {
            PageInfo<RoomInfoVo> roomInfoVoPageInfo = roomInfoService.toGetRoomInfo(roomInfoVo);
            return success("room/type/toGetRoomInfo", ApiResult.SUCCESS_MESSAGE, roomInfoVoPageInfo);
        } catch (Exception e) {
            log.error("【查询 教室信息】 错误：" + e.getMessage());
            return error("room/type/toGetRoomInfo", ErrorCode.ERROR_SYSTEM);
        }
    }


    @RequestMapping(value = "toGetRoomInfoWithOutPage", method = {RequestMethod.POST})
    @ApiOperation(value = "根据场地ID查询无分页教室信息", notes = "siteId:场地ID", httpMethod = "POST")
    public ApiResult toGetRoomInfoWithOutPage(@RequestBody RoomInfoVo roomInfoVo) {
        if (EmptyUtil.isEmpty(roomInfoVo)) {
            return error("room/type/toGetRoomInfoWithOutPage", ErrorCode.ERROR_MISS_PARAM);
        }
        try {
            List<PkRoomInfo> pkRoomInfos = roomInfoService.toGetRoomInfoWithOutPage(roomInfoVo);
            return success("room/type/toGetRoomInfoWithOutPage", ApiResult.SUCCESS_MESSAGE, pkRoomInfos);
        } catch (Exception e) {
            log.error("【根据场地ID查询教室信息】 错误：" + e.getMessage());
            return error("room/type/toGetRoomInfoWithOutPage", ErrorCode.ERROR_SYSTEM);
        }
    }

    @RequestMapping(value = "toDeleteRoomInfo", method = {RequestMethod.POST})
    @ApiOperation(value = "删除教室信息", notes = "roomId", httpMethod = "POST")
    public ApiResult toDeleteRoomInfo(@RequestBody RoomInfoVo roomInfoVo) {
        if (EmptyUtil.isEmpty(roomInfoVo)) {
            return error("room/type/toDeleteRoomType", ErrorCode.ERROR_MISS_PARAM);
        }
        try {
            int i = roomInfoService.toDeleteRoomInfo(roomInfoVo);
            if (i < 0) {
                return error("room/type/toDeleteRoomType", ErrorCode.ERROR_SYSTEM);
            }
            return success("room/type/toDeleteRoomType", ApiResult.SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.error("【删除 教室信息 】错误：" + e.getMessage());
            return error("room/type/toDeleteRoomType", ErrorCode.ERROR_SYSTEM);
        }
    }

    @RequestMapping(value = "toUpdateRoomInfo", method = {RequestMethod.POST})
    @ApiOperation(value = "修改 教室信息", notes = "{\n" +
            "  \"bestLarger\": 最大容乃班级,\n" +
            "  \"manQuantity\": 最大容纳人数,\n" +
            "  \"roomId\": \"主键\",\n" +
            "  \"roomName\": \"教室名\",\n" +
            "  \"roomType\": 教室类型ID,\n" +
            "  \"schoolCode\": \"学校编号\",\n" +
            "  \"siteId\": \"场地ID\",\n" +
            "  \"subjectIds\": \"适用学科\",\n" +
            "}", httpMethod = "POST")
    public ApiResult toUpdateRoomInfo(@RequestBody RoomInfoVo roomInfoVo) {
        if (EmptyUtil.isEmpty(roomInfoVo)) {
            return error("room/type/toUpdateRoomInfo", ErrorCode.ERROR_MISS_PARAM);
        }
        try {
            int i = roomInfoService.toUpdateRoomInfo(roomInfoVo);
            if (i < 0) {
                return error("room/type/toUpdateRoomInfo", ErrorCode.ERROR_SYSTEM);
            }
            return success("room/type/toUpdateRoomInfo", ApiResult.SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.error("【修改 教室信息 】错误：" + e.getMessage());
            return error("room/type/toUpdateRoomInfo", ErrorCode.ERROR_SYSTEM);
        }
    }
}

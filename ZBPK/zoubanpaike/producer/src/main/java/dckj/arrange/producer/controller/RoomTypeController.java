package dckj.arrange.producer.controller;


import com.github.pagehelper.PageInfo;
import dckj.arrange.common.base.ApiBaseController;
import dckj.arrange.common.base.ApiResult;
import dckj.arrange.common.base.ErrorCode;
import dckj.arrange.common.entity.PkRoomType;
import dckj.arrange.common.model.request.RoomTypeVo;
import dckj.arrange.common.util.EmptyUtil;
import dckj.arrange.producer.service.RoomTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("room/type")
@Api(value = "room/type", description = "教室类型相关")
@Slf4j
public class RoomTypeController extends ApiBaseController {

    @Autowired
    private RoomTypeService roomTypeService;

    @RequestMapping(value = "toInsertRoomType", method = {RequestMethod.POST})
    @ApiOperation(value = "增加教室类型",notes = "{\n" +
            "  \"schoolCode\": \"学校编号\",\n" +
            "  \"typeName\": \"类型名称\"\n" +
            "}",httpMethod = "POST")
    public ApiResult toInsertRoomType(@RequestBody RoomTypeVo roomTypeVo) {
        if(EmptyUtil.isEmpty(roomTypeVo)){
            return  error("room/type/toInsertRoomType",ErrorCode.ERROR_MISS_PARAM);
        }
        try {
            int i = roomTypeService.toInsertRoomType(roomTypeVo);
            if (i < 0) {
                return error("room/type/toInsertRoomType", ErrorCode.ERROR_SYSTEM);
            }
            return success("room/type/toInsertRoomType", ApiResult.SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.error("增加 教室类型 错误：" + e.getMessage());
            return error("room/type/toInsertRoomType", ErrorCode.ERROR_SYSTEM);
        }
    }

    @RequestMapping(value = "toGetRoomType", method = {RequestMethod.POST})
    @ApiOperation(value = "查询教室类型",notes = "{\n" +
            "  \"pageFilterVo\": {\n" +
            "    \"pageNumber\": 1,\n" +
            "    \"pageSize\": 10,\n" +
            "  \"schoolCode\": \"学校编号\",\n" +
            "}",httpMethod = "POST")
    public ApiResult toGetRoomType(@RequestBody RoomTypeVo roomTypeVo){
        if(EmptyUtil.isEmpty(roomTypeVo)){
            return  error("room/type/toGetRoomType",ErrorCode.ERROR_MISS_PARAM);
        }
        try {
            PageInfo<PkRoomType> pkRoomTypePageInfo = roomTypeService.toGetRoomType(roomTypeVo);
            return  success("room/type/toGetRoomType",ApiResult.SUCCESS_MESSAGE,pkRoomTypePageInfo);
        } catch (Exception e) {
            log.error("查询 教室类型 错误："+e.getMessage());
            return error("room/type/toGetRoomType", ErrorCode.ERROR_SYSTEM);
        }
    }

    @RequestMapping(value = "toDeleteRoomType", method = {RequestMethod.POST})
    @ApiOperation(value = "删除教室类型",notes = "typeId",httpMethod = "POST")
    public ApiResult toDeleteRoomType( @RequestBody RoomTypeVo roomTypeVo) {
        if(EmptyUtil.isEmpty(roomTypeVo)){
            return  error("room/type/toDeleteRoomType",ErrorCode.ERROR_MISS_PARAM);
        }
        try {
            int i = roomTypeService.toDeleteRoomType(roomTypeVo);
            if (i < 0) {
                return error("room/type/toDeleteRoomType", ErrorCode.ERROR_SYSTEM);
            }
            return success("room/type/toDeleteRoomType", ApiResult.SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.error("删除 教室类型 错误：" + e.getMessage());
            return error("room/type/toDeleteRoomType", ErrorCode.ERROR_SYSTEM);
        }
    }

    @RequestMapping(value = "toUpdateRoomType", method = {RequestMethod.POST})
    @ApiOperation(value = "修改 教室类型",notes = "{\n" +
            "  \"schoolCode\": \"学校编号\",\n" +
            "  \"typeId\": 主键,\n" +
            "}",httpMethod = "POST")
    public ApiResult toUpdateRoomType(@RequestBody RoomTypeVo roomTypeVo) {
        if(EmptyUtil.isEmpty(roomTypeVo)){
            return  error("room/type/toUpdateRoomType",ErrorCode.ERROR_MISS_PARAM);
        }
        try {
            int i = roomTypeService.toUpdateRoomType(roomTypeVo);
            if (i < 0) {
                return error("room/type/toUpdateRoomType", ErrorCode.ERROR_SYSTEM);
            }
            return success("room/type/toUpdateRoomType", ApiResult.SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.error("修改 教室类型 错误：" + e.getMessage());
            return error("room/type/toUpdateRoomType", ErrorCode.ERROR_SYSTEM);
        }
    }
}

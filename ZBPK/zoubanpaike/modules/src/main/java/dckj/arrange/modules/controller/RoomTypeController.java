package dckj.arrange.modules.controller;

import dckj.arrange.common.base.ApiBaseController;
import dckj.arrange.common.base.ApiResult;
import dckj.arrange.common.model.request.RoomTypeVo;
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
@RequestMapping("room/type")
@Api(value = "room/type", description = "教室类型相关")
@Slf4j
public class RoomTypeController extends ApiBaseController {

    @Autowired
    private FeginService feginService;

    @RequestMapping(value = "toInsertRoomType", method = {RequestMethod.POST})
    @ApiOperation(value = "增加教室类型", notes = "只需要传入教室名称即可", httpMethod = "POST")
    public ApiResult toInsertRoomType(@RequestBody RoomTypeVo roomTypeVo) {
        ApiResult apiResult = feginService.toInsertRoomType(roomTypeVo);
        return apiResult;
    }

    @RequestMapping(value = "toGetRoomType", method = {RequestMethod.POST})
    @ApiOperation(value = "查询教室类型", notes = "传入分页参数 即可查询想要的", httpMethod = "POST")
    public ApiResult toGetRoomType(@RequestBody RoomTypeVo roomTypeVo) {
        ApiResult apiResult = feginService.toGetRoomType(roomTypeVo);
        return apiResult;
    }

    @RequestMapping(value = "toDeleteRoomType", method = {RequestMethod.POST})
    @ApiOperation(value = "删除教室类型", notes = "传入单条记录的唯一ID即可删除", httpMethod = "POST")
    public ApiResult toDeleteRoomType(@RequestBody RoomTypeVo roomTypeVo) {
        ApiResult apiResult = feginService.toDeleteRoomType(roomTypeVo);
        return apiResult;
    }

    @RequestMapping(value = "toUpdateRoomType", method = {RequestMethod.POST})
    @ApiOperation(value = "修改 教室类型", notes = "传入单条记录的唯一ID 以及修改参数 即可修改", httpMethod = "POST")
    public ApiResult toUpdateRoomType(@RequestBody RoomTypeVo roomTypeVo) {
        ApiResult apiResult = feginService.toUpdateRoomType(roomTypeVo);
        return apiResult;
    }
}

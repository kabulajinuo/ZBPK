package dckj.arrange.modules.controller;

import dckj.arrange.common.base.ApiBaseController;
import dckj.arrange.common.base.ApiResult;
import dckj.arrange.common.model.request.RoomInfoVo;
import dckj.arrange.modules.feign.FeginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("room/info")
@Api(value = "room/info", description = "教室信息相关")
@Slf4j
public class RoomInfoController extends ApiBaseController {
    @Autowired
    private FeginService feginService;

    @RequestMapping(value = "toInsertRoomInfo", method = {RequestMethod.POST})
    @ApiOperation(value = "增加教室信息", notes = "只需要传入相应参数即可不懂问技术人员", httpMethod = "POST")
    public ApiResult toInsertRoomInfo(@RequestBody @Valid RoomInfoVo roomInfoVo, BindingResult bindingResult) {
        ApiResult apiResult = feginService.toInsertRoomInfo(roomInfoVo);
        return apiResult;
    }

    @RequestMapping(value = "toGetRoomInfo", method = {RequestMethod.POST})
    @ApiOperation(value = "查询教室信息", notes = "传入分页参数 学校编码 即可查询想要的", httpMethod = "POST")
    public ApiResult toGetRoomInfo(@RequestBody RoomInfoVo roomInfoVo) {
        ApiResult apiResult = feginService.toGetRoomInfo(roomInfoVo);
        return apiResult;
    }

    @RequestMapping(value = "toGetRoomInfoWithOutPage", method = {RequestMethod.POST})
    @ApiOperation(value = "根据场地ID查询无分页教室信息", notes = "siteId:场地ID", httpMethod = "POST")
    public ApiResult toGetRoomInfoWithOutPage(@RequestBody RoomInfoVo roomInfoVo) {

        ApiResult apiResult = feginService.toGetRoomInfoWithOutPage(roomInfoVo);
        return apiResult;
    }

    @RequestMapping(value = "toDeleteRoomInfo", method = {RequestMethod.POST})
    @ApiOperation(value = "删除教室信息", notes = "传入单条记录的唯一ID即可删除", httpMethod = "POST")
    public ApiResult toDeleteRoomInfo(@RequestBody RoomInfoVo roomInfoVo) {
        ApiResult apiResult = feginService.toDeleteRoomInfo(roomInfoVo);
        return apiResult;
    }

    @RequestMapping(value = "toUpdateRoomInfo", method = {RequestMethod.POST})
    @ApiOperation(value = "修改 教室信息", notes = "传入单条记录的唯一ID 以及修改参数 即可修改", httpMethod = "POST")
    public ApiResult toUpdateRoomInfo(@RequestBody RoomInfoVo roomInfoVo) {
        ApiResult apiResult = feginService.toUpdateRoomInfo(roomInfoVo);
        return apiResult;
    }
}

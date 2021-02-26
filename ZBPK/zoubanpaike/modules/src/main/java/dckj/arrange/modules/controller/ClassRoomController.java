package dckj.arrange.modules.controller;

import dckj.arrange.common.base.ApiBaseController;
import dckj.arrange.common.base.ApiResult;
import dckj.arrange.common.model.request.ClassRoomReq;
import dckj.arrange.modules.feign.FeginService;
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
@RequestMapping("class/room")
@Api(value = "class/room", description = "班级设置所属教室相关")
@Slf4j
public class ClassRoomController extends ApiBaseController {
    @Autowired
    private FeginService feginService;

    @RequestMapping(value = "toGetClassRoom", method = {RequestMethod.POST})
    @ApiOperation(value = "查询班级所属教室信息", notes = "gradeId :当前任务的所选的年级ID,schoolCode学校编号", httpMethod = "POST")
    public ApiResult toGetClassRoom(@RequestBody ClassRoomReq classRoomReq) {
        ApiResult apiResult = feginService.toGetClassRoom(classRoomReq);
        return apiResult;
    }

    @RequestMapping(value = "toInsertClassRoom", method = {RequestMethod.POST})
    @ApiOperation(value = "增加/修改班级所属教室", notes = "[\n" +
            "  {\n" +
            "    \"classId\": \"班级ID\",\n" +
            "    \"className\": \"班级名字\",\n" +
            "    \"id\": \"主键\",\n" +
            "    \"roomId\": \"教室ID\",\n" +
            "    \"roomName\": \"教室名字\",\n" +
            "    \"siteId\": \"场地ID\",\n" +
            "    \"siteName\": \"场地名字\",\n" +
            "    \"taskId\": \"任务ID\",\n" +
            "  }\n" +
            "]如果是修改的话 主键就必传 否则就是增加", httpMethod = "POST")
    public ApiResult toInsertClassRoom(@RequestBody List<ClassRoomReq> classRoomReqs) {
        ApiResult apiResult = feginService.toInsertClassRoom(classRoomReqs);
        return apiResult;
    }
}

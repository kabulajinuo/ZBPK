package dckj.arrange.producer.service;

import com.github.pagehelper.PageInfo;
import dckj.arrange.common.entity.PkRoomInfo;
import dckj.arrange.common.model.request.RoomInfoVo;

import java.util.List;

public interface RoomInfoService {
    int toInsertRoomInfo(RoomInfoVo roomInfoVo);

    void toInsertBatchTeacherInfo(List<RoomInfoVo> roomInfoVos);

    PageInfo<RoomInfoVo> toGetRoomInfo(RoomInfoVo roomInfoVo);

    int toDeleteRoomInfo(RoomInfoVo roomInfoVo);

    int toUpdateRoomInfo(RoomInfoVo roomInfoVo);

    List<PkRoomInfo> toGetRoomInfoWithOutPage(RoomInfoVo roomInfoVo);

    void toImportRoomInfo(List<RoomInfoVo> list);

}

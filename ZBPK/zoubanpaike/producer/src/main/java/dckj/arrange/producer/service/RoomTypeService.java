package dckj.arrange.producer.service;

import com.github.pagehelper.PageInfo;
import dckj.arrange.common.entity.PkRoomType;
import dckj.arrange.common.model.request.RoomTypeVo;

public interface RoomTypeService {
    int toInsertRoomType(RoomTypeVo roomTypeVo);
    PageInfo<PkRoomType> toGetRoomType(RoomTypeVo roomTypeVo);
    int toDeleteRoomType(RoomTypeVo roomTypeVo);
    int toUpdateRoomType(RoomTypeVo roomTypeVo);
    Integer toGetTypeIds(String typeName,String schoolCode);
}

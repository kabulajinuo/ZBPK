package dckj.arrange.common.model.request;

import dckj.arrange.common.entity.PkRoomType;
import lombok.Data;

@Data
public class RoomTypeVo extends PkRoomType {
    private PageFilterVo pageFilterVo;
}

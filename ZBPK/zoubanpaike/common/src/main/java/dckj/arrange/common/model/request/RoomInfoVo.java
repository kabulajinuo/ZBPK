package dckj.arrange.common.model.request;

import dckj.arrange.common.annotation.Excel;
import dckj.arrange.common.entity.PkRoomInfo;
import lombok.Data;

@Data
public class RoomInfoVo extends PkRoomInfo {
    @Excel(name = "所在教学场地")
    private String siteName;
    @Excel(name = "教室类型")
    private String typeName;
    private String subjectNames;
    private PageFilterVo pageFilterVo;
}

package dckj.arrange.common.model.request;

import dckj.arrange.common.entity.PkClassRoom;
import lombok.Data;

@Data
public class ClassRoomReq extends PkClassRoom {

    private String className;
    private String gradeId;
    private String schoolCode;

}

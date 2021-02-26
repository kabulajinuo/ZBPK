package dckj.arrange.common.model.request;

import dckj.arrange.common.entity.PkTeacherPosition;
import lombok.Data;

@Data
public class TeacherPositionVo extends PkTeacherPosition {
    private PageFilterVo pageFilterVo;
    private String postIds;
}

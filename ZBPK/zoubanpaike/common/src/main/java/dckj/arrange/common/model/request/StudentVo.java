package dckj.arrange.common.model.request;

import dckj.arrange.common.entity.PkStudent;
import lombok.Data;

@Data
public class StudentVo extends PkStudent {
    private String gradeName;
    private String className;
    private PageFilterVo pageFilterVo;
}

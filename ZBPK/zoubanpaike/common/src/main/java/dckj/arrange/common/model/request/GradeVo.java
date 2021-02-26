package dckj.arrange.common.model.request;

import dckj.arrange.common.entity.PkGrade;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GradeVo extends PkGrade {
    @ApiModelProperty(value = "年级所含班级数 不必传")
    private Integer classNum;
    @ApiModelProperty(value = "年级管理者 不必传")
    private String managerNames;
    private PageFilterVo pageFilterVo;
}

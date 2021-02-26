package dckj.arrange.common.model.request;

import dckj.arrange.common.annotation.Excel;
import dckj.arrange.common.entity.PkTeacherInfo;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class TeacherInfoVo extends PkTeacherInfo {

    /**
     * @Pattern(regexp = "^[1](([3][0-9])|([4][5,7,9])|([5][0-9])|([6][6])|([7][3,5,6,7,8])|([8][0-9])|([9][8,9]))[0-9]{8}$",message = "手机号不正确")
     */

    @NotEmpty(message = "性别不能为空")
    @Excel(name="性别")
    private String gender;
    private String postNames;
    private String subjectNames;
    @NotEmpty(message = "手机号不能为空")
    @Size(min = 0, max = 11, message = "手机号码长度不能超过11位")
    @Excel(name="联系电话")
    private String phone;
    private String password;
    private PageFilterVo pageFilterVo;

}

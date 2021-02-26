package dckj.arrange.common.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Xiangjing
 * @since 2019-08-15
 */
@TableName("pk_grade")
@Data
public class PkGrade extends Model<PkGrade> {

    private static final long serialVersionUID = 1L;

    @TableId("grade_id")
    private String gradeId;
    /**
     * 年纪名字
     */
    @TableField("grade_name")
    @ApiModelProperty(value = "年纪名字 必传")
    @NotEmpty(message = "年级不能为空")
    private String gradeName;
    /**
     * 年纪管理者
     */
    @TableField("grade_managers")
    @ApiModelProperty(value = "年纪管理者 必传 以逗号分隔 ")
    @NotEmpty(message = "年纪管理者不能为空")
    private String gradeManagers;
    /**
     * 学校Code
     */
    @TableField("school_code")
    @ApiModelProperty(value = "学校Code 必传")
    @NotEmpty(message = "学校code不能为空")
    private String schoolCode;
    /**
     * 创建时间
     */
    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @TableField("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;


    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getGradeManagers() {
        return gradeManagers;
    }

    public void setGradeManagers(String gradeManagers) {
        this.gradeManagers = gradeManagers;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.gradeId;
    }

    @Override
    public String toString() {
        return "PkGrade{" +
        ", gradeId=" + gradeId +
        ", gradeName=" + gradeName +
        ", gradeManagers=" + gradeManagers +
        ", schoolCode=" + schoolCode +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}

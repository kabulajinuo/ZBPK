package dckj.arrange.common.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Xiangjing
 * @since 2019-08-13
 */
@TableName("pk_subject")
public class PkSubject extends Model<PkSubject> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "subject_id", type = IdType.AUTO)
    private Integer subjectId;
    /**
     * 科目名称
     */
    @TableField("subject_name")
    @ApiModelProperty(value = "科目名称 必传")
    private String subjectName;
    /**
     * 创建时间
     */
    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间 不必传")
    private Date createTime;
    /**
     * 学校编号
     */
    @TableField("school_code")
    @ApiModelProperty(value = "学校Code 必传")
    private String schoolCode;

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    @Override
    protected Serializable pkVal() {
        return this.subjectId;
    }

    @Override
    public String toString() {
        return "PkSubject{" +
        ", subjectId=" + subjectId +
        ", subjectName=" + subjectName +
        ", createTime=" + createTime +
        "}";
    }
}

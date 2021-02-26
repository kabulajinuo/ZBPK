package dckj.arrange.common.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import dckj.arrange.common.annotation.Excel;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author Xiangjing
 * @since 2019-08-15
 */
@TableName("pk_teacher_info")
@Data
public class PkTeacherInfo extends Model<PkTeacherInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 绑定用户
     */
    @TableId("user_id")
    private String userId;
    /**
     * 职务
     */
    @TableField("post_ids")
    @NotEmpty(message = "职务不能为空")
    @Excel(name = "职务")
    private String postIds;
    /**
     * 学科
     */
    @TableField("subject_ids")
    @NotEmpty(message = "学科不能为空")
    @Excel(name = "授课科目")
    private String subjectIds;
    /**
     * 工号
     */
    @TableField("job_no")
    @NotEmpty(message = "工号不能为空")
    @Excel(name = "工号")
    private String jobNo;
    /**
     * 教师名字
     */
    @TableField("teacher_name")
    @NotEmpty(message = "教师名字不能为空")
    @Excel(name = "姓名")
    private String teacherName;
    /**
     * 学校编号
     */
    @TableField("school_code")
    @NotEmpty(message = "学校编号不能为空")
    @Excel(name = "学校编号")
    private String schoolCode;
    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @TableField("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPostIds() {
        return postIds;
    }

    public void setPostIds(String postIds) {
        this.postIds = postIds;
    }

    public String getSubjectIds() {
        return subjectIds;
    }

    public void setSubjectIds(String subjectIds) {
        this.subjectIds = subjectIds;
    }

    public String getJobNo() {
        return jobNo;
    }

    public void setJobNo(String jobNo) {
        this.jobNo = jobNo;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
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
        return this.userId;
    }

    @Override
    public String toString() {
        return "PkTeacherInfo{" +
        ", userId=" + userId +
        ", postIds=" + postIds +
        ", subjectIds=" + subjectIds +
        ", jobNo=" + jobNo +
        ", teacherName=" + teacherName +
        ", schoolCode=" + schoolCode +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}

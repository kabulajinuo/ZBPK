package dckj.arrange.common.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 排课任务表
 * </p>
 *
 * @author jiangyong
 * @since 2019-08-17
 */
@TableName("pk_task")
public class PkTask extends Model<PkTask> {

    private static final long serialVersionUID = 1L;

    /**
     * 排课任务id
     */
    private String id;
    /**
     * 排课任务名称
     */
    @TableField("task_name")
    private String taskName;
    /**
     * 年级id
     */
    @TableField("grade_id")
    private String gradeId;
    /**
     * 年级
     */
    @TableField("grade_name")
    private String gradeName;
    /**
     * 排课班级总数
     */
    @TableField("class_number")
    private Integer classNumber;
    /**
     * 学年
     */
    @TableField("school_year")
    private String schoolYear;
    /**
     * 学期类型 0-上学期 1-下学期
     */
    @TableField("semester_type")
    private Integer semesterType;
    /**
     * 走班类型 0-不走班 1-小走班 2大走班 3全走班
     */
    @TableField("pk_type")
    private Integer pkType;
    /**
     * 选课模式 0-常规排课 1-“3+1+2"模式” 2- “"6选3"模式”
     */
    @TableField("xk_mode")
    private Integer xkMode;
    /**
     * 学校编号
     */
    @TableField("school_code")
    private String schoolCode;
    /**
     * 0-未发布 1-调整中 2-已完结 3-已发布
     */
    private Integer type;
    /**
     * 操作人id（登录人权限账号id）
     */
    @TableField("user_id")
    private String userId;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

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

    public Integer getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(Integer classNumber) {
        this.classNumber = classNumber;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public Integer getSemesterType() {
        return semesterType;
    }

    public void setSemesterType(Integer semesterType) {
        this.semesterType = semesterType;
    }

    public Integer getPkType() {
        return pkType;
    }

    public void setPkType(Integer pkType) {
        this.pkType = pkType;
    }

    public Integer getXkMode() {
        return xkMode;
    }

    public void setXkMode(Integer xkMode) {
        this.xkMode = xkMode;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
        return this.id;
    }

    @Override
    public String toString() {
        return "PkTask{" +
        ", id=" + id +
        ", taskName=" + taskName +
        ", gradeId=" + gradeId +
        ", gradeName=" + gradeName +
        ", classNumber=" + classNumber +
        ", schoolYear=" + schoolYear +
        ", semesterType=" + semesterType +
        ", pkType=" + pkType +
        ", xkMode=" + xkMode +
        ", schoolCode=" + schoolCode +
        ", type=" + type +
        ", userId=" + userId +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}

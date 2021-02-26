package dckj.arrange.common.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 排课基础信息表
 * </p>
 *
 * @author PengJin
 * @since 2019-08-29
 */
@TableName("pk_base_detail")
public class PkBaseDetail extends Model<PkBaseDetail> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;
    /**
     * 任务id
     */
    @TableField("task_id")
    private String taskId;
    /**
     * 班级id
     */
    @TableField("class_id")
    private String classId;
    /**
     * 科目id
     */
    @TableField("subject_id")
    private Integer subjectId;
    /**
     * 单节科数
     */
    @TableField("single_num")
    private Integer singleNum;
    /**
     * 连堂数
     */
    @TableField("continuou_num")
    private Integer continuouNum;
    /**
     * 科目名称
     */
    @TableField("subject_name")
    private String subjectName;
    /**
     * 教师id
     */
    @TableField("teacher_id")
    private String teacherId;
    /**
     * 教师名称
     */
    @TableField("teacher_name")
    private String teacherName;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getSingleNum() {
        return singleNum;
    }

    public void setSingleNum(Integer singleNum) {
        this.singleNum = singleNum;
    }

    public Integer getContinuouNum() {
        return continuouNum;
    }

    public void setContinuouNum(Integer continuouNum) {
        this.continuouNum = continuouNum;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
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
        return "PkBaseDetail{" +
        ", id=" + id +
        ", classId=" + classId +
        ", subjectId=" + subjectId +
        ", singleNum=" + singleNum +
        ", continuouNum=" + continuouNum +
        ", subjectName=" + subjectName +
        ", teacherId=" + teacherId +
        ", teacherName=" + teacherName +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}

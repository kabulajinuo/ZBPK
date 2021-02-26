package dckj.arrange.common.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 教学任务表
 * </p>
 *
 * @author Xiangjing
 * @since 2019-08-17
 */
@TableName("pk_teaching_task")
public class PkTeachingTask extends Model<PkTeachingTask> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;
    /**
     * 科目id
     */
    @TableField("subject_id")
    private Integer subjectId;
    /**
     * 连堂是否处理完（0.未处理，1.已处理）
     */
    @TableField("is_dispose")
    private Integer isDispose;
    /**
     * 连堂次数
     */
    @TableField("continuou_num")
    private Integer continuouNum;
    /**
     * 任务id
     */
    @TableField("task_id")
    private String taskId;
    /**
     * 教师id
     */
    @TableField("teacher_id")
    private String teacherId;
    /**
     * 教师姓名
     */
    @TableField("teacher_name")
    private String teacherName;
    /**
     * 必选班级数
     */
    @TableField("must_number")
    private Integer mustNumber;
    /**
     * 非必选班级数
     */
    @TableField("choice_number")
    private Integer choiceNumber;
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

    public Integer getIsDispose() {
        return isDispose;
    }

    public void setIsDispose(Integer isDispose) {
        this.isDispose = isDispose;
    }

    public String getTaskId() {
        return taskId;
    }

    public Integer getContinuouNum() {
        return continuouNum;
    }

    public void setContinuouNum(Integer continuouNum) {
        this.continuouNum = continuouNum;
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

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
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

    public Integer getMustNumber() {
        return mustNumber;
    }

    public void setMustNumber(Integer mustNumber) {
        this.mustNumber = mustNumber;
    }

    public Integer getChoiceNumber() {
        return choiceNumber;
    }

    public void setChoiceNumber(Integer choiceNumber) {
        this.choiceNumber = choiceNumber;
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
        return "PkTeachingTask{" +
        ", id=" + id +
        ", subjectId=" + subjectId +
        ", teacherId=" + teacherId +
        ", teacherName=" + teacherName +
        ", mustNumber=" + mustNumber +
        ", choiceNumber=" + choiceNumber +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}

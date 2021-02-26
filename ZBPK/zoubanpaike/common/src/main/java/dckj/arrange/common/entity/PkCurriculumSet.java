package dckj.arrange.common.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 课程设置表
 * </p>
 *
 * @author Xiangjing
 * @since 2019-08-17
 */
@TableName("pk_curriculum_set")
public class PkCurriculumSet extends Model<PkCurriculumSet> {

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
     * 任务id
     */
    @TableField("task_id")
    private String taskId;
    /**
     * 课程类型（1. 必考，2.选考）
     */
    @TableField("curriculum_type")
    private Integer curriculumType;
    /**
     * 必考节次/每周
     */
    @TableField("must_number")
    private Integer mustNumber;
    /**
     * 年级id
     */
    @TableField("grade_id")
    private String gradeId;
    /**
     * 选考节次/每周
     */
    @TableField("choice_number")
    private Integer choiceNumber;
    /**
     * 重要程度(1.高，2.中，3.低)
     */
    @TableField("level")
    private Integer level;
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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

    public Integer getCurriculumType() {
        return curriculumType;
    }

    public void setCurriculumType(Integer curriculumType) {
        this.curriculumType = curriculumType;
    }

    public Integer getMustNumber() {
        return mustNumber;
    }

    public void setMustNumber(Integer mustNumber) {
        this.mustNumber = mustNumber;
    }

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
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
        return "PkCurriculumSet{" +
        ", id=" + id +
        ", taskId=" + taskId +
        ", subjectId=" + subjectId +
        ", curriculumType=" + curriculumType +
        ", mustNumber=" + mustNumber +
        ", gradeId=" + gradeId +
        ", choiceNumber=" + choiceNumber +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}

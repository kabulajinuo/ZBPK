package dckj.arrange.common.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 课时详细表
 * </p>
 *
 * @author Xiangjing
 * @since 2019-08-18
 */
@TableName("pk_lesson_detail")
@Data
public class PkLessonDetail extends Model<PkLessonDetail> {

    private static final long serialVersionUID = 1L;

    @TableField("task_id")
    @NotEmpty(message = "任务ID不能为空")
    private String taskId;
    /**
     * 课节名称
     */
    @TableField("lesson_name")
    @NotEmpty(message = "课节名称不能为空")
    private String lessonName;
    /**
     * 开始
     */
    @TableField("start_time")
    @NotEmpty(message = "开始时钟不能为空")
    private String startTime;
    /**
     * 结束
     */
    @TableField("end_time")
    @NotEmpty(message = "结束时钟不能为空")
    private String endTime;
    /**
     * 时长
     */
    @TableField("detail_duration")
    @NotNull(message = "每节课时长不能为空")
    private Integer detailDuration;
    /**
     * 休息时长
     */
    @TableField("free_time")
    @NotNull(message = "休息时长不能为空")
    private Integer freeTime;
    /**
     * 重要程度
     */
    @NotNull(message = "重要程度不能为空")
    private Integer importance;
    /**
     * 学校编号
     */
    @TableField("school_code")
    @NotEmpty(message = "学校编号不能为空")
    private String schoolCode;
    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;


    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public Integer getDetailDuration() {
        return detailDuration;
    }

    public void setDetailDuration(Integer detailDuration) {
        this.detailDuration = detailDuration;
    }

    public Integer getFreeTime() {
        return freeTime;
    }

    public void setFreeTime(Integer freeTime) {
        this.freeTime = freeTime;
    }

    public Integer getImportance() {
        return importance;
    }

    public void setImportance(Integer importance) {
        this.importance = importance;
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

    @Override
    protected Serializable pkVal() {
        return this.taskId;
    }

    @Override
    public String toString() {
        return "PkLessonDetail{" +
        ", taskId=" + taskId +
        ", lessonName=" + lessonName +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        ", detailDuration=" + detailDuration +
        ", freeTime=" + freeTime +
        ", importance=" + importance +
        ", schoolCode=" + schoolCode +
        ", createTime=" + createTime +
        "}";
    }
}

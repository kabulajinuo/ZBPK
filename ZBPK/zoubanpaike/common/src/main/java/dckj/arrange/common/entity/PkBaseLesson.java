package dckj.arrange.common.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 课时基础表
 * </p>
 *
 * @author Xiangjing
 * @since 2019-08-18
 */
@TableName("pk_base_lesson")
@Data
public class PkBaseLesson extends Model<PkBaseLesson> {

    private static final long serialVersionUID = 1L;

    /**
     * 任务ID
     */
    @TableId("task_id")
    @NotEmpty(message = "任务ID不能为空")
    private String taskId;
    /**
     * 开始周期
     */
    @TableField("start_day")
    @NotNull(message = "开始周期不能为空")
    private Integer startDay;
    /**
     * 结束周期
     */
    @TableField("end_day")
    @NotNull(message = "结束周期不能为空")
    private Integer endDay;
    /**
     * 上午课节
     */
    @NotNull(message = "上午课节不能为空")
    private Integer morning;
    /**
     * 下午课节
     */
    @NotNull(message = "下午课节不能为空")
    private Integer afternoon;
    /**
     * 每节课时长
     */
    @NotNull(message = "每节课时长")
    private Integer duration;
    /**
     * 开始时间
     */
    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    /**
     * 学校编号
     */
    @TableField("school_code")
    @NotEmpty(message = "学校编号不能为空")
    private String schoolCode;


    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Integer getStartDay() {
        return startDay;
    }

    public void setStartDay(Integer startDay) {
        this.startDay = startDay;
    }

    public Integer getEndDay() {
        return endDay;
    }

    public void setEndDay(Integer endDay) {
        this.endDay = endDay;
    }

    public Integer getMorning() {
        return morning;
    }

    public void setMorning(Integer morning) {
        this.morning = morning;
    }

    public Integer getAfternoon() {
        return afternoon;
    }

    public void setAfternoon(Integer afternoon) {
        this.afternoon = afternoon;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
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

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    @Override
    protected Serializable pkVal() {
        return this.taskId;
    }

    @Override
    public String toString() {
        return "PkBaseLesson{" +
        ", taskId=" + taskId +
        ", startDay=" + startDay +
        ", endDay=" + endDay +
        ", morning=" + morning +
        ", afternoon=" + afternoon +
        ", duration=" + duration +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", schoolCode=" + schoolCode +
        "}";
    }
}

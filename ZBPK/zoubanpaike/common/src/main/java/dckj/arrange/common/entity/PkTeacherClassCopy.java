package dckj.arrange.common.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 教师任教课程表
 * </p>
 *
 * @author Jiangyong
 * @since 2019-08-22
 */
@TableName("pk_teacher_class_copy")
public class PkTeacherClassCopy extends Model<PkTeacherClassCopy> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;
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
     * 班级id
     */
    @TableField("class_id")
    private String classId;
    /**
     * 班级名称
     */
    @TableField("class_name")
    private String className;
    /**
     * 任务id
     */
    @TableField("task_id")
    private String taskId;
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

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
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
        return "PkTeacherClassCopy{" +
        ", id=" + id +
        ", teacherId=" + teacherId +
        ", teacherName=" + teacherName +
        ", classId=" + classId +
        ", className=" + className +
        ", taskId=" + taskId +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}

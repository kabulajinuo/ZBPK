package dckj.arrange.common.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 总课程表
 * </p>
 *
 * @author Pengjin
 * @since 2019-08-19
 */
@TableName("pk_class_schedule")
public class PkClassSchedule extends Model<PkClassSchedule> {

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
     * 星期
     */
    private String week;
    /**
     * 节次
     */
    private String section;
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
     * 科目id
     */
    @TableField("subject_id")
    private Integer subjectId;
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
     * 教师姓名
     */
    @TableField("teacher_name")
    private String teacherName;
    /**
     * 场地id
     */
    @TableField("site_id")
    private String siteId;
    /**
     * 场地名称
     */
    @TableField("site_name")
    private String siteName;
    /**
     * 教室id
     */
    @TableField("room_id")
    private String roomId;
    /**
     * 教室名称
     */
    @TableField("room_name")
    private String roomName;
    /**
     * 是否预设（0.未预设，1.已预设）
     */
    @TableField("is_preinstall")
    private Integer isPreinstall;
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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
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

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
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

    public Integer getIsPreinstall() {
        return isPreinstall;
    }

    public void setIsPreinstall(Integer isPreinstall) {
        this.isPreinstall = isPreinstall;
    }

    @Override
    public String toString() {
        return "PkClassSchedule{" +
        ", id=" + id +
        ", taskId=" + taskId +
        ", week=" + week +
        ", section=" + section +
        ", classId=" + classId +
        ", className=" + className +
        ", subjectId=" + subjectId +
        ", subjectName=" + subjectName +
        ", teacherId=" + teacherId +
        ", teacherName=" + teacherName +
        ", siteId=" + siteId +
        ", siteName=" + siteName +
        ", roomId=" + roomId +
        ", roomName=" + roomName +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}

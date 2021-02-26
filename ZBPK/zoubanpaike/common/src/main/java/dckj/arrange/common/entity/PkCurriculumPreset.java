package dckj.arrange.common.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 课程预设操作记录表
 * </p>
 *
 * @author Jiangyong
 * @since 2019-08-19
 */
@TableName("pk_curriculum_preset")
public class PkCurriculumPreset extends Model<PkCurriculumPreset> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;
    /**
     * 课程任务id
     */
    @TableField("task_id")
    private String taskId;
    /**
     * 科目id
     */
    @TableField("subject_id")
    private String subjectId;
    /**
     * 科目名字
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
     * 班级id
     */
    @TableField("class_id")
    private String classId;
    /**
     * 班级名字
     */
    @TableField("class_name")
    private String className;
    /**
     * 年级id
     */
    @TableField("greade_id")
    private String greadeId;
    /**
     * 年级名字
     */
    @TableField("greade_name")
    private String greadeName;
    /**
     * 已排总课时
     */
    @TableField("p_count_class_sk_num")
    private Integer pCountClassSkNum;
    /**
     * 总课时
     */
    @TableField("count_class_sk_num")
    private Integer countClassSkNum;
    /**
     * 已排任教老师数
     */
    @TableField("p_teachea_num")
    private Integer pTeacheaNum;
    /**
     * 任教老师总数
     */
    @TableField("teachea_num")
    private Integer teacheaNum;
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

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
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

    public String getGreadeId() {
        return greadeId;
    }

    public void setGreadeId(String greadeId) {
        this.greadeId = greadeId;
    }

    public String getGreadeName() {
        return greadeName;
    }

    public void setGreadeName(String greadeName) {
        this.greadeName = greadeName;
    }

    public Integer getpCountClassSkNum() {
        return pCountClassSkNum;
    }

    public void setpCountClassSkNum(Integer pCountClassSkNum) {
        this.pCountClassSkNum = pCountClassSkNum;
    }

    public Integer getCountClassSkNum() {
        return countClassSkNum;
    }

    public void setCountClassSkNum(Integer countClassSkNum) {
        this.countClassSkNum = countClassSkNum;
    }

    public Integer getpTeacheaNum() {
        return pTeacheaNum;
    }

    public void setpTeacheaNum(Integer pTeacheaNum) {
        this.pTeacheaNum = pTeacheaNum;
    }

    public Integer getTeacheaNum() {
        return teacheaNum;
    }

    public void setTeacheaNum(Integer teacheaNum) {
        this.teacheaNum = teacheaNum;
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
        return "PkCurriculumPreset{" +
        ", id=" + id +
        ", taskId=" + taskId +
        ", subjectId=" + subjectId +
        ", subjectName=" + subjectName +
        ", teacherId=" + teacherId +
        ", teacherName=" + teacherName +
        ", classId=" + classId +
        ", className=" + className +
        ", greadeId=" + greadeId +
        ", greadeName=" + greadeName +
        ", pCountClassSkNum=" + pCountClassSkNum +
        ", countClassSkNum=" + countClassSkNum +
        ", pTeacheaNum=" + pTeacheaNum +
        ", teacheaNum=" + teacheaNum +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}

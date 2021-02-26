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
 * 学生表
 * </p>
 *
 * @author Xiangjing
 * @since 2019-08-17
 */
@TableName("pk_student")
@Data
public class PkStudent extends Model<PkStudent> {

    private static final long serialVersionUID = 1L;

    @TableId("user_id")
    private String userId;
    /**
     * 年级ID
     */
    @TableField("grade_id")
    @NotEmpty(message = "年级不能为空")
    @Excel(name = "所属年级")
    private String gradeId;
    /**
     * 班级ID
     */
    @TableField("class_id")
    @NotEmpty(message = "班级不能为空")
    @Excel(name = "所属班级")
    private String classId;
    /**
     * 学号
     */
    @TableField("stu_no")
    @NotEmpty(message = "学号不能为空")
    @Excel(name = "学号")
    private String stuNo;
    /**
     * 学生名字
     */
    @TableField("stu_name")
    @NotEmpty(message = "姓名不能为空")
    @Excel(name = "姓名")
    private String stuName;
    /**
     * 性别(0男,1女)
     */
    @TableField("stu_sex")
    @NotEmpty(message = "性别不能为空")
    @Excel(name = "性别")
    private String stuSex;
    /**
     * 就读方式（0寄宿生 1走读生）
     */
    @TableField("reading_way")
    @NotEmpty(message = "就读方式不能为空")
    @Excel(name = "就读方式")
    private String readingWay;
    /**
     * 入学年份
     */
    @TableField("start_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Excel(name = "入学年份")
    private Date startTime;
    /**
     * 是否毕业（0未毕业 1毕业）
     */
    @TableField("is_graduate")
    @NotEmpty(message = "是否毕业不能为空")
    @Excel(name = "是否毕业")
    private String isGraduate;
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

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuSex() {
        return stuSex;
    }

    public void setStuSex(String stuSex) {
        this.stuSex = stuSex;
    }

    public String getReadingWay() {
        return readingWay;
    }

    public void setReadingWay(String readingWay) {
        this.readingWay = readingWay;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getIsGraduate() {
        return isGraduate;
    }

    public void setIsGraduate(String isGraduate) {
        this.isGraduate = isGraduate;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

    @Override
    public String toString() {
        return "PkStudent{" +
                ", userId=" + userId +
                ", gradeId=" + gradeId +
                ", classId=" + classId +
                ", stuNo=" + stuNo +
                ", stuName=" + stuName +
                ", stuSex=" + stuSex +
                ", readingWay=" + readingWay +
                ", startTime=" + startTime +
                ", isGraduate=" + isGraduate +
                ", schoolCode=" + schoolCode +
                "}";
    }
}

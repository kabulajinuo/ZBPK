package dckj.arrange.common.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 班级表
 * </p>
 *
 * @author Pengjin
 * @since 2019-08-14
 */
@TableName("pk_class")
public class PkClass extends Model<PkClass> {

    private static final long serialVersionUID = 1L;

    /**
     * 班级id
     */
    private String id;
    /**
     * 班级名称
     */
    @TableField("class_name")
    @Excel(name = "班级名字", width=15)
    private String className;
    /**
     * 学校code
     */
    @TableField("school_code")
    private String schoolCode;
    /**
     * 班主任id
     */
    @TableField("adviser_id")
    private String adviserId;
    /**
     * 班级人数
     */
    @TableField("class_num")
    private Integer classNum;
    /**
     * 年级id
     */
    @TableField("grade_id")
    private String gradeId;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;

    public String getAdviserId() {
        return adviserId;
    }

    public void setAdviserId(String adviserId) {
        this.adviserId = adviserId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public Integer getClassNum() {
        return classNum;
    }

    public void setClassNum(Integer classNum) {
        this.classNum = classNum;
    }

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
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
        return "PkClass{" +
        ", id=" + id +
        ", className=" + className +
        ", schoolCode=" + schoolCode +
        ", classNum=" + classNum +
        ", gradeId=" + gradeId +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}

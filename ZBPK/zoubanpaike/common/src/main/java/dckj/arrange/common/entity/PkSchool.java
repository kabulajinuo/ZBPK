package dckj.arrange.common.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 学校表
 * </p>
 *
 * @author Xiangjing
 * @since 2019-08-17
 */
@TableName("pk_school")
public class PkSchool extends Model<PkSchool> {

    private static final long serialVersionUID = 1L;

    /**
     * 学校id
     */
    private String id;
    /**
     * 学校名称
     */
    @TableField("school_name")
    private String schoolName;
    /**
     * 学校code
     */
    @TableField("school_code")
    private String schoolCode;
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

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
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
        return "PkSchool{" +
        ", id=" + id +
        ", schoolName=" + schoolName +
        ", schoolCode=" + schoolCode +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}

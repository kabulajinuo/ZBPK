package dckj.arrange.common.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Xaingjing
 * @since 2019-08-14
 */
@TableName("pk_site")
public class PkSite extends Model<PkSite> {

    private static final long serialVersionUID = 1L;

    @TableId("site_id")
    private String siteId;
    /**
     * 场地名称
     */
    @TableField("site_name")
    @ApiModelProperty(value = "场地名称 必传")
    private String siteName;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 学校编号
     */
    @TableField("school_code")
    @ApiModelProperty(value = "学校编号 必传")
    private String schoolCode;


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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    @Override
    protected Serializable pkVal() {
        return this.siteId;
    }

    @Override
    public String toString() {
        return "PkSite{" +
        ", siteId=" + siteId +
        ", siteName=" + siteName +
        ", createTime=" + createTime +
        ", schoolCode=" + schoolCode +
        "}";
    }
}

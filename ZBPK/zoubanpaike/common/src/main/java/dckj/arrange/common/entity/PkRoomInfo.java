package dckj.arrange.common.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import dckj.arrange.common.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author Xiangjing
 * @since 2019-08-14
 */
@TableName("pk_room_info")
@Data
public class PkRoomInfo extends Model<PkRoomInfo> implements Serializable{

    private static final long serialVersionUID = 1L;

    @TableId("room_id")
    @ApiModelProperty(value = "主键")
    private String roomId;
    /**
     * 场地ID
     */
    @TableField("site_id")
    @ApiModelProperty(value = "场地ID")
    private String siteId;
    /**
     * 教室类型
     */
    @TableField("room_type")
    @ApiModelProperty(value = "教室类型")
    private Integer roomType;
    /**
     * 学科IDs
     */
    @TableField("subject_ids")
    @ApiModelProperty(value = "学科Ids 以,拼接的字符串")
    @NotEmpty(message = "学科IDs不能为空")
    @Excel(name = "适用学科")
    private String subjectIds;
    /**
     * 教室名字
     */
    @TableField("room_name")
    @ApiModelProperty(value = "教室名字")
    @NotEmpty(message = "教室名字不能为空")
    @Excel(name = "教室名称")
    private String roomName;
    /**
     * 所能容纳人数
     */
    @TableField("man_quantity")
    @Max(value = 10000,message = "最大容量只能10000")
    @ApiModelProperty(value = "所能容纳人数")
    @NotNull(message = "容纳人数不能为空")
    @Excel(name = "容纳人数")
    private Integer manQuantity;
    /**
     * 最大容纳班级数
     */
    @TableField("best_larger")
    @Max(value = 100,message = "最大容量只能100")
    @ApiModelProperty(value = "最大容纳班级数")
    @NotNull(message = "容纳班级数不能为空")
    @Excel(name = "容纳班级数")
    private Integer bestLarger;
    /**
     * 创建时间
     */
    @TableField("create_time")
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /**
     * 学校编号
     */
    @TableField("school_code")
    @ApiModelProperty(value = "学校编号")
    @NotEmpty(message = "学校编号不能为空")
    @Excel(name = "学校编号")
    private String schoolCode;


    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public Integer getRoomType() {
        return roomType;
    }

    public void setRoomType(Integer roomType) {
        this.roomType = roomType;
    }

    public String getSubjectIds() {
        return subjectIds;
    }

    public void setSubjectIds(String subjectIds) {
        this.subjectIds = subjectIds;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getManQuantity() {
        return manQuantity;
    }

    public void setManQuantity(Integer manQuantity) {
        this.manQuantity = manQuantity;
    }

    public Integer getBestLarger() {
        return bestLarger;
    }

    public void setBestLarger(Integer bestLarger) {
        this.bestLarger = bestLarger;
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
        return this.roomId;
    }

    @Override
    public String toString() {
        return "PkRoomInfo{" +
        ", roomId=" + roomId +
        ", siteId=" + siteId +
        ", roomType=" + roomType +
        ", subjectIds=" + subjectIds +
        ", roomName=" + roomName +
        ", manQuantity=" + manQuantity +
        ", bestLarger=" + bestLarger +
        ", createTime=" + createTime +
        ", schoolCode=" + schoolCode +
        "}";
    }
}

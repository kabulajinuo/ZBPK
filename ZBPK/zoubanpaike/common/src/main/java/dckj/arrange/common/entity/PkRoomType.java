package dckj.arrange.common.entity;

import com.baomidou.mybatisplus.enums.IdType;
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
 * @author Pengjin
 * @since 2019-08-14
 */
@TableName("pk_room_type")
public class PkRoomType extends Model<PkRoomType> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "type_id", type = IdType.AUTO)
    private Integer typeId;
    /**
     * 教室类型
     */
    @TableField("type_name")
    @ApiModelProperty(value = "教师类型 必传")
    private String typeName;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 学校code
     */
    @TableField("school_code")
    @ApiModelProperty(value = "学校code 必传")
    private String schoolCode;


    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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
        return this.typeId;
    }

    @Override
    public String toString() {
        return "PkRoomType{" +
        ", typeId=" + typeId +
        ", typeName=" + typeName +
        ", createTime=" + createTime +
        ", schoolCode=" + schoolCode +
        "}";
    }
}

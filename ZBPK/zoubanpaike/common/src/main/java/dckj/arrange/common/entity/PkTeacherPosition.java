package dckj.arrange.common.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Xiangjing
 * @since 2019-08-13
 */
@Data
@TableName("pk_teacher_position")
public class PkTeacherPosition extends Model<PkTeacherPosition> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "post_id", type = IdType.AUTO)
    private Integer postId;
    /**
     * 职务名称
     */
    @TableField("post_name")
    @ApiModelProperty(value = "职务名称 必传")
    private String postName;
    /**
     * 创建时间
     */
    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 学校编号
     */
    @TableField("school_code")
    @ApiModelProperty(value = "职务名称 必传")
    private String schoolCode;
    /**
     * 职务编号
     */
    @TableField("post_code")
    @ApiModelProperty(value = "职务编号 不必传")
    private String postCode;

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.postId;
    }

}

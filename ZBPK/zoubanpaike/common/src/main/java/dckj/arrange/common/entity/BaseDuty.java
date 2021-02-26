package dckj.arrange.common.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 职务表
 * </p>
 *
 * @author Pengjin
 * @since 2019-08-13
 */
@TableName("base_duty")
public class BaseDuty extends Model<BaseDuty> {

    private static final long serialVersionUID = 1L;

    /**
     * 职务id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 职务名称
     */
    @TableField("duty_name")
    private String dutyName;
    /**
     * 职务code
     */
    @TableField("duty_code")
    private String dutyCode;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDutyName() {
        return dutyName;
    }

    public void setDutyName(String dutyName) {
        this.dutyName = dutyName;
    }

    public String getDutyCode() {
        return dutyCode;
    }

    public void setDutyCode(String dutyCode) {
        this.dutyCode = dutyCode;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "BaseDuty{" +
        ", id=" + id +
        ", dutyName=" + dutyName +
        ", dutyCode=" + dutyCode +
        "}";
    }
}

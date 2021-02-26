package dckj.arrange.common.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 字典表
 * </p>
 *
 * @author Pengjin
 * @since 2019-08-13
 */
@TableName("base_dict")
public class BaseDict extends Model<BaseDict> {

    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 编码
     */
    private String code;
    /**
     * 描述
     */
    @TableField("dict_name")
    private String dictName;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 排序
     */
    @TableField("sort_number")
    private Integer sortNumber;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
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
        return "BaseDict{" +
        ", id=" + id +
        ", code=" + code +
        ", dictName=" + dictName +
        ", type=" + type +
        ", sortNumber=" + sortNumber +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}

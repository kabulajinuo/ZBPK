package dckj.arrange.common.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author Pengjin
 * @since 2019-08-13
 */
@TableName("base_menu")
public class BaseMenu extends Model<BaseMenu> {

    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 编码
     */
    private String code;
    /**
     * 描述
     */
    @TableField("menu_name")
    private String menuName;
    /**
     * 父级id
     */
    @TableField("parent_id")
    private String parentId;
    /**
     * 资源地址
     */
    @TableField("resource_url")
    private String resourceUrl;
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

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
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
        return "BaseMenu{" +
        ", id=" + id +
        ", code=" + code +
        ", menuName=" + menuName +
        ", parentId=" + parentId +
        ", resourceUrl=" + resourceUrl +
        ", type=" + type +
        ", sortNumber=" + sortNumber +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}

package dckj.arrange.common.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author Pengjin
 * @since 2019-08-13
 */
@TableName("base_role")
public class BaseRole extends Model<BaseRole> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 角色名称
     */
    @TableField("role_name")
    private String roleName;
    /**
     * 角色code
     */
    @TableField("role_code")
    private String roleCode;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "BaseRole{" +
        ", id=" + id +
        ", roleName=" + roleName +
        ", roleCode=" + roleCode +
        "}";
    }
}

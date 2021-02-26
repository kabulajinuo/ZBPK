package dckj.arrange.common.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author Pengjin
 * @since 2019-08-13
 */
@TableName("base_user")
public class BaseUser extends Model<BaseUser> {

    private static final long serialVersionUID = 1L;

    private String id;
    private String account;
    private String phone;
    private String password;
    private String email;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 性别 0.男 1.女
     */
    private String gender;
    /**
     * 头像
     */
    @TableField("head_pic")
    private String headPic;
    /**
     * 职务id
     */
    @TableField("duty_id")
    private Integer dutyId;
    /**
     * 角色id
     */
    @TableField("role_id")
    private Integer roleId;
    /**
     * 学习阶段id
     */
    @TableField("learning_phase_id")
    private String learningPhaseId;
    /**
     * 0.学生  1.老师 2学校账号 3家长
     */
    @TableField("register_type")
    private Integer registerType;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;
    /**
     * 最近一次登录时间
     */
    @TableField("last_login_time")
    private Date lastLoginTime;
    /**
     * 人脸识别id
     */
    @TableField("face_id")
    private Integer faceId;
    /**
     * 账号状态(0.禁用，1.启用)
     */
    private Integer status;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public Integer getDutyId() {
        return dutyId;
    }

    public void setDutyId(Integer dutyId) {
        this.dutyId = dutyId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getLearningPhaseId() {
        return learningPhaseId;
    }

    public void setLearningPhaseId(String learningPhaseId) {
        this.learningPhaseId = learningPhaseId;
    }

    public Integer getRegisterType() {
        return registerType;
    }

    public void setRegisterType(Integer registerType) {
        this.registerType = registerType;
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

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getFaceId() {
        return faceId;
    }

    public void setFaceId(Integer faceId) {
        this.faceId = faceId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "BaseUser{" +
        ", id=" + id +
        ", account=" + account +
        ", phone=" + phone +
        ", password=" + password +
        ", email=" + email +
        ", nickname=" + nickname +
        ", gender=" + gender +
        ", headPic=" + headPic +
        ", dutyId=" + dutyId +
        ", roleId=" + roleId +
        ", learningPhaseId=" + learningPhaseId +
        ", registerType=" + registerType +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", lastLoginTime=" + lastLoginTime +
        ", faceId=" + faceId +
        ", status=" + status +
        "}";
    }
}

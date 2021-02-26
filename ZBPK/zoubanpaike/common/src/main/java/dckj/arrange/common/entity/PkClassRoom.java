package dckj.arrange.common.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 班级所属教室表
 * </p>
 *
 * @author Xiangjing
 * @since 2019-08-19
 */
@Data
@TableName("pk_class_room")
public class PkClassRoom extends Model<PkClassRoom> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;
    /**
     * 任务ID
     */
    @TableField("task_id")
    @NotEmpty(message = "任务ID不能为空")
    private String taskId;
    /**
     * 班级ID
     */
    @TableField("class_id")
    @NotEmpty(message = "班级ID不能为空")
    private String classId;
    /**
     * 场地ID
     */
    @TableField("site_id")
    @NotEmpty(message = "场地ID不能为空")
    private String siteId;
    /**
     * 教室ID
     */
    @TableField("room_id")
    @NotEmpty(message = "教室ID不能为空")
    private String roomId;
    /**
     * 教室名字
     */
    @TableField("room_name")
    @NotEmpty(message = "教室名字不能为空")
    private String roomName;
    /**
     * 场地名字
     */
    @TableField("site_name")
    @NotEmpty(message = "场地名字不能为空")
    private String siteName;
    /**
     * 创建时间
     */
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

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
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
        return "PkClassRoom{" +
        ", id=" + id +
        ", taskId=" + taskId +
        ", classId=" + classId +
        ", siteId=" + siteId +
        ", roomId=" + roomId +
        ", roomName=" + roomName +
        ", siteName=" + siteName +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}

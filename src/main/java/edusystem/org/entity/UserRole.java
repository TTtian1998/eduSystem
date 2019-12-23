package edusystem.org.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author 田泽鑫
 * @date 2019/11/23
 */
@TableName("user_role")
public class UserRole {
    @TableId(value = "id",type = IdType.AUTO)
    private int id;
    @TableField("user_id")
    private int userId;
    @TableField("role_id")
    private int roleId;

    public UserRole() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}

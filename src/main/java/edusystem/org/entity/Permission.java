package edusystem.org.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * @author 田泽鑫
 * @date 2019/11/28
 */
public class Permission {
    @TableId(value = "permission_id",type = IdType.AUTO)
    private int permissionId;
    @TableField("menu_code")
    private String menuCode;
    @TableField("permission_name")
    private String permissionName;
    @TableField("menu_name")
    private String menuName;
    @TableField("permission_param")
    private String permissionParam;

    public Permission() {
    }

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getPermissionParam() {
        return permissionParam;
    }

    public void setPermissionParam(String permissionParam) {
        this.permissionParam = permissionParam;
    }
}

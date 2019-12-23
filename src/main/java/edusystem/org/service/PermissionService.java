package edusystem.org.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edusystem.org.common.entity.Result;
import edusystem.org.entity.Permission;

import java.util.List;

/**
 * @author 田泽鑫
 * @date 2019/11/28
 */
public interface PermissionService extends IService<Permission> {
    //查询某用户的 角色  菜单列表   权限列表
    List<Permission> getUserPermission(String account);
}

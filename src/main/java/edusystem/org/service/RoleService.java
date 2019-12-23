package edusystem.org.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edusystem.org.entity.Role;

import java.util.List;

/**
 * @author 田泽鑫
 * @date 2019/11/21
 */
public interface RoleService extends IService<Role> {
    // 获取用户的账号查找用户角色集
    List<Role> findUserRole(String account);


}

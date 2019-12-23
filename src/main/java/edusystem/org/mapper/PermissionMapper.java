package edusystem.org.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edusystem.org.common.entity.Result;
import edusystem.org.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

/**
 * @author 田泽鑫
 * @date 2019/11/28
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
    //查询用户的角色 菜单 权限
    List<Permission> getUserPermission(String account);

    //查询所有的菜单
    Set<String> getAllMenu();

    //查询所有的权限
    Set<String> getAllPermission();
}

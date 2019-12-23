package edusystem.org.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edusystem.org.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 田泽鑫
 * @date 2019/11/22
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    // 通过用户账号查找用户角色集
    List<Role> findUserRole(String account);
}

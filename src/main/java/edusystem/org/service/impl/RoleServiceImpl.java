package edusystem.org.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edusystem.org.entity.Role;
import edusystem.org.mapper.RoleMapper;
import edusystem.org.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 田泽鑫
 * @date 2019/11/22
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public List<Role> findUserRole(String account) {
        return this.baseMapper.findUserRole(account);
    }
}

package edusystem.org.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edusystem.org.entity.UserRole;
import edusystem.org.mapper.UserRoleMapper;
import edusystem.org.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * @author 田泽鑫
 * @date 2019/11/23
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
}

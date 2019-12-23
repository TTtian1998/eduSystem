package edusystem.org.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edusystem.org.entity.Permission;
import edusystem.org.mapper.PermissionMapper;
import edusystem.org.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 田泽鑫
 * @date 2019/11/28
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    @Override
    public List<Permission> getUserPermission(String account) {
        return permissionMapper.getUserPermission(account);
    }
}

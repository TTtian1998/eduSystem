package edusystem.org.common.shiro;

import edusystem.org.common.entity.Result;
import edusystem.org.common.myEnum.ResultTypeEnum;
import edusystem.org.entity.Permission;
import edusystem.org.entity.Role;
import edusystem.org.entity.User;
import edusystem.org.service.PermissionService;
import edusystem.org.service.RoleService;
import edusystem.org.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static edusystem.org.util.Constants.SESSION_USER_INFO;

/**
 * @author 田泽鑫
 * @date 2019/11/22
 * @description: 自定义Realm
 */
public class ShiroRealm extends AuthorizingRealm {
    private Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;
    /**
     * 授权方法，获取用户角色和权限
     *
     * @param principals principals
     * @return AuthorizationInfo 权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Session session = SecurityUtils.getSubject().getSession();
        User user =(User) session.getAttribute("userInfo");
        String account = user.getAccount();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 获取用户角色集
        List<Role> roleList = this.roleService.findUserRole(account);
        Set<String> roleSet = roleList.stream().map(Role::getRoleName).collect(Collectors.toSet());
        simpleAuthorizationInfo.setRoles(roleSet);
        // 获取用户权限集
        List<Permission> permissionList = this.permissionService.getUserPermission(account);
        Set<String> permissionSet = permissionList.stream().map(Permission::getPermissionParam).collect(Collectors.toSet());
        simpleAuthorizationInfo.setStringPermissions(permissionSet);
        return simpleAuthorizationInfo;
    }

    /**
     * 认证方法
     * 验证当前登录的Subject
     * LoginController中调用UserServiceImpl中的authLogin方法中执行Subject.login()时 执行此方法
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String account = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        User user= userService.getUser(account,password);
        if (user==null){
            //没有找到账号
            throw new UnknownAccountException("没有找到账号");
        }
        //使用CredentialsMatcher进行密码匹配
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getAccount(),
                user.getPassword(),
                //ByteSource.Util.bytes("salt"), salt=username+salt,采用明文访问时，不需要此句
                getName()
        );
        //session中不需要保存密码
        user.setPassword(null);
        //将用户信息放入session中
        SecurityUtils.getSubject().getSession().setAttribute(SESSION_USER_INFO, user);
        return authenticationInfo;
    }
}

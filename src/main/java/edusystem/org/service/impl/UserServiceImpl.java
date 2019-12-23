package edusystem.org.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edusystem.org.common.entity.Result;
import edusystem.org.common.exception.LoginException;
import edusystem.org.common.shiro.ShiroHelper;
import edusystem.org.entity.User;
import edusystem.org.entity.UserRole;
import edusystem.org.mapper.UserMapper;
import edusystem.org.service.UserRoleService;
import edusystem.org.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static edusystem.org.common.myEnum.ResultTypeEnum.LOGIN_ERROR;
import static edusystem.org.common.myEnum.ResultTypeEnum.REGIST_FAILED;


/**
 * @author 田泽鑫
 * @date 2019/11/21
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private ShiroHelper shiroHelper;

    @Override
    public IPage<User> selectUserDetail(Page<User> page, int deleteStatus) {
        return userMapper.selectUserDetail(page,deleteStatus);
    }

    @Override
    public Result getInfo() {
        // 菜单暂时写死
        List menuList = new ArrayList();
        menuList.add("nav");
        menuList.add("banner");
        menuList.add("more");
        //从session获取用户信息
        Session session = SecurityUtils.getSubject().getSession();
        User userInfo = (User) session.getAttribute("userInfo");
        AuthorizationInfo authorizationInfo = shiroHelper.getCurrentuserAuthorizationInfo();
        Map info = new HashMap();
        // 获取到用户的权限和角色集合
        Object permissionList = authorizationInfo.getStringPermissions();
        Object roleName = authorizationInfo.getRoles();
        info.put("permissionList",permissionList);
        info.put("userName",userInfo.getUserName());
        info.put("roleName",roleName);
        info.put("menuList",menuList);
        info.put("userId",userInfo.getUserId());

        //存储用户角色 权限 信息
        Map userPermission = new HashMap();
        userPermission.put("userPermission",info);
        return new Result().info(userPermission);
    }

    @Override
    public Result authLogin(String account, String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(account,password);
        try {
            subject.login(token);
            Map result = new HashMap();
            result.put("result","success");
            return new Result().info(result);
        }catch (AuthenticationException e){
            throw new LoginException(LOGIN_ERROR);
        }
    }

    @Override
    public Result regist(User user) {
        user.setCreateDate(new Date());
        try{
            if (userMapper.insert(user)>0){
                //保存用户角色
                UserRole userRole = new UserRole();
                userRole.setUserId(user.getUserId());
                userRole.setRoleId(user.getRoleId());
                this.userRoleService.save(userRole);
                return new Result().success();
            }
        }catch (Exception e){
            throw  new LoginException(REGIST_FAILED);
        }

        return new Result().fail();
    }

    @Override
    public List<User> getAllUser(){
        List<User> userList = userMapper.selectList(
                new QueryWrapper<User>()
                        .lambda()
                        .eq(User::isDeleteStatus, 0)
        );
        return userList;
    }

    @Override
    public void createUser(User user) {
        user.setCreateDate(new Date());
        // todo 保存用户角色 用户密码加密存储
        save(user);
    }

    @Override
    public void updateUser(User user) {

    }


    @Override
    public void deleteUsers(String[] userIds) {

    }

    @Override
    public boolean findByAccount(String username) {
        return false;
    }

    @Override
    public Result logout() {
        try {
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.logout();
        } catch (Exception e) {
        }
        return new Result().success();
    }

    @Override
    public User getUser(String account, String password) {
        User user = userMapper.selectOne(
                new QueryWrapper<User>()
                        .lambda()
                        .eq(User::getAccount, account)
                        .eq(User::getPassword,password)
        );
        return user;
    }


}

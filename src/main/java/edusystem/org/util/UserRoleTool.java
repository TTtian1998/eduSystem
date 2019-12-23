package edusystem.org.util;

import edusystem.org.common.shiro.ShiroHelper;
import edusystem.org.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;

import static edusystem.org.util.Constants.*;

/**
 * @author 田泽鑫
 * @date 2019/12/23
 * @description 用户角色工具类，用户获取当前用户 和用户的角色，由于业务逻辑常用，封装成类方便调用
 */
@Component
public class UserRoleTool {
    @Autowired
    private ShiroHelper shiroHelper;


    //获取当前用户的角色名称
    public String getCurrentUserRole(){
        String role = null;
        //从session获取用户信息
        Session session = SecurityUtils.getSubject().getSession();
        User userInfo = (User) session.getAttribute(SESSION_USER_INFO);
        AuthorizationInfo authorizationInfo = shiroHelper.getCurrentuserAuthorizationInfo();
        //获取到用户的角色
        String roleName=null;
        Iterator roles = authorizationInfo.getRoles().iterator();
        while(roles.hasNext()) {
            roleName= (String) roles.next();
        }
        //将已知非null的变量放在前面
        if (ROLE_TEACHER.equals(roleName)){
            role = ROLE_TEACHER;
        }else if (ROLE_STUDENT.equals(roleName)){
            role = ROLE_STUDENT;
        }
        return role;
    }

    //获取当前用户的用户信息
    public User getCurrentUser(){
        //从session获取用户信息
        Session session = SecurityUtils.getSubject().getSession();
        User userInfo = (User) session.getAttribute(SESSION_USER_INFO);
        return userInfo;
    }
}

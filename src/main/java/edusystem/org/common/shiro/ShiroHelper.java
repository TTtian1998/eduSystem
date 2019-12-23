package edusystem.org.common.shiro;

import org.apache.shiro.authz.AuthorizationInfo;
import org.springframework.stereotype.Component;

/**
 * @author 田泽鑫
 * @date 2019/11/26
 * @description: Shiro工具类
 */
@Component
public class ShiroHelper extends ShiroRealm {

    /**
     * 获取当前用户的角色和权限集合
     *
     * @return AuthorizationInfo
     */
    public AuthorizationInfo getCurrentuserAuthorizationInfo() {
        return super.doGetAuthorizationInfo(null);
    }
}

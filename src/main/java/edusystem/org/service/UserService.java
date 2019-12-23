package edusystem.org.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import edusystem.org.common.entity.Result;
import edusystem.org.entity.User;

import java.util.List;

/**
 * @author 田泽鑫
 * @date 2019/11/21
 */
public interface UserService extends IService<User> {
    //分页查询
    IPage<User> selectUserDetail(Page<User> page, int deleteStatus);
    //查询当前登录用户的权限等信息
    Result getInfo();
    public List<User> getAllUser();
    // 新增用户
    void createUser(User user);
    //修改用户
    void updateUser(User user);
    //注册用户
    Result regist(User user);
    //删除用户
    void deleteUsers(String[] userIds);
    // 通过账号查找用户是否存在
    boolean findByAccount(String account);
    //退出登录
    Result logout();
    //根据账号密码查询用户
    User getUser(String account,String password);
    //登录
    Result authLogin(String account,String password);
}

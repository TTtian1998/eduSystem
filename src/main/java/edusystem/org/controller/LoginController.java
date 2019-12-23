package edusystem.org.controller;

import edusystem.org.common.entity.Result;
import edusystem.org.entity.User;
import edusystem.org.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

/**
 * @author 田泽鑫
 * @date 2019/11/22
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;

    //登录
    @PostMapping("/auth")
    public Result login(@RequestBody User user
    ){
        // todo password加密
        String account = user.getAccount();
        String password = user.getPassword();
        return this.userService.authLogin(account,password);
    }

    @PostMapping("/regist")
    public Result regist(@RequestBody  User user){
        //查询用户是否存在 根据账号查询,用户允许同名
        if (userService.findByAccount(user.getAccount())){
            return new Result().success();
        }
        return this.userService.regist(user);
    }

    @PostMapping("/getInfo")
    public Result getInfo(){
        return userService.getInfo();
    }

    //退出登录
    @PostMapping("/logout")
    public Result loginOut(){
        return userService.logout();
    }
}

package edusystem.org.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edusystem.org.common.entity.Result;
import edusystem.org.entity.User;
import edusystem.org.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 田泽鑫
 * @date 2019/11/21
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/getAllUser")
    public IPage<User> getAllUser(Page page){
        return userService.selectUserDetail(page,0);
    }

    @PostMapping("/getUser")
    public User getUser(String account,String password){
        return userService.getUser(account,password);
    }

    @PostMapping("/regist")
    public Result regist(User user){
       return this.userService.regist(user);
    }
}

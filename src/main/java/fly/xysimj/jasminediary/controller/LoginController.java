package fly.xysimj.jasminediary.controller;

import fly.xysimj.jasminediary.entity.Result;
import fly.xysimj.jasminediary.entity.User;
import fly.xysimj.jasminediary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.Session;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @program: JasmineDiary
 * @ClassName LoginController
 * @description: 登录控制器
 * @author: 徐杨顺
 * @create: 2021-12-20 13:48
 * @Version 1.0
 **/
@RestController
@CrossOrigin
@RequestMapping("/fyl")
public class LoginController {
    @Autowired
    UserService userService;
    @CrossOrigin //解决跨域问题,域名,端口,协议不同都算跨域
    @PostMapping(value = "/login")
    @ResponseBody
    public Result login(@RequestBody User requsestUser, HttpSession session) {
        String username = requsestUser.getUsername();
        String password = requsestUser.getPassword();
        User user = userService.getUser(username, password);
        if (!Objects.equals("admin", username) || !Objects.equals("123456", requsestUser.getPassword())) {
            String message = "账号密码错误";
            System.out.println("test");
            return new Result(400);
        }else{
            session.setAttribute("user",user); //将user对象存入session
            return new Result(200);
        }
    }
}

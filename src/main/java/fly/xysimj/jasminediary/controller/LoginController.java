package fly.xysimj.jasminediary.controller;

import fly.xysimj.jasminediary.entity.Result;
import fly.xysimj.jasminediary.entity.User;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/fyl")
public class LoginController {
    @CrossOrigin
    @PostMapping(value = "/login")
    @ResponseBody
    public Result login(@RequestBody User requsestUser) {
        String username = requsestUser.getUsername();
        String password = requsestUser.getPassword();

        if (!Objects.equals("admin", username) || !Objects.equals("123456", requsestUser.getPassword())) {
            String message = "账号密码错误";
            System.out.println("test");
            return new Result(400);
        }else{
            return new Result(200);
        }
    }
}

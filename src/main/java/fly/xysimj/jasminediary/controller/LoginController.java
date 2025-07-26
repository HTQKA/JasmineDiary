package fly.xysimj.jasminediary.controller;

import fly.xysimj.jasminediary.config.annotation.LogPrint;
import fly.xysimj.jasminediary.entity.Result;
import fly.xysimj.jasminediary.entity.User;
import fly.xysimj.jasminediary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    // 解决跨域问题,域名,端口,协议不同都算跨域
    @CrossOrigin
    @PostMapping(value = "/login")
    @ResponseBody
    @LogPrint
    public Result login(@RequestBody User requestUser, HttpSession session) {
        String username = requestUser.getUsername();
        String password = requestUser.getPassword();
        User user = userService.getUser(username, password);
        if (!Objects.equals("admin", username) || !Objects.equals("123456", requestUser.getPassword())) {
            String message = "账号密码错误";
            System.out.println("test");
            return new Result(400);
        }else{
            //将user对象存入session
            session.setAttribute("user",user);
            //查询user角色信息存入session
            System.out.println("登录成功");
            return new Result(200,"登录成功");
        }
    }
   /**
    * @program: LoginController.java
    * @ClassNmae: LoginController
    * @description:
    * @author: TheShun
    * @date: 2022/6/30 9:28
    * @param:
    * @return:
    **/
    @PostMapping("/index")
    @ResponseBody
    @CrossOrigin
    @LogPrint
    public Result index(HttpSession session){
        return null;
    }

    //注册权限
    //新建角色,分配权限
    //新建用户,分配角色



}

package fly.xysimj.jasminediary.controller;

import fly.xysimj.jasminediary.entity.Result;
import fly.xysimj.jasminediary.entity.User;
import fly.xysimj.jasminediary.mapper.UserMapper;
import fly.xysimj.jasminediary.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * @program: JasmineDiary
 * @ClassName UserController
 * @description: 用户控制器
 * @author: 徐杨顺
 * @create: 2021-12-09 13:57
 * @Version 1.0
 **/
@RestController
@CrossOrigin
@RequestMapping("/fyl/user")
@Slf4j
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserService userService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostMapping("/login")
    public Result login(@RequestBody User user, HttpSession session, HttpServletRequest request) {
        String username = user.getUsername();
        username = HtmlUtils.htmlEscape(username);
        //User user1 = userService.getUser(username, user.getPassword());
        Result result = userService.userLogin(username, user.getPassword());
        return result;
    }

    @PostMapping("/logout")
    public Result logout(@RequestBody User user,HttpSession session,HttpServletRequest request){
        Result result = userService.loginOut(request.getHeader("adminId"));
        return result;
    }

    @PostMapping("/index")
    public Result index(){
        return null;
    }

    @PostMapping("/addUser")
    public Result addUser(@RequestBody User user,HttpSession session,HttpServletRequest request){
        return  userService.addUser(user);
    }
    @PostMapping("/updateUser")
    public Result updateUser(@RequestBody User user,HttpSession session,HttpServletRequest request){
        //校验用户是否是否登录
        userService.updateUser(user);
        return null;
    }


    @PostMapping("/deleteUser")
    public Result deleteUsr(@RequestBody User user,HttpSession session,HttpServletRequest request){
        log.info("注销账号");
        return userService.deleteUser(user);
    }
    @GetMapping("/getAllUsers")
    public Result getAllUsers() {
        return Result.success(userMapper.getAllUsers());
    }

}

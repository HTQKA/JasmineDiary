package fly.xysimj.jasminediary.controller;

import fly.xysimj.jasminediary.entity.Result;
import fly.xysimj.jasminediary.entity.User;
import fly.xysimj.jasminediary.mapper.UserMapper;
import fly.xysimj.jasminediary.service.UserService;
import fly.xysimj.jasminediary.utils.JsonUtils;
import io.lettuce.core.protocol.CommandType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


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
public class UserController {
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
    public Result addUser(){
        return  null;
    }
    @PostMapping("/updateUser")
    public Result updateUser(){
        return null;
    }
    @PostMapping("/deleteUser")
    public Result deleteUsr(){
        return null;
    }
    @GetMapping("/getAllUsers")
    public ArrayList<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    @GetMapping("/getAllUser")
    public ArrayList<User> getAllUser() {
        return userMapper.getAllUsers();
    }


}

package fly.xysimj.jasminediary.controller;

import fly.xysimj.jasminediary.entity.Result;
import fly.xysimj.jasminediary.entity.User;
import fly.xysimj.jasminediary.mapper.UserMapper;
import fly.xysimj.jasminediary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.util.ArrayList;


/**
 * @program: JasmineDiary
 * @ClassName UserController
 * @description: 用户控制器
 * @author: 徐杨顺
 * @create: 2021-12-09 13:57
 * @Version 1.0
 **/
@RestController
@RequestMapping("/fyl/user")
public class UserController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserService userService;

    @GetMapping("/getAllUsers")
    public ArrayList<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    @GetMapping("/getAllUser")
    public ArrayList<User> getAllUser() {
        return userMapper.getAllUsers();
    }

    @PostMapping("/login")
    @CrossOrigin
    public Result login(@RequestBody User user) {
        String username = user.getUsername();
        username = HtmlUtils.htmlEscape(username);

        User user1 = userService.getUser(username, user.getPassword());
        if (null != user1) {
            return new Result(200);
        } else {
            return new Result(400);
        }
    }

}

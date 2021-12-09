package fly.xysimj.jasminediary.controller;

import fly.xysimj.jasminediary.entity.User;
import fly.xysimj.jasminediary.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class UserController {
    @Autowired
    UserMapper userMapper;

    @GetMapping("/getAllUsers")
    public ArrayList<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    @GetMapping("/getAllUser")
    public ArrayList<User> getAllUser() {
        return userMapper.getAllUsers();
    }
}

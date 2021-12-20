package fly.xysimj.jasminediary.service;

import fly.xysimj.jasminediary.entity.User;
import fly.xysimj.jasminediary.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: JasmineDiary
 * @ClassName UserService
 * @description: 用户业务类
 * @author: 徐杨顺
 * @create: 2021-12-20 15:04
 * @Version 1.0
 **/
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public User getUser(String username, String password) {
        return userMapper.getUser(username, password);
    }
}

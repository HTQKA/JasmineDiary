package fly.xysimj.jasminediary.service;

import fly.xysimj.jasminediary.entity.Result;
import fly.xysimj.jasminediary.entity.User;
import fly.xysimj.jasminediary.mapper.UserMapper;
import fly.xysimj.jasminediary.utils.JsonUtils;
import fly.xysimj.jasminediary.utils.ProductToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    ProductToken productToken;

    private Map<Integer,String> UserLogin = new HashMap<>();

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public User getUser(String username, String password) {
        return userMapper.getUser(username, password);
    }

    public Result userLogin(String username,String password){
        User user = userMapper.getUser(username, password);
        if (null != user) {
            //redisTemplate.opsForValue().set("user", JsonUtils.objectToJson(user));
            String token = UUID.randomUUID().toString().replaceAll("-","");
            Map<String, String> mapinfo = productToken.productToken(user.getId().toString(), token);
            return new Result(200,"登录成功",mapinfo);
            //获取登录token
        } else {
            return new Result(400);
        }
    }

    public Result loginOut(String adminId){
        //User user = userMapper.getUser(username, password);
        redisTemplate.delete(adminId);
        return new Result(200,"成功登出");
    }
}

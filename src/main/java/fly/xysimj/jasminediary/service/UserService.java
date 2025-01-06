package fly.xysimj.jasminediary.service;

import com.github.benmanes.caffeine.cache.Cache;
import fly.xysimj.jasminediary.commom.UserCache;
import fly.xysimj.jasminediary.entity.Result;
import fly.xysimj.jasminediary.entity.User;
import fly.xysimj.jasminediary.entity.UserSession;
import fly.xysimj.jasminediary.mapper.UserMapper;
import fly.xysimj.jasminediary.utils.ProductToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    UserMapper userMapper;
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    ProductToken productToken;

    private Map<Integer,String> UserLogin = new HashMap<>();

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private Cache<String, Object> caffeineCache;

    public User getUser(String username, String password) {
        return userMapper.getUser(username, password);
    }

    public Result userLogin(String username,String password){
        User user = userMapper.getUser(username, password);
        if (null != user) {
            //redisTemplate.opsForValue().set("user", JsonUtils.objectToJson(user));
            String token = UUID.randomUUID().toString().replaceAll("-","");
            UserSession userSession = new UserSession();
            userSession.setUserId(user.getId()+"");
            userSession.setUsername(user.getUsername());
            userSession.setLoginTime(new Date());
            //本地缓存
            //caffeineCache.put(token,userSession);
            UserCache.add(token, userSession);
            Map<String, UserSession> mapinfo = productToken.productToken(token,userSession);
            return new Result(200,"登录成功",mapinfo);
            //获取登录token
        } else {
            return new Result(400,"用户名或密码错误");
        }
    }

    public Result loginOut(String adminId){
        //User user = userMapper.getUser(username, password);
        redisTemplate.delete(adminId);
        return new Result(200,"成功登出");
    }

    public Result addUser(User user) {
        //查询用户不存在
        if (checkUser(user)) {
            return Result.fail("用户已存在");
        }
        int insert = userMapper.insert(user);
        log.info("insert result: " + user);

        return Result.success("添加成功");

    }

    public void updateUser(User user) {
        if(checkUser(user)){
            userMapper.updateById(user);
        }
    }

    //校验用户是否存在
    public Boolean checkUser(User user) {
        User user1 = userMapper.getUser(user.getUsername(), user.getPassword());
        if (user1 !=  null){
            return true;
        }
        return false;
    }

    //校验用户是否登录
    public Boolean checkLogin(String adminId) {
        return null;
    }

    public Result deleteUser(User user) {
        Boolean b = checkUser(user);
        if (b) {
            userMapper.deleteById(user.getId());
            return Result.success("删除成功");
        }
        return Result.fail("用户不存在");

    }
}

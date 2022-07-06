package fly.xysimj.jasminediary.utils;

import org.apache.coyote.http11.filters.SavedRequestInputFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @program: JasmineDiary
 * @ClassName ProductToken
 * @description: 生成token类
 * @author: 徐杨顺
 * @create: 2022-07-05 10:07
 * @Version 1.0
 **/

@Service
public class ProductToken {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public Map<String,String> productToken(String key,String value){
        Map<String,String> infoMap = new HashMap<String, String>();
        if (redisTemplate.opsForValue().get(key)==null){
            //将登陆信息保存至redis
            redisTemplate.opsForValue().set(key,value);
            infoMap.put(key,value);
        }
        //设置token有效时间
        redisTemplate.expire(key,3000, TimeUnit.SECONDS);
        return infoMap;
    }
}

package fly.xysimj.jasminediary.commom;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author XYS
 * @date 2025年01月06日 14:13
 */
@Configuration
public class CacheConfig {

    @Bean
    public Cache<String, Object> caffeineCache() {
        Cache<String, Object> cache = Caffeine.newBuilder()
                // 初始容量
                .initialCapacity(100)
                // 最大缓存数量
                .maximumSize(500)
                // 缓存过期时间：写入缓存后，经过某个时间缓存失效
                .expireAfterWrite(3, TimeUnit.SECONDS)
                // 缓存失效监听器
                .removalListener((key, value, cause) -> System.out.println("key:" + key + " value:" + value + " cause:" + cause))
                // 开启统计功能
                .recordStats()
                .build();
        //cache.put("name", "zzc");
        // 如果一个 key 不存在，那么会进入指定的函数生成 value
        //cache.get("age", key -> "18");
        // 判断是否存在，如果不存在，则返回 null
        //Object ageValue = cache.getIfPresent("age");
        // 移除一个key
        //cache.invalidate("name");
        return cache;
    }
}


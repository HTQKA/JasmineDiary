package fly.xysimj.jasminediary.commom;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author XYS
 * @date 2025年01月06日 14:05
 */
public class UserCache {

    @Schema(description = "用户缓存")
    public static  final ConcurrentHashMap<String, Object> CONCURRENT_HASH_MAP = new ConcurrentHashMap<>();

    @Schema(description = "缓存持续时间")
    private static final Long CACHE_HOLD_TIME_2H = 2 * 60 * 60* 1000L;
    @Schema(description = "缓存持续时间")
    private static final Long CACHE_HOLD_TIME_12H = 12 * 60 * 60* 1000L;
    @Schema(description = "缓存持续时间")
    private static final Long CACHE_HOLD_TIME_24H = 12 * 60 * 60* 1000L;


    /**
     * 添加数据到缓存
     * @param key 缓存key
     * @param value 缓存value
     * @param holdTime 缓存持续时间
     */
    public static void add(String key,Object value,Long holdTime) {
        if(checkKey(key)){
            return ;
        }
        CONCURRENT_HASH_MAP.put(key, value);
        //设置缓存过期时间
        CONCURRENT_HASH_MAP.put(key + "_HOLDTIME", System.currentTimeMillis() + holdTime);
    }

    /**
     * 添加数据到缓存，默认缓存2小时
     * @param key 缓存key
     * @param value 缓存value
     */
    public static void add(String key, Object value) {
        add(key, value, CACHE_HOLD_TIME_2H);
    }

    public static Object get(String key) {
        if (checkKey(key)) {
            return CONCURRENT_HASH_MAP.get(key);
        }
        return null;
    }

    /**
     * 删除一个缓存
     * @param key
     */
    public static void remove(String key) {
        CONCURRENT_HASH_MAP.remove(key);
        CONCURRENT_HASH_MAP.remove(key + "_HOLDTIME");
    }

    /**
     * 清空缓存
     */
    public static void clear() {
        CONCURRENT_HASH_MAP.clear();
    }

    public static boolean checkKey(String key) {
        if (CONCURRENT_HASH_MAP.containsKey(key)) {
            Long o = Optional.ofNullable((Long)CONCURRENT_HASH_MAP.get(key + "_HOLDTIME")).orElse(-1L);
            //存在key。校验是否过期
            if ( o < System.currentTimeMillis()) {
                //过期，删除key
                CONCURRENT_HASH_MAP.remove(key);
                CONCURRENT_HASH_MAP.remove(key + "_HOLDTIME");
                return false;
            }
            return true;
        }
        return false;

    }


}

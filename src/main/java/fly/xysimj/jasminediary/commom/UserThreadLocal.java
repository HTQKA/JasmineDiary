package fly.xysimj.jasminediary.commom;

import java.util.HashMap;
import java.util.Map;

/**
 * @author XYS
 * @date 2025年01月07日 19:00
 * @description 用户线程本地变量
 */
public class UserThreadLocal {
   private static final ThreadLocal<Map<String,Object>>  USER_THREAD_LOCAL = new ThreadLocal<>();
   private static final String KEY_USER = "USER";


    /**
     * 获取用户线程变量
     * @return 用户线程变量
     */
    public static Map<String,Object> getUserThreadLocal(){
        return USER_THREAD_LOCAL.get();
    }

    /**
     * 设置用户线程变量
     * @param user 用户信息
     */
    public static void setUserThreadLocal(Object user){
        Map<String,Object> userMap = new HashMap<>();
        userMap.put(KEY_USER,user);
        USER_THREAD_LOCAL.set(userMap);
    }

    /**
     * 获取用户信息
     * @return 用户信息
     */
    public static Object getUser(){
        return USER_THREAD_LOCAL.get().get(KEY_USER);
    }


    /**
     * 清除用户线程变量
     */
    public static void clearUserThreadLocal() {
        USER_THREAD_LOCAL.remove();
    }
}

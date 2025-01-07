package fly.xysimj.jasminediary.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.github.benmanes.caffeine.cache.Cache;
import fly.xysimj.jasminediary.commom.UserCache;
import fly.xysimj.jasminediary.commom.UserThreadLocal;
import fly.xysimj.jasminediary.entity.Result;
import fly.xysimj.jasminediary.entity.TokenInfo;
import fly.xysimj.jasminediary.entity.UserSession;
import fly.xysimj.jasminediary.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * @program: JasmineDiary
 * @ClassName UserInterceptor
 * @description: 拦截器
 * @author: 徐杨顺
 * @create: 2022-07-05 10:24
 * @Version 1.0
 **/
@Slf4j
public class UserInterceptor implements HandlerInterceptor {
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    private Cache<String, Object> caffeineCache;

    /**
     * 调用控制器方法之前调用(Controller方法调用之前)
     * @param request
     * @param response
     * @param object
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object)  {
        //检查用户传递的token是否合法
        TokenInfo tokenInfo = this.getUserToKen(request);
        if (StringUtils.isBlank(tokenInfo.getToken())) {
            System.out.println("没有传入对应的身份信息,返回登录");
            object = Result.fail("身份校验失败");
            String result = JSONObject.toJSONString(Result.fail("没有传入对应的身份信息,返回登录"));
            returnJson(response,result);
            return false;
        }
        try {
            //先从本地缓存cache中获取用户信息
            //Object ifPresent = caffeineCache.getIfPresent(tokenInfo.getToken());
            Object o = UserCache.get(tokenInfo.getToken());
            if (o != null) {
                //本地线程变量
                UserThreadLocal.setUserThreadLocal(o);
                return true;
            }
            //本地缓存中没有在从redis中获取用户信息
            UserSession userSession = JSONObject.parseObject(redisTemplate.opsForValue().get(tokenInfo.getToken()), UserSession.class);
            if (userSession != null ){
                log.info("校验成功");
                //本地线程变量
                UserThreadLocal.setUserThreadLocal(userSession);
                return true;
            }else{
                System.out.println("校验失败,返回登录");
                String result = JSONObject.toJSONString(Result.fail("校验失败,返回登录"));
                returnJson(response,result);
                return false;
            }
        }catch (Exception e){
            System.out.println("校验失败,信息匹配错误,返回登录");
            object = Result.fail("身份校验失败");
            String result = JSONObject.toJSONString(Result.fail("身份校验失败"));
            returnJson(response,result);
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //清除本地线程变量
        UserThreadLocal.clearUserThreadLocal();
    }

    /**
     * 在cookie中获取用户传递的token
     */
    private TokenInfo getUserToKen(HttpServletRequest request) {
        TokenInfo info = new TokenInfo();
        String adminId = request.getHeader("adminId");
        String token = request.getHeader("token");
        if (StringUtils.isNotBlank(token) && StringUtils.isNotBlank(token)) {
            info.setAdminId(adminId);
            info.setToken(token);
        }
        return info;
    }

    public void returnErrorResponse(HttpServletResponse response, Result result) throws IOException {
        OutputStream out = null;
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json");
            out = response.getOutputStream();
            out.write(JsonUtils.objectToJson(result).getBytes(StandardCharsets.UTF_8));
            out.flush();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * 返回前端json数据
     */
    private void returnJson(HttpServletResponse response,String result){
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(result);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}

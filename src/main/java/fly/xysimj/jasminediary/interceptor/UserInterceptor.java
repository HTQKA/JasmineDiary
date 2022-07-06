package fly.xysimj.jasminediary.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import fly.xysimj.jasminediary.entity.Result;
import fly.xysimj.jasminediary.entity.TokenException;
import fly.xysimj.jasminediary.entity.TokenInfo;
import fly.xysimj.jasminediary.utils.JsonUtils;
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
public class UserInterceptor implements HandlerInterceptor {
    @Autowired
    StringRedisTemplate redisTemplate;

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
        if (StringUtils.isBlank(tokenInfo.getAdminId()) && StringUtils.isBlank(tokenInfo.getToken())) {
            System.out.println("没有传入对应的身份信息,返回登录");
            object = Result.fail("身份校验失败");
            String result = JSONObject.toJSONString(Result.fail("没有传入对应的身份信息,返回登录"));
            returnJson(response,result);
            return false;
        }
        try {
            String token = redisTemplate.opsForValue().get(tokenInfo.getAdminId());
            if (token != null && token.equals(tokenInfo.getToken())){
                System.out.println("校验成功");
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
    }

    /**
     * 在cookie中获取用户传递的token
     */
    private TokenInfo getUserToKen(HttpServletRequest request) {
        TokenInfo info = new TokenInfo();
        String adminId = request.getHeader("adminId");
        String token = request.getHeader("token");
        if (StringUtils.isNotBlank(adminId) && StringUtils.isNotBlank(token)) {
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

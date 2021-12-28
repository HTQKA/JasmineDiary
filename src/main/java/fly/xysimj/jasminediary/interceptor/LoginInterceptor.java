package fly.xysimj.jasminediary.interceptor;

import fly.xysimj.jasminediary.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.apache.commons.lang.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @program: JasmineDiary
 * @ClassName LoginInterceptor
 * @description: 登录拦截器
 * @author: 徐杨顺
 * @create: 2021-12-27 09:37
 * @Version 1.0
 **/
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 登录拦截器,会自动拦截preHandle中定义的请求
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
     @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String contextPath = session.getServletContext( ).getContextPath();
        String[] requireAuthPages = new String[]{
                "index",
        };
        //获取请求地址
        String uri = request.getRequestURI();

        uri = StringUtils.remove(uri, contextPath + "/");
        String page = uri;

        if(beginWith(page,requireAuthPages)){
            User user = (User) session.getAttribute("user");
            if (user == null) {
                //重定向到/login
                response.sendRedirect("login");
                return false;
            }
        }
        return true;
    }

    private boolean beginWith(String page, String[] requireAuthPages) {
        boolean result = false;
        for (String requiredAuthPage : requireAuthPages) {
            if (StringUtils.startsWith(page, requiredAuthPage)) {
                result = true;
                break;
            }
        }
        return result;
    }
}

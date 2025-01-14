package fly.xysimj.jasminediary.config;

import fly.xysimj.jasminediary.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: JasmineDiary
 * @ClassName MyWebConfigurer
 * @description: 拦截器
 * @author: 徐杨顺
 * @create: 2021-12-27 09:55
 * @Version 1.0
 **/
@Configuration
public class MyWebConfigurer implements WebMvcConfigurer {

    @Bean
    public LoginInterceptor getLoginInterceptor() {
        return new LoginInterceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截所有路径,除了/index.html
        LoginInterceptor userInterceptor = getLoginInterceptor();
        registry.addInterceptor(userInterceptor).addPathPatterns("/**").excludePathPatterns("/index.html","fyl/user/getVerificationCodePhoto");
    }

}

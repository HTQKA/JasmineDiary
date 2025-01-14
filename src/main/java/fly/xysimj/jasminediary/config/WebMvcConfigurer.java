package fly.xysimj.jasminediary.config;

import fly.xysimj.jasminediary.interceptor.UserInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @program: JasmineDiary
 * @ClassName WebMvcConfigurer
 * @description: 适配器
 * @author: 徐杨顺
 * @create: 2022-07-05 10:22
 * @Version 1.0
 **/
@Configuration
public class WebMvcConfigurer  extends WebMvcConfigurationSupport {

    @Bean
    public UserInterceptor getUserInterceptor(){
        return new UserInterceptor();
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getUserInterceptor()).excludePathPatterns("/fyl/user/login","/fyl/user/getVerificationCodePhoto");
        super.addInterceptors(registry);
    }
}

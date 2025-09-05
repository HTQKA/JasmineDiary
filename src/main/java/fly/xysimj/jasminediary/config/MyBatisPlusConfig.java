package fly.xysimj.jasminediary.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: JasmineDiary
 * @ClassName MyBatisPlusConfig
 * @description: MyBatis-Plus配置类，用于配置分页插件
 * @author: 徐杨顺
 * @create: 2022-07-05 10:22
 * @Version 1.0
 **/
@Configuration
public class MyBatisPlusConfig {
    
    /**
     * 配置分页插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 添加分页插件，并设置数据库类型
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
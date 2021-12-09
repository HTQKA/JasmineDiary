package fly.xysimj.jasminediary;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
@MapperScan(basePackages = {"fly.xysimj.jasminediary.mapper"})
public class JasmineDiaryApplication {

    public static void main(String[] args) {
        SpringApplication.run(JasmineDiaryApplication.class, args);
    }

}

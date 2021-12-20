package fly.xysimj.jasminediary.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: JasmineDiary
 * @ClassName TestController
 * @description: 测试postman多并发测试
 * @author: 徐杨顺
 * @create: 2021-12-20 12:19
 * @Version 1.0
 **/
@RestController
public class TestController {
    @GetMapping("/demo")
    public String testDemo() {
        return "result~";
    }
}

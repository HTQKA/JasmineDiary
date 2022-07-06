package fly.xysimj.jasminediary.controller;

import fly.xysimj.jasminediary.config.annotation.LogPrint;
import fly.xysimj.jasminediary.entity.Auth;
import fly.xysimj.jasminediary.entity.Result;
import fly.xysimj.jasminediary.service.AuthService;
import fly.xysimj.jasminediary.utils.IUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @program: JasmineDiary
 * @ClassName AuthorController
 * @description: 权限类
 * @author: 徐杨顺
 * @create: 2022-06-30 09:59
 * @Version 1.0
 **/
@RestController
@CrossOrigin
@RequestMapping("/xys")
public class AuthorController {
    @Autowired
    AuthService authService;
    //新增权限
    @RequestMapping("/addAuth")
    public Result addAuth(@RequestBody Auth auth, HttpSession httpSession){
        int i = authService.saveAuth(auth);
        if(i > 0){
            return Result.success("","新增成功");
        }
        return Result.fail(400,"新增失败","权限名称已存在");
    }
    //修改权限

    //删除权限
}

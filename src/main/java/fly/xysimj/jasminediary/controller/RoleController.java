package fly.xysimj.jasminediary.controller;

import fly.xysimj.jasminediary.entity.Result;
import fly.xysimj.jasminediary.entity.Role;
import fly.xysimj.jasminediary.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: JasmineDiary
 * @ClassName RoleController
 * @description: 角色控制器
 * @author: 徐杨顺
 * @create: 2022-07-06 14:09
 * @Version 1.0
 **/
@RestController
@CrossOrigin
@RequestMapping("/xys/role")
public class RoleController {
    @Autowired
    RoleService roleService;
    @PostMapping("/addRole")
    public Result addRole(@RequestBody Role role, HttpServletRequest request) {
        return roleService.addRole(role);
    }
}

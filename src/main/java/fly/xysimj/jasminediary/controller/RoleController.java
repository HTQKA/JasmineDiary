package fly.xysimj.jasminediary.controller;

import fly.xysimj.jasminediary.entity.Result;
import fly.xysimj.jasminediary.entity.Role;
import fly.xysimj.jasminediary.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/fyl/role")
@Tag(name = "角色管理", description = "角色管理接口")
public class RoleController {
    @Autowired
    RoleService roleService;

    @Operation(summary = "添加角色", description = "添加角色")
    @PostMapping("/addRole")
    public Result addRole(@Parameter(description = "角色对象") @Validated @RequestBody Role role) {
        return roleService.addRole(role);
    }

    @Operation(summary = "删除角色", description = "查询角色列表")
    @GetMapping("/deleteRole/{roleId}")
    public Result deleteRole(@Parameter(description = "角色ID") @PathVariable("roleId") Long roleId) {
        return roleService.deleteRole(roleId);
    }
    @Operation(summary = "查询角色列表", description = "查询角色列表")
    @GetMapping("/listRole")
    public Result listRole() {
        return roleService.listRole();
    }

    @Operation(summary = "更新角色", description = "更新角色")
    @PutMapping("/updateRole")
    public Result updateRole(@Parameter(description = "角色对象") @Validated @RequestBody Role role) {
        return roleService.updateRole(role);
    }





}

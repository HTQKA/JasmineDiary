package fly.xysimj.jasminediary.controller;

import fly.xysimj.jasminediary.entity.Result;
import fly.xysimj.jasminediary.entity.RolePermissionEntity;
import fly.xysimj.jasminediary.service.RolePermissionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author XYS
 * @date 2025年01月13日 18:29
 */
@RestController
@RequestMapping("/fyl/rolePermission")
@Tag(name = "角色权限管理")
@Slf4j
public class RolePermissionController {

    @Autowired
    private RolePermissionService rolePermissionService;

    @PostMapping("/add")
    public Result add(@RequestBody RolePermissionEntity rolePermissionEntity) {
        return rolePermissionService.addRolePermission(rolePermissionEntity);
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Long id) {
        return rolePermissionService.deleteRolePermission(id);
    }

    @PostMapping("/update")
    public Result update(@RequestBody RolePermissionEntity rolePermissionEntity) {
        return rolePermissionService.updateRolePermission(rolePermissionEntity);
    }

    @GetMapping("/list")
    public Result list() {
        return rolePermissionService.listRolePermissions();
    }
}

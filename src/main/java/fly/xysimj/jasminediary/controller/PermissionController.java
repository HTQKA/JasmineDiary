package fly.xysimj.jasminediary.controller;

import fly.xysimj.jasminediary.entity.PermissionEntity;
import fly.xysimj.jasminediary.entity.Result;
import fly.xysimj.jasminediary.service.PermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author XYS
 * @Date 2025年01月06日 18:55
 */
@Tag(name = "权限管理")
@RestController("permissionController")
@Slf4j
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @Operation(summary = "获取权限列表")
    public Result getPermissionList() {
        return permissionService.getPermissionList();
    }

    @Operation(summary = "添加权限")
    public Result addPermission(@Parameter(description = "权限对象") @Validated @RequestBody PermissionEntity permission) {
        return permissionService.addPermission(permission);
    }

    @Operation(summary = "删除权限")
    public Result deletePermission(@Parameter(description = "权限ID") @Validated @RequestBody Integer permissionId) {
        return permissionService.deletePermission(permissionId);
    }

    @Operation(summary = "更新权限")
    public Result updatePermission(@Parameter(description = "权限对象") @Validated @RequestBody PermissionEntity permission) {
        return permissionService.updatePermission(permission);
    }

    @Operation(summary = "获取权限详情")
    public Result getPermissionDetail(@Parameter(description = "权限ID") @Validated @RequestBody Integer permissionId) {
        return permissionService.getPermissionById(permissionId);
    }
}

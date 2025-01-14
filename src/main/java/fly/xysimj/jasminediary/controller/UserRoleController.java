package fly.xysimj.jasminediary.controller;

import fly.xysimj.jasminediary.entity.Result;
import fly.xysimj.jasminediary.entity.UserRoleEntity;
import fly.xysimj.jasminediary.service.UserRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author XYS
 * @date 2025年01月13日 18:29
 */
@Tag(name = "用户角色管理")
@Slf4j
@RestController
@RequestMapping("/fyl/userRole")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;


    @PostMapping("/add")
    @Operation(summary = "添加用户角色")
    public Result addUserRole(@RequestBody UserRoleEntity userRoleEntity) {
        userRoleService.addUserRole(userRoleEntity);

        return Result.success("添加成功");
    }

    @PostMapping("/delete")
    @Operation(summary = "删除用户角色")
    public Result deleteUserRole(@RequestParam("id") Long id) {
        return  userRoleService.deleteUserRole(id);
    }

    @PostMapping("/update")
    @Operation(summary = "更新用户角色")
    public Result updateUserRole(@RequestBody UserRoleEntity userRoleEntity) {
        return userRoleService.updateUserRole(userRoleEntity);
    }

    @GetMapping("/list")
    @Operation(summary = "查询用户角色列表")
    public Result listUserRole() {
        return userRoleService.listUserRole();
    }

}

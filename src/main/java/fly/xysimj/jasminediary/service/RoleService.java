package fly.xysimj.jasminediary.service;

import fly.xysimj.jasminediary.entity.Result;
import fly.xysimj.jasminediary.entity.Role;
import fly.xysimj.jasminediary.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: JasmineDiary
 * @ClassName RoleService
 * @description: 角色业务类
 * @author: 徐杨顺
 * @create: 2022-06-30 11:00
 * @Version 1.0
 **/
@Service
public class RoleService {
    @Autowired
    RoleMapper roleMapper;

    public Result addRole(Role role) {
        int i = roleMapper.insert(role);
        if (i > 0) {
            return Result.success("新增成功");
        }
        return Result.fail("新增失败");
    }

    public Result deleteRole(Long roleId) {
        //查询是否存在
        int i = roleMapper.deleteById(roleId);
        return Result.success("删除成功");
    }

    public Result listRole() {
        return Result.success(roleMapper.getAllRoles());
    }

    public Result updateRole(Role role) {
        int i = roleMapper.updateById(role);
        if (i > 0) {
            return Result.success("更新成功");
        }
        return Result.fail("更新失败");
    }
}

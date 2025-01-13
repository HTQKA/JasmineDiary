package fly.xysimj.jasminediary.service.impl;

import fly.xysimj.jasminediary.entity.Result;
import fly.xysimj.jasminediary.entity.RolePermissionEntity;
import fly.xysimj.jasminediary.mapper.RolePermissionMapper;
import fly.xysimj.jasminediary.service.RolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author XYS
 * @date 2025年01月13日 18:34
 */
@Service
@Slf4j
public class RolePermissionServiceImpl implements RolePermissionService {
    @Autowired
    private RolePermissionMapper reRolePermissionMapper;
    @Override
    public Result addRolePermission(RolePermissionEntity rolePermissionEntity) {
        int insert = reRolePermissionMapper.insert(rolePermissionEntity);
        return Result.success("添加角色权限成功");
    }

    @Override
    public Result deleteRolePermission(Long id) {
        int delete = reRolePermissionMapper.deleteById(id);
        if (delete == 1) {
            return Result.success();
        }
        return Result.fail("删除角色权限失败");
    }

    @Override
    public Result updateRolePermission(RolePermissionEntity rolePermissionEntity) {
        int update = reRolePermissionMapper.updateById(rolePermissionEntity);
        if (update == 1) {
            return Result.success();
        }
        return Result.fail("更新角色权限失败");
    }


    @Override
    public Result listRolePermissions() {
        List<RolePermissionEntity> list = reRolePermissionMapper.selectList(null);
        return Result.success(list);
    }
}

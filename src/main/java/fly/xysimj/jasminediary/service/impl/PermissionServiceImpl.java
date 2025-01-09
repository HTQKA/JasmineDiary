package fly.xysimj.jasminediary.service.impl;

import fly.xysimj.jasminediary.entity.PermissionEntity;
import fly.xysimj.jasminediary.entity.Result;
import fly.xysimj.jasminediary.mapper.PermissionMapper;
import fly.xysimj.jasminediary.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author XYS
 * @date 2025年01月06日 18:58
 */
@Slf4j
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    @Override
    public Result getPermissionList() {
        return Result.success(permissionMapper.selectList(null));
    }

    @Override
    public Result addPermission(PermissionEntity permissionEntity) {
        permissionMapper.insert(permissionEntity);
        return Result.success();
    }

    @Override
    public Result deletePermission(Long permissionId) {
        int i = permissionMapper.deleteById(permissionId);
        if (i == 1) {
            return Result.success();
        } else {
            return Result.fail("删除失败");
        }
    }

    @Override
    public Result updatePermission(PermissionEntity permission) {
        permissionMapper.updateById(permission);
        return Result.success();
    }

    @Override
    public Result getPermissionById(Long permissionId) {
        PermissionEntity permissionEntity = permissionMapper.selectById(permissionId);
        if (permissionEntity!= null) {
            return Result.success(permissionEntity);
        } else {
            return Result.fail("没有找到该权限");
        }
    }
}

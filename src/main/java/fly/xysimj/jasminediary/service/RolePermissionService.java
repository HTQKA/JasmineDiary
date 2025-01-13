package fly.xysimj.jasminediary.service;

import fly.xysimj.jasminediary.entity.Result;
import fly.xysimj.jasminediary.entity.RolePermissionEntity;

/**
 * @author XYS
 * @date 2025年01月13日 18:32
 */
public interface RolePermissionService {
    Result addRolePermission(RolePermissionEntity rolePermissionEntity);

    Result deleteRolePermission(Long id);

    Result updateRolePermission(RolePermissionEntity rolePermissionEntity);

    Result listRolePermissions();
}

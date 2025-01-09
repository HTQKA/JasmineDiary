package fly.xysimj.jasminediary.service;

import fly.xysimj.jasminediary.entity.PermissionEntity;
import fly.xysimj.jasminediary.entity.Result;



/**
 * @author XYS
 * @date 2025年01月06日 18:58
 */
public interface PermissionService {
    Result getPermissionList();

    Result addPermission(PermissionEntity parameter);

    Result deletePermission(Long permissionId);

    Result updatePermission(PermissionEntity permission);

    Result getPermissionById(Long permissionId);

}

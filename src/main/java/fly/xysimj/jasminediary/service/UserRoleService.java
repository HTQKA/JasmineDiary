package fly.xysimj.jasminediary.service;

import fly.xysimj.jasminediary.entity.Result;
import fly.xysimj.jasminediary.entity.UserRoleEntity;

/**
 * @author XYS
 * @date 2025年01月13日 18:32
 */
public interface UserRoleService {
    Result addUserRole(UserRoleEntity userRoleEntity);

    Result deleteUserRole(Long id);

    Result updateUserRole(UserRoleEntity userRoleEntity);

    Result listUserRole();
}

package fly.xysimj.jasminediary.service.impl;

import fly.xysimj.jasminediary.entity.Result;
import fly.xysimj.jasminediary.entity.UserRoleEntity;
import fly.xysimj.jasminediary.mapper.UserRoleMapper;
import fly.xysimj.jasminediary.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author XYS
 * @date 2025年01月13日 18:34
 */
@Slf4j
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Override
    public Result addUserRole(UserRoleEntity userRoleEntity) {
        userRoleMapper.insert(userRoleEntity);
        return Result.success("添加成功");
    }

    @Override
    public Result deleteUserRole(Long id) {
        int i = userRoleMapper.deleteById(id);
        if (i == 1) {
            return Result.success("删除成功");
        }
        return Result.fail("删除失败");
    }

    @Override
    public Result updateUserRole(UserRoleEntity userRoleEntity) {
        int i = userRoleMapper.updateById(userRoleEntity);
        if (i == 1) {
            return Result.success("更新成功");
        }
        return Result.fail("更新失败");
    }

    @Override
    public Result listUserRole() {
        List<UserRoleEntity> userRoleEntities = userRoleMapper.selectList(null);
        return Result.success(userRoleEntities);
    }
}

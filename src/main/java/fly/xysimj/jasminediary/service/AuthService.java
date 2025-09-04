package fly.xysimj.jasminediary.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fly.xysimj.jasminediary.entity.Auth;
import fly.xysimj.jasminediary.mapper.AuthMapper;
import fly.xysimj.jasminediary.utils.IDate;
import fly.xysimj.jasminediary.utils.IUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: JasmineDiary
 * @ClassName AuthService
 * @description: 权限业务类
 * @author: 徐杨顺
 * @create: 2022-07-01 13:13
 * @Version 1.0
 **/
@Service
public class AuthService {
    @Autowired
    AuthMapper authMapper;

    //查询所有权限（分页）
    public Page<Auth> getAllAuth(int pageNum, int pageSize, Auth auth){
        // 创建分页对象
        Page<Auth> page = new Page<>(pageNum, pageSize);
        
        // 创建LambdaQueryWrapper
        LambdaQueryWrapper<Auth> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        
        // 添加查询条件
        if(auth != null){
            if(!IUtils.isEmpty(auth.getTitle())){
                lambdaQueryWrapper.like(Auth::getTitle, auth.getTitle());
            }
            if(!IUtils.isEmpty(auth.getModule())){
                lambdaQueryWrapper.eq(Auth::getModule, auth.getModule());
            }
            if(!IUtils.isEmpty(auth.getStatus())){
                lambdaQueryWrapper.eq(Auth::getStatus, auth.getStatus());
            }
            if(!IUtils.isEmpty(auth.getPid())){
                lambdaQueryWrapper.eq(Auth::getPid, auth.getPid());
            }
        }
        
        // 添加排序条件
        lambdaQueryWrapper.orderByAsc(Auth::getPriority);
        
        // 执行分页查询
        return authMapper.selectPage(page, lambdaQueryWrapper);
    }
    
    //查询所有权限（不分页，保持原有接口兼容）
    public List<Auth> getAllAuth(Auth auth){
        LambdaQueryWrapper<Auth> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        
        if(auth != null){
            if(!IUtils.isEmpty(auth.getTitle())){
                lambdaQueryWrapper.like(Auth::getTitle, auth.getTitle());
            }
            if(!IUtils.isEmpty(auth.getModule())){
                lambdaQueryWrapper.eq(Auth::getModule, auth.getModule());
            }
            if(!IUtils.isEmpty(auth.getStatus())){
                lambdaQueryWrapper.eq(Auth::getStatus, auth.getStatus());
            }
            if(!IUtils.isEmpty(auth.getPid())){
                lambdaQueryWrapper.eq(Auth::getPid, auth.getPid());
            }
        }
        
        lambdaQueryWrapper.orderByAsc(Auth::getPriority);
        return authMapper.selectList(lambdaQueryWrapper);
    }

    //根据ID查看权限
    public Auth getAuthById(String id){
        if(IUtils.isEmpty(id)){
            return null;
        }
        return authMapper.selectById(id);
    }

    //删除权限
    public int deleteAuth(String id){
        if(IUtils.isEmpty(id)){
            return 0;
        }
        //先检查是否有子权限
        LambdaQueryWrapper<Auth> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Auth::getPid, id);
        List<Auth> childAuths = authMapper.selectList(lambdaQueryWrapper);
        if(childAuths != null && !childAuths.isEmpty()){
            //有子权限，不允许删除
            return -1;
        }
        return authMapper.deleteById(id);
    }

    //修改权限
    public int updateAuth(Auth auth){
        if(auth == null || IUtils.isEmpty(auth.getId())){
            return 0;
        }
        //检查是否有重复的权限
        Auth existingAuth = authMapper.getByAction(auth);
        if(existingAuth != null && !existingAuth.getId().equals(auth.getId())){
            //存在相同action的其他权限
            return -1;
        }
        auth.setLastUpdateTime(IDate.getTimestamp());
        auth.setLastUpdateBy("XYS"); // 实际应用中应该从当前登录用户获取
        return authMapper.updateById(auth);
    }

    //新增权限
    public int saveAuth(Auth auth){
        //新增是先判断是否有重复的权限
        Auth byAction = authMapper.getByAction(auth);
        if(byAction == null){
            auth.setId(IUtils.getUUID());
            auth.setLastUpdateTime(IDate.getTimestamp());
            auth.setLastUpdateBy("XYS");
            auth.setStatus("Y");
            int i = authMapper.saveAuth(auth);
            return i;
        }
        return 0;
    }
}

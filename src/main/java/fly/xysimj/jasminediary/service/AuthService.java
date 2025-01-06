package fly.xysimj.jasminediary.service;

import fly.xysimj.jasminediary.entity.Auth;
import fly.xysimj.jasminediary.mapper.AuthMapper;
import fly.xysimj.jasminediary.utils.IDate;
import fly.xysimj.jasminediary.utils.IStr;
import fly.xysimj.jasminediary.utils.IUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;

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

    //查询所有权限
    public ArrayList<Auth> getAllAuth(Auth auth){
        return null;
    }

    //删除权限

    //修改权限

    //新增权限

    public int saveAuth(Auth auth){
        //新增是先判断是否有重复的权限,父权权限是否一致
        Auth byAction = authMapper.getByAction(auth);
        if(byAction ==null){
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

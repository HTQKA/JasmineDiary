package fly.xysimj.jasminediary.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fly.xysimj.jasminediary.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

/**
* @author TheShun
* @description 针对表【xys_sys_role】的数据库操作Mapper
* @createDate 2022-06-30 10:56:38
* @Entity generator.domain.Role
*/
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    @Select("select * from role ")
    ArrayList<Role> getAllRoles();


}

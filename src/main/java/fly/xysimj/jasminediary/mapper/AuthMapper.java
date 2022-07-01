package fly.xysimj.jasminediary.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fly.xysimj.jasminediary.entity.Auth;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;


/**
* @author TheShun
* @description 针对表【xys_sys_auth】的数据库操作Mapper
* @createDate 2022-07-01 13:44:25
* @Entity generator.domain.Auth
*/
@Mapper
public interface AuthMapper extends BaseMapper<Auth> {

    @Insert("insert into xys_sys_auth (id,pid,action_key,action,title,module,priority,menu,menu_key,menu_icon,lm_user,lm_time,level,isfunc,status ) values " +
            "(#{id},#{pid},#{actionKey},#{action},#{title},#{module},#{priority},#{menu},#{menuKey},#{menuIcon},#{lmUser},#{lmTime},#{level},#{isfunc},#{status})")
    int saveAuth(Auth auth);

    @Select("select * from xys_sys_auth where action = #{action}")
    Auth getByAction(Auth auth);
}

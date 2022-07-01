package fly.xysimj.jasminediary.mapper;

import fly.xysimj.jasminediary.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
/**
 * @author TheShun
 */
@Mapper
public interface UserMapper {
    @Select("select * from user")
    ArrayList<User> getAllUsers();

    ArrayList<User> getAllUser();

    @Select("select * from user where username = #{username} and password=#{password}")
    User getUser(@Param("username") String username, @Param("password") String password);
}

package fly.xysimj.jasminediary.mapper;

import fly.xysimj.jasminediary.entity.User;
import fly.xysimj.jasminediary.mapper.sql.UserSqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
/**
 * @author TheShun
 */
@Mapper
public interface UserMapper{
    @Select("select * from user")
    ArrayList<User> getAllUsers();

    ArrayList<User> getAllUser();

    @Select("select * from user where username = #{username} and password=#{password}")
    User getUser(@Param("username") String username, @Param("password") String password);

    @SelectProvider(method = "getUserById",type = UserSqlProvider.class)
   // @ResultMap(value = "user")
    User getUserById(@Param("id") Integer id);




}

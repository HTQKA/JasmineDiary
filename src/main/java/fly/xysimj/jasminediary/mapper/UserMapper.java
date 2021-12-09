package fly.xysimj.jasminediary.mapper;

import fly.xysimj.jasminediary.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
@Mapper
public interface UserMapper {
    @Select("select * from user")
    ArrayList<User> getAllUsers();

    ArrayList<User> getAllUser();
}

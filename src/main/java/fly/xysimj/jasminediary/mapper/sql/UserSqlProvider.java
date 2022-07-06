package fly.xysimj.jasminediary.mapper.sql;

import org.apache.ibatis.jdbc.SQL;

/**
 * @program: JasmineDiary
 * @ClassName UserSqlProvider
 * @description: userSql
 * @author: 徐杨顺
 * @create: 2022-07-05 13:53
 * @Version 1.0
 **/
public class UserSqlProvider {

    public String getUserById(final Integer id){
        String sql  = new SQL(){
            {
                SELECT("*");
                FROM("user");
                WHERE("1=1 and id = '" + id +"'");
            }
        }.toString();
        return sql;
    }
}

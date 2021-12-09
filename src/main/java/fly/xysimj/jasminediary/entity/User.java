package fly.xysimj.jasminediary.entity;

import lombok.Data;

/**
 * @program: JasmineDiary
 * @ClassName User
 * @description: 用户实体类
 * @author: 徐杨顺
 * @create: 2021-12-09 13:56
 * @Version 1.0
 **/
@Data
public class User {
    private int id;
    private String username;
    private String password;
}

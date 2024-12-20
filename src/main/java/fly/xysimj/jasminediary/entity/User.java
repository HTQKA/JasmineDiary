package fly.xysimj.jasminediary.entity;

import lombok.Data;

import java.util.Date;

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
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private Date createTime;
    private Date updateTime;
    private Integer status;
    private Date lastLogin;
    private String lastLoginIp;
    private String lastLoginAddress;
    private String loginNum;
}

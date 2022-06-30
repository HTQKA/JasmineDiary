package fly.xysimj.jasminediary.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @program: JasmineDiary
 * @ClassName Auth
 * @description: 权限类
 * @author: 徐杨顺
 * @create: 2022-06-30 10:10
 * @Version 1.0
 **/

@Data
public class Auth {
    private String id;
    private String pid;
    private String action_key;
    private String action;
    private String title;
    private String module;
    private int priority;
    private String menu;
    private String menu_key;
    private String menu_icon;
    private String lm_user;
    private Timestamp lm_time;
    private int level;
    private String isFunc;
    private String status;

}

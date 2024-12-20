package fly.xysimj.jasminediary.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author TheShun
 * @TableName xys_sys_auth
 */
@Data
public class Auth extends BaseEntity implements Serializable {
    /**
     * 主键
     */
    private String id;

    /**
     * 父权限id
     */
    private String pid;

    /**
     * 权限标识
     */
    private String actionKey;

    /**
     * 权限url
     */
    private String action;

    /**
     * 权限名称
     */
    private String title;

    /**
     * 所属模块
     */
    private String module;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 是否是菜单YorN
     */
    private String menu;

    /**
     * 菜单I18N
     */
    private String menuKey;

    /**
     * 菜单icon
     */
    private String menuIcon;


    /**
     * 权限等级
     */
    private Integer level;

    /**
     * 是否有特殊权限Y,N
     */
    private String isfunc;

    /**
     * 权限状态Y,N
     */
    private String status;

    /**
     *
     */
    private String xys1;

    /**
     *
     */
    private String xys2;

    /**
     *
     */
    private String xys3;

    private static final long serialVersionUID = 1L;

}

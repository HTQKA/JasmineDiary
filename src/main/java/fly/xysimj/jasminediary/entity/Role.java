package fly.xysimj.jasminediary.entity;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @TableName xys_sys_role
 */
@Data
public class Role extends BaseEntity implements Serializable {
    /**
     * 主键
     */
    private String id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色等级
     */
    private Integer roleLevel;

    /**
     * 角色描述
     */
    private String note;

    /**
     * 最后修改人
     */
    private String lastUpdateUser;

    /**
     * 最后修改时间
     */
    private Date lastUpdateTime;

    /**
     * 状态,Y启用 ,N禁用
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



}

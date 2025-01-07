package fly.xysimj.jasminediary.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @TableName xys_sys_role
 */

@Data
@TableName("role")
public class Role extends BaseEntity implements Serializable {
    /**
     * 主键
     */
    @Schema(description = "主键")
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 角色名称
     */
    @Schema(description = "角色名称")
    private String roleName;

    /**
     * 角色等级
     */
    @Schema(description = "角色等级")
    private Integer roleLevel;

    /**
     * 角色描述
     */
    @Schema(description = "角色描述")
    private String note;

    /**
     * 最后修改人
     */
    @Schema(description = "最后修改人")
    private String lastUpdateUser;

    /**
     * 最后修改时间
     */
    @Schema(description = "最后修改时间")
    private Date lastUpdateTime;

    /**
     * 状态,Y启用 ,N禁用
     */
    @Schema(description = "状态,Y启用 ,N禁用")
    private String status;

    @TableLogic(value = "0",delval = "1")
    @Schema(description = "删除标记,0未删除,1已删除",defaultValue = "0")
    private String deleteFlag;



}

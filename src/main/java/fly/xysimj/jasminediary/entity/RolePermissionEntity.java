package fly.xysimj.jasminediary.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

/**
 * @author XYS
 * @date 2025年01月13日 18:31
 */
@Data
@Tag(name = "角色权限关系表")
public class RolePermissionEntity  extends BaseEntity {
    @Schema(description = "主键ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @Schema(description = "角色ID")
    private Long roleId;
    @Schema(description = "权限ID")
    private Long permissionId;

}

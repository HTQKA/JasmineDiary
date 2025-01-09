package fly.xysimj.jasminediary.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * @author XYS
 * @date 2025年01月06日 19:06
 */
@Schema(description = "权限实体类")
@Data
@NoArgsConstructor
@TableName("permission")
public class PermissionEntity extends BaseEntity {
    @Schema(description = "权限ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @Schema(description = "权限名称")
    private String permissionName;
    @Schema(description = "权限描述")
    private String description;
    @Schema(description = "权限类型")
    private String type;
    @Schema(description = "权限路径")
    @NonNull
    private String url;
    @Schema(description = "权限状态")
    private Integer status;

}

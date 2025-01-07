package fly.xysimj.jasminediary.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author XYS
 * @date 2025年01月06日 19:06
 */
@Schema(description = "权限实体类")
@Data
@TableName("permission")
public class PermissionEntity extends BaseEntity {
    @Schema(description = "权限ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @Schema(description = "权限名称")
    private String name;
    @Schema(description = "权限描述")
    private String description;
    @Schema(description = "权限类型")
    private String type;
    @Schema(description = "权限路径")
    private String url;
    @Schema(description = "权限状态")
    private Integer status;

}

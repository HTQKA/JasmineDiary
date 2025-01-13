package fly.xysimj.jasminediary.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

/**
 * @author XYS
 * @date 2025年01月13日 18:30
 */
@Tag(name = "用户角色关系表")
@Data
public class UserRoleEntity extends BaseEntity {
    @Schema(description = "id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @Schema(description = "用户id")
    private Long userId;
    @Schema(description = "角色id")
    private Long roleId;

}

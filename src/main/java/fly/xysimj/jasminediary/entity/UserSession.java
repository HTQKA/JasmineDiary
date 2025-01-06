package fly.xysimj.jasminediary.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * @author XYS
 * @date 2025年01月06日 10:27
 * @description 用户会话实体类
 */
@Schema(description = "存储用户信息")
@Data
public class UserSession {
    @Schema(description = "会话ID")
    private String sessionId;
    @Schema(description = "用户ID")
    private String userId;
    @Schema(description = "用户名")
    private String username;
    @Schema(description = "登录时间")
    private Date loginTime;
}

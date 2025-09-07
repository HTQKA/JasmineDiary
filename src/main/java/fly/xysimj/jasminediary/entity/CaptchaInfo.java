package fly.xysimj.jasminediary.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "验证码返回主体")
public class CaptchaInfo {
    private String code;
    private  byte[] image;

    @Schema(description = "验证码缓存key")
    private String captchaKey;

    @Schema(description = "验证码图片Base64字符串")
    private String captchaBase64;
}

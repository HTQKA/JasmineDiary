package fly.xysimj.jasminediary.Dto;

import lombok.Data;

@Data
public class UserLoginDto {
    private String username;
    private String password;
    private String captchaCode;
    private String captchaKey;
}

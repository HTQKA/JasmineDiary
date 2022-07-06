package fly.xysimj.jasminediary.entity;

import lombok.Data;
import lombok.ToString;

/**
 * @program: JasmineDiary
 * @ClassName TokenInfo
 * @description:
 * @author: 徐杨顺
 * @create: 2022-07-05 10:41
 * @Version 1.0
 **/
@Data
@ToString
public class TokenInfo {

    private String adminId;
    private String token;
}

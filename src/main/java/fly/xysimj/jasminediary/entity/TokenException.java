package fly.xysimj.jasminediary.entity;

import lombok.Data;

/**
 * @program: JasmineDiary
 * @ClassName TokenException
 * @description: token自定义异常
 * @author: 徐杨顺
 * @create: 2022-07-05 15:18
 * @Version 1.0
 **/
@Data
public class TokenException extends RuntimeException{
    private Integer code;
    private String message;
    public TokenException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public TokenException() {
    }
}

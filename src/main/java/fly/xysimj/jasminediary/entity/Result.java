package fly.xysimj.jasminediary.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @program: JasmineDiary
 * @ClassName Result
 * @description: 返回结果类
 * @author: 徐杨顺
 * @create: 2021-12-10 12:40
 * @Version 1.0
 **/
@Data
@Getter
@Setter
public class Result {
    private int code;//返回代码
    private String message;//返回信息

    public Result(int code) {
        this.code = code;
    }

}

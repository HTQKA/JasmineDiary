package fly.xysimj.jasminediary.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.ResultSet;

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
public class Result implements Serializable {
    //uuid,用作唯一标识符，供序列化和反序列化时检测是否一致
    private static final long serialVersionUID = 7498483649536881777L;
    private Integer code;
    private String message;
    private Boolean success;
    private Object data;

    public Result(Integer code) {
        this.code = code;
    }
    public Result(Integer code,String message) {
        this.code = code;
        this.message = message;
    }
    public Result(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result() {
    }

    public static Result success(Object data, String message){
        Result result = new Result();
        result.setCode(200);
        result.setData(data);
        result.setMessage(message);
        result.setSuccess(true);
        return result;
    }


    public static Result success() {
        return success(null, "成功");
    }
    public static Result success(Object data) {
        return success(data, "");
    }

    public static Result fail(Integer code,String message){
        Result result = new Result();
        result.setCode(code);
        result.setData(null);
        result.setSuccess(Boolean.FALSE);
        result.setMessage(message);
        return result;
    }

    public static Result fail(Integer code,String message,Object data){
        Result result = new Result();
        result.setCode(code);
        result.setData(data);
        result.setSuccess(false);
        result.setMessage(message);
        return result;
    }

    public static Result fail(String message){
        Result result = new Result();
        result.setSuccess(Boolean.FALSE);
        result.setMessage(message);
        return result;
    }

}

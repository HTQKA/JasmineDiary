package fly.xysimj.jasminediary.error;

import fly.xysimj.jasminediary.entity.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: JasmineDiary
 * @ClassName GlobalExceptionHandler
 * @description: 全局异常处理
 * @author: 徐杨顺
 * @create: 2022-06-29 15:27
 * @Version 1.0
 **/

@ControllerAdvice
public class GlobalExceptionHandler {
    // 全局异常处理
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.fail(400,"请求错误",e.getMessage());
    }

    // 特定异常处理
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result error(ArithmeticException e) {
        e.printStackTrace();
        return Result.fail(400,"请求错误",e.getMessage());
    }

}

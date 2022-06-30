package fly.xysimj.jasminediary.config.aspect;

import com.alibaba.fastjson.JSONObject;
import fly.xysimj.jasminediary.config.annotation.LogPrint;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: JasmineDiary
 * @ClassName LogPrintAspect
 * @description: 日志输出切面
 * @author: 徐杨顺
 * @create: 2022-06-28 11:18
 * @Version 1.0
 **/
@Aspect
@Component
public class LogPrintAspect {
    //换行符
    private static final String LINE_SEPARATOR = System.lineSeparator();

    private static final Logger log =  LoggerFactory.getLogger(LoggerFactory.class);
    @Pointcut("@annotation(fly.xysimj.jasminediary.config.annotation.LogPrint)")
    public void logPrint(){}

    @Before("logPrint()")
    public void deBefore(JoinPoint joinPoint) throws Throwable{
        //开始打印请求日志

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 获取 @WebLog 注解的描述信息
        String methodDescription = getAspectLogDescription(joinPoint);

        // 打印请求相关参数

        log.info("========================================== Start ==========================================");
        // 打印请求 url
        log.info("URL            : {}", request.getRequestURL().toString());
        // 打印描述信息
        log.info("Description    : {}", methodDescription);
        // 打印 Http method
        log.info("HTTP Method    : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        // 打印请求的 IP
        log.info("IP             : {}", request.getRemoteAddr());
        // 打印请求入参
        log.info("Request Args   : {}", getParams(joinPoint));
    }

    @After("logPrint()")
    public void doAfter() throws Throwable{
        // 接口结束后换行，方便分割查看
        log.info("=========================================== End ===========================================" + LINE_SEPARATOR);
    }

    @Around("logPrint()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        //打印参数
        log.info("Response Args: {}" , JSONObject.toJSONString(result));
        //执行耗时
        log.info("Time-Consuming : {} ms", System.currentTimeMillis() - startTime);
        return result;
    }

    public String getAspectLogDescription(JoinPoint joinPoint)
            throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        StringBuilder description = new StringBuilder("");
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description.append(method.getAnnotation(LogPrint.class).description());
                    break;
                }
            }
        }
        return description.toString();
    }

    private String getParams(JoinPoint joinPoint) {
        String params = "";
        if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
            for (int i = 0; i < joinPoint.getArgs().length; i++) {
                Object arg = joinPoint.getArgs()[i];
                if ((arg instanceof HttpServletResponse) || (arg instanceof HttpServletRequest)
                        || (arg instanceof MultipartFile) || (arg instanceof MultipartFile[])) {
                    continue;
                }
                try {
                    params +=JSONObject.toJSONString(joinPoint.getArgs()[i]);
                } catch (Exception e1) {
                    log.error(e1.getMessage());
                }
            }
        }
        return params;
    }
}

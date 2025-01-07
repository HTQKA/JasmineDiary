package fly.xysimj.jasminediary.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author XYS
 * @date 2025年01月07日 20:03
 */
@Component
public class RequestUtils {

        public static HttpServletRequest getRequest(){
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        }
}

package fly.xysimj.jasminediary.interceptor.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import fly.xysimj.jasminediary.commom.UserThreadLocal;
import fly.xysimj.jasminediary.entity.BaseEntity;
import fly.xysimj.jasminediary.entity.UserSession;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author XYS
 * @date 2025年01月03日 14:47
 */
@Component
public class DefaultDBFieldHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        if (Objects.nonNull(metaObject) && metaObject.getOriginalObject() instanceof BaseEntity) {
            BaseEntity baseEntity = (BaseEntity) metaObject.getOriginalObject();
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            baseEntity.setCreateTime(timestamp);
            baseEntity.setLastUpdateTime(timestamp);

            //获取用户信息
            UserSession user = (UserSession)UserThreadLocal.getUser();
            //获取请求对象
            //HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            //String token = request.getHeader("token");
            if (Objects.nonNull(user)) {
                baseEntity.setCreateBy(user.getUsername());
                baseEntity.setCreateById(user.getUserId());
                baseEntity.setLastUpdateBy(user.getUsername());
                baseEntity.setLastUpdateById(user.getUserId());
            }


        }
    }
    @Override
    public void updateFill(MetaObject metaObject) {
        if (Objects.nonNull(metaObject) && metaObject.getOriginalObject() instanceof BaseEntity) {
            BaseEntity baseEntity = (BaseEntity) metaObject.getOriginalObject();
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            baseEntity.setLastUpdateTime(timestamp);

            //获取用户信息
            UserSession user = (UserSession)UserThreadLocal.getUser();
            if (Objects.nonNull(user)) {
                baseEntity.setLastUpdateBy(user.getUsername());
                baseEntity.setLastUpdateById(user.getUserId());
            }
        }
    }
}

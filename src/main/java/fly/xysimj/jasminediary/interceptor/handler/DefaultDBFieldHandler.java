package fly.xysimj.jasminediary.interceptor.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import fly.xysimj.jasminediary.entity.BaseEntity;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;
import java.util.Objects;

/**
 * @author XYS
 * @date 2025年01月03日 14:47
 */
public class DefaultDBFieldHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        if (Objects.nonNull(metaObject) && metaObject.getOriginalObject() instanceof BaseEntity) {
            BaseEntity baseEntity = (BaseEntity) metaObject.getOriginalObject();
            Date date = new Date();

            if (Objects.isNull(baseEntity.getCreateTime())) {
                baseEntity.setCreateTime(date);
            }
            if (Objects.isNull(baseEntity.getLastUpdateTime())) {
                baseEntity.setLastUpdateTime(date);
            }
            //获取用户信息
        }
    }
    @Override
    public void updateFill(MetaObject metaObject) {
        if (Objects.nonNull(metaObject) && metaObject.getOriginalObject() instanceof BaseEntity) {
            BaseEntity baseEntity = (BaseEntity) metaObject.getOriginalObject();
            Date date = new Date();

            if (Objects.isNull(baseEntity.getLastUpdateTime())) {
                baseEntity.setLastUpdateTime(date);
            }
            //获取用户信息
        }
    }
}

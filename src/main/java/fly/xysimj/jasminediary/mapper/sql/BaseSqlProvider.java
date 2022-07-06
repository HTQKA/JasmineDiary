package fly.xysimj.jasminediary.mapper.sql;

import fly.xysimj.jasminediary.entity.Sql;
import fly.xysimj.jasminediary.utils.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.builder.annotation.ProviderContext;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: JasmineDiary
 * @ClassName BaseSqlProvider
 * @description: 基础sql类
 * @author: 徐杨顺
 * @create: 2022-07-06 10:09
 * @Version 1.0
 **/
@Slf4j
public class BaseSqlProvider {

    private static final Map<String, String> BASE_SQL_CACHE_MAP = new ConcurrentHashMap<>();

    public String getById(ProviderContext context){
        return BASE_SQL_CACHE_MAP.computeIfAbsent(getCacheKey(context),value ->{
            Sql sql = new Sql();
            sql.select(fields(context));
            sql.from(tableName(context));
            sql.where(id(context));
            log.info("getById:\n{}", sql.build());
            return sql.build();
        });
    }

    public String listByIds(ProviderContext context) {
        return BASE_SQL_CACHE_MAP.computeIfAbsent(getCacheKey(context),value ->{
            Sql sql = new Sql();
            sql.select(fields(context));
            sql.from(tableName(context));
            sql.whereIn(id(context));
            log.info("listByIds:\n{}",sql.build());
            return sql.build();
        });
    }

    public String listByEntity(ProviderContext context) {
        return BASE_SQL_CACHE_MAP.computeIfAbsent(getCacheKey(context),value->{
            Sql sql = new Sql();
            sql.select(fields(context));
            sql.from(tableName(context));
            sql.where(fields(context));
            log.info("listByEntity:\n{}",sql.build());
            return sql.build();
        });
    }

    public String insert(ProviderContext context) {
        return BASE_SQL_CACHE_MAP.computeIfAbsent(getCacheKey(context),value -> {
            Sql sql = new Sql();
            sql.insertInto(tableName(context), fields(context));
            sql.values(fields(context));
            log.info("insert:\n{}", sql.build());
            return sql.build();
        });
    }

    public String insertBatch(ProviderContext context) {
        return BASE_SQL_CACHE_MAP.computeIfAbsent(getCacheKey(context),value->{
            Sql sql = new Sql();
            sql.insertIntoBatch(tableName(context), fields(context));
            sql.valuesBatch(fields(context));
            log.info("insertBatch:\n{}",sql.build());
            return sql.build();
        });
    }


    public String updateById(ProviderContext context) {
        return BASE_SQL_CACHE_MAP.computeIfAbsent(getCacheKey(context), value -> {
            Sql sql = new Sql();
            sql.update(tableName(context));
            String[] fieldsExceptId = Arrays.stream(fields(context)).filter(field -> !field.equals(id(context))).toArray(String[]::new);
            sql.set(fieldsExceptId);
            sql.where(id(context));
            log.info("updateById:\n{}", sql.build());
            return sql.build();
        });
    }

    public String deleteById(ProviderContext context) {
        return BASE_SQL_CACHE_MAP.computeIfAbsent(getCacheKey(context), value -> {
            Sql sql = new Sql();
            sql.deleteFrom(tableName(context));
            sql.where(id(context));
            log.info("deleteById:\n{}", sql.build());
            return sql.build();
        });
    }

    public String deleteByIds(ProviderContext context) {
        return BASE_SQL_CACHE_MAP.computeIfAbsent(getCacheKey(context), value -> {
            Sql sql = new Sql();
            sql.deleteFrom(tableName(context));
            sql.whereIn(id(context));
            log.info("deleteByIds:\n{}", sql.build());
            return sql.build();
        });
    }

    public String countAll(ProviderContext context) {
        return BASE_SQL_CACHE_MAP.computeIfAbsent(getCacheKey(context), value -> {
            Sql sql = new Sql();
            sql.selectCount("*");
            sql.from(tableName(context));
            log.info("countAll:\n{}", sql.build());
            return sql.build();
        });
    }

    public String countByEntity(ProviderContext context) {
        return BASE_SQL_CACHE_MAP.computeIfAbsent(getCacheKey(context), value -> {
            Sql sql = new Sql();
            sql.selectCount("*");
            sql.from(tableName(context));
            sql.where(fields(context));
            log.info("countByEntity:\n{}", sql.build());
            return sql.build();
        });
    }

    private static Class<?> entityType(ProviderContext context) {
        Class<?> clazz = context.getMapperType();
        return (Class<?>) ((ParameterizedType) (clazz.getGenericInterfaces()[0])).getActualTypeArguments()[0];
    }

    /**
     * 获取表名称
     */
    public static String tableName(ProviderContext context) {
        return StrUtil.camelToUnderscore(entityType(context).getSimpleName());
    }
    /**
     * @program: BaseSqlProvider.java
     * @ClassNmae: BaseSqlProvider
     * @description:获取所有字段名称
     * @author: TheShun
     * @date: 2022/7/6 10:26
     * @param:
     * @param: null
     * @return:
     * @return: null
     **/
    public static String[] fields(ProviderContext context) {
        Class<?> entityType = entityType(context);
        Field[] entityFields = entityType.getDeclaredFields();
        Field[] superFields = entityType.getSuperclass().getDeclaredFields();
        //检查并取得徐磊话版本号字段
        superFields = Arrays.stream(superFields).filter(field -> !"serialVersionUID".equals(field.getName())).toArray(Field[]::new);
        Field[] fields = Arrays.copyOf(entityFields, entityFields.length + superFields.length);
        System.arraycopy(superFields, 0, fields, entityFields.length, superFields.length);
        return Arrays.stream(fields).map(Field::getName).toArray(String[]::new);

    }

    private static String getCacheKey(ProviderContext context) {
        return context.getMapperType().getSimpleName() + ":" + context.getMapperMethod().getName();
    }

    /**
     * 主键,默认取实体第一个字段
     * @param context
     * @return
     */
    public static String id(ProviderContext context) {
        return fields(context)[0];
    }

}

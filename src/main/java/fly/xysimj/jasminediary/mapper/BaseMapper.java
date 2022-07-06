package fly.xysimj.jasminediary.mapper;

import fly.xysimj.jasminediary.mapper.sql.BaseSqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.Collection;
import java.util.List;
@Mapper
public interface BaseMapper <E,I>{

    @SelectProvider(type = BaseSqlProvider.class,method = "getById")
    E getById(I id);

    @SelectProvider(type = BaseSqlProvider.class,method = "listByIds")
    List<E> listByIds(Collection<I> collection);

    @SelectProvider(type = BaseSqlProvider.class,method = "listByEntity")
    List<E> listByEntity(E e);


    @InsertProvider(type = BaseSqlProvider.class, method = "insert")
    @Options(keyProperty = "id", useGeneratedKeys = true)
    int insert(E e);

    @InsertProvider(type = BaseSqlProvider.class, method = "insertBatch")
    @Options(keyProperty = "id", useGeneratedKeys = true)
    int insertBatch(Collection<E> list);

    @UpdateProvider(type = BaseSqlProvider.class, method = "updateById")
    int updateById(E e);

    @DeleteProvider(type = BaseSqlProvider.class, method = "deleteById")
    int deleteById(I id);

    @DeleteProvider(type = BaseSqlProvider.class, method = "deleteByIds")
    int deleteByIds(Collection<I> list);

    @SelectProvider(type = BaseSqlProvider.class, method = "countAll")
    int countAll();

    @SelectProvider(type = BaseSqlProvider.class, method = "countByEntity")
    int countByEntity(E e);

}

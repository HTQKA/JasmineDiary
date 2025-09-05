package fly.xysimj.jasminediary.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fly.xysimj.jasminediary.entity.Department;
import org.apache.ibatis.annotations.Mapper;

/**
 * @program: JasmineDiary
 * @ClassName DepartmentMapper
 * @description: 部门Mapper接口
 * @author: 徐杨顺
 * @create: 2022-08-29 15:40
 * @Version 1.0
 **/
@Mapper
public interface DepartmentMapper extends BaseMapper<Department> {

}
package fly.xysimj.jasminediary.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fly.xysimj.jasminediary.entity.Department;
import fly.xysimj.jasminediary.mapper.DepartmentMapper;
import fly.xysimj.jasminediary.utils.IUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: JasmineDiary
 * @ClassName DepartmentService
 * @description: 部门服务类
 * @author: 徐杨顺
 * @create: 2022-08-29 15:45
 * @Version 1.0
 **/
@Service
public class DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    //分页查询所有部门
    public Page<Department> getAllDepartments(int pageNum, int pageSize, Department department) {
        // 创建分页对象
        Page<Department> page = new Page<>(pageNum, pageSize);
        
        // 创建LambdaQueryWrapper
        LambdaQueryWrapper<Department> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        
        // 添加查询条件
        if (department != null) {
            if (!IUtils.isEmpty(department.getDepartmentName())) {
                lambdaQueryWrapper.like(Department::getDepartmentName, department.getDepartmentName());
            }
            if (!IUtils.isEmpty(department.getStatus())) {
                lambdaQueryWrapper.eq(Department::getStatus, department.getStatus());
            }
            if (department.getParentId() != null) {
                lambdaQueryWrapper.eq(Department::getParentId, department.getParentId());
            }
        }
        
        // 添加排序条件
        lambdaQueryWrapper.orderByAsc(Department::getSort);
        
        // 执行分页查询
        return departmentMapper.selectPage(page, lambdaQueryWrapper);
    }
    
    //查询所有部门（不分页）
    public List<Department> getAllDepartments(Department department) {
        LambdaQueryWrapper<Department> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        
        if (department != null) {
            if (!IUtils.isEmpty(department.getDepartmentName())) {
                lambdaQueryWrapper.like(Department::getDepartmentName, department.getDepartmentName());
            }
            if (!IUtils.isEmpty(department.getStatus())) {
                lambdaQueryWrapper.eq(Department::getStatus, department.getStatus());
            }
            if (department.getParentId() != null) {
                lambdaQueryWrapper.eq(Department::getParentId, department.getParentId());
            }
        }
        
        lambdaQueryWrapper.orderByAsc(Department::getSort);
        return departmentMapper.selectList(lambdaQueryWrapper);
    }

    //根据ID查看部门
    public Department getDepartmentById(Long id) {
        if (id == null) {
            return null;
        }
        return departmentMapper.selectById(id);
    }

    //删除部门
    public int deleteDepartment(Long id) {
        if (id == null) {
            return 0;
        }
        //先检查是否有子部门
        LambdaQueryWrapper<Department> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Department::getParentId, id);
        List<Department> childDepartments = departmentMapper.selectList(lambdaQueryWrapper);
        if (childDepartments != null && !childDepartments.isEmpty()) {
            //有子部门，不允许删除
            return -1;
        }
        return departmentMapper.deleteById(id);
    }

    //修改部门
    public int updateDepartment(Department department) {
        if (department == null || department.getId() == null) {
            return 0;
        }
        
        //检查部门是否存在
        Department oldDepartment = departmentMapper.selectById(department.getId());
        if (oldDepartment == null) {
            return 0;
        }
        
        //检查部门名称是否重复
        LambdaQueryWrapper<Department> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Department::getDepartmentName, department.getDepartmentName());
        lambdaQueryWrapper.ne(Department::getId, department.getId());
        List<Department> list = departmentMapper.selectList(lambdaQueryWrapper);
        if (list != null && !list.isEmpty()) {
            //存在重复的部门名称
            return -1;
        }
        
        return departmentMapper.updateById(department);
    }

    //新增部门
    public int saveDepartment(Department department) {
        if (department == null || IUtils.isEmpty(department.getDepartmentName())) {
            return 0;
        }
        
        //检查部门名称是否重复
        LambdaQueryWrapper<Department> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Department::getDepartmentName, department.getDepartmentName());
        List<Department> list = departmentMapper.selectList(lambdaQueryWrapper);
        if (list != null && !list.isEmpty()) {
            //存在重复的部门名称
            return -1;
        }
        
        if (department.getStatus() == null) {
            department.setStatus("Y"); // 默认启用
        }
        
        return departmentMapper.insert(department);
    }
}
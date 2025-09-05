package fly.xysimj.jasminediary.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fly.xysimj.jasminediary.entity.Department;
import fly.xysimj.jasminediary.entity.Result;
import fly.xysimj.jasminediary.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 部门管理控制器
 */
@RestController
@RequestMapping("/api/department")
@Api(tags = "部门管理")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * 分页查询部门列表
     */
    @PostMapping("/listPage")
    @ApiOperation("分页查询部门列表")
    public Result listPage(@RequestParam(defaultValue = "1") int pageNum,
                                          @RequestParam(defaultValue = "10") int pageSize,
                                          @RequestBody(required = false) Department department) {
        try {
            Page<Department> page = departmentService.getAllDepartments(pageNum, pageSize, department);
            return Result.success(page, "查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(500, "查询失败：" + e.getMessage());
        }
    }

    /**
     * 查询所有部门列表（不分页）
     */
    @PostMapping("/list")
    @ApiOperation("查询所有部门列表（不分页）")
    public Result list(@RequestBody(required = false) Department department) {
        try {
            Department departmentParam = department != null ? department : new Department();
            java.util.List<Department> list = departmentService.getAllDepartments(departmentParam);
            return Result.success(list, "查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(500, "查询失败：" + e.getMessage());
        }
    }

    /**
     * 根据ID查询部门
     */
    @GetMapping("/info/{id}")
    @ApiOperation("根据ID查询部门")
    public Result info(@PathVariable Long id) {
        try {
            Department department = departmentService.getDepartmentById(id);
            if (department != null) {
                return Result.success(department, "查询成功");
            } else {
                return Result.fail(404, "部门不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(500, "查询失败：" + e.getMessage());
        }
    }

    /**
     * 删除部门
     */
    @PostMapping("/delete/{id}")
    @ApiOperation("删除部门")
    public Result delete(@PathVariable Long id) {
        try {
            int result = departmentService.deleteDepartment(id);
            if (result == 1) {
                return Result.success(null, "删除成功");
            } else if (result == -1) {
                return Result.fail(400, "该部门下有子部门，不允许删除");
            } else {
                return Result.fail(404, "删除失败，部门不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(500, "删除失败：" + e.getMessage());
        }
    }

    /**
     * 修改部门
     */
    @PostMapping("/update")
    @ApiOperation("修改部门")
    public Result update(@RequestBody Department department) {
        try {
            int result = departmentService.updateDepartment(department);
            if (result == 1) {
                return Result.success(null, "更新成功");
            } else if (result == -1) {
                return Result.fail(400, "部门名称已存在");
            } else {
                return Result.fail(404, "更新失败，部门不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(500, "更新失败：" + e.getMessage());
        }
    }

    /**
     * 新增部门
     */
    @PostMapping("/save")
    @ApiOperation("新增部门")
    public Result save(@RequestBody Department department) {
        try {
            int result = departmentService.saveDepartment(department);
            if (result == 1) {
                return Result.success(null, "新增成功");
            } else if (result == -1) {
                return Result.fail(400, "部门名称已存在");
            } else {
                return Result.fail(400, "新增失败，部门名称不能为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(500, "新增失败：" + e.getMessage());
        }
    }
}
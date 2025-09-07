package fly.xysimj.jasminediary.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fly.xysimj.jasminediary.entity.Organization;
import fly.xysimj.jasminediary.entity.Result;
import fly.xysimj.jasminediary.service.OrganizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 机构管理控制器
 */
@RestController
@RequestMapping("/fyl/organization")
@Api(tags = "机构管理")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    /**
     * 分页查询机构列表
     */
    @PostMapping("/listPage")
    @ApiOperation("分页查询机构列表")
    public Result listPage(@RequestParam(defaultValue = "1") int pageNum,
                                           @RequestParam(defaultValue = "10") int pageSize,
                                           @RequestBody(required = false) Organization organization) {
        try {
            Page<Organization> page = organizationService.getAllOrganizations(pageNum, pageSize, organization);
            return Result.success(page, "查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(500, "查询失败：" + e.getMessage());
        }
    }

    /**
     * 查询所有机构列表（不分页）
     */
    @PostMapping("/list")
    @ApiOperation("查询所有机构列表（不分页）")
    public Result list(@RequestBody(required = false) Organization organization) {
        try {
            Organization organizationParam = organization != null ? organization : new Organization();
            java.util.List<Organization> list = organizationService.getAllOrganizations(organizationParam);
            return Result.success(list, "查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(500, "查询失败：" + e.getMessage());
        }
    }

    /**
     * 根据ID查询机构
     */
    @GetMapping("/info/{id}")
    @ApiOperation("根据ID查询机构")
    public Result info(@PathVariable Long id) {
        try {
            Organization organization = organizationService.getOrganizationById(id);
            if (organization != null) {
                return Result.success(organization, "查询成功");
            } else {
                return Result.fail(404, "机构不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(500, "查询失败：" + e.getMessage());
        }
    }

    /**
     * 删除机构
     */
    @PostMapping("/delete/{id}")
    @ApiOperation("删除机构")
    public Result delete(@PathVariable Long id) {
        try {
            int result = organizationService.deleteOrganization(id);
            if (result == 1) {
                return Result.success(null, "删除成功");
            } else if (result == -1) {
                return Result.fail(400, "该机构下有子机构，不允许删除");
            } else {
                return Result.fail(404, "删除失败，机构不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(500, "删除失败：" + e.getMessage());
        }
    }

    /**
     * 修改机构
     */
    @PostMapping("/update")
    @ApiOperation("修改机构")
    public Result update(@RequestBody Organization organization) {
        try {
            int result = organizationService.updateOrganization(organization);
            if (result == 1) {
                return Result.success(null, "更新成功");
            } else if (result == -1) {
                return Result.fail(400, "机构名称或代码已存在");
            } else {
                return Result.fail(404, "更新失败，机构不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(500, "更新失败：" + e.getMessage());
        }
    }

    /**
     * 新增机构
     */
    @PostMapping("/save")
    @ApiOperation("新增机构")
    public Result save(@RequestBody Organization organization) {
        try {
            int result = organizationService.saveOrganization(organization);
            if (result == 1) {
                return Result.success(null, "新增成功");
            } else if (result == -1) {
                return Result.fail(400, "机构名称或代码已存在");
            } else {
                return Result.fail(400, "新增失败，机构名称和代码不能为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(500, "新增失败：" + e.getMessage());
        }
    }
}
package fly.xysimj.jasminediary.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fly.xysimj.jasminediary.entity.Organization;
import fly.xysimj.jasminediary.mapper.OrganizationMapper;
import fly.xysimj.jasminediary.utils.IUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: JasmineDiary
 * @ClassName OrganizationService
 * @description: 机构服务类
 * @author: 徐杨顺
 * @create: 2022-08-29 15:50
 * @Version 1.0
 **/
@Service
public class OrganizationService {

    @Autowired
    private OrganizationMapper organizationMapper;

    //分页查询所有机构
    public Page<Organization> getAllOrganizations(int pageNum, int pageSize, Organization organization) {
        // 创建分页对象
        Page<Organization> page = new Page<>(pageNum, pageSize);
        
        // 创建LambdaQueryWrapper
        LambdaQueryWrapper<Organization> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        
        // 添加查询条件
        if (organization != null) {
            if (!IUtils.isEmpty(organization.getOrgName())) {
                lambdaQueryWrapper.like(Organization::getOrgName, organization.getOrgName());
            }
            if (!IUtils.isEmpty(organization.getOrgCode())) {
                lambdaQueryWrapper.like(Organization::getOrgCode, organization.getOrgCode());
            }
            if (!IUtils.isEmpty(organization.getStatus())) {
                lambdaQueryWrapper.eq(Organization::getStatus, organization.getStatus());
            }
            if (organization.getParentId() != null) {
                lambdaQueryWrapper.eq(Organization::getParentId, organization.getParentId());
            }
        }
        
        // 添加排序条件
        lambdaQueryWrapper.orderByAsc(Organization::getSort);
        
        // 执行分页查询
        return organizationMapper.selectPage(page, lambdaQueryWrapper);
    }
    
    //查询所有机构（不分页）
    public List<Organization> getAllOrganizations(Organization organization) {
        LambdaQueryWrapper<Organization> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        
        if (organization != null) {
            if (!IUtils.isEmpty(organization.getOrgName())) {
                lambdaQueryWrapper.like(Organization::getOrgName, organization.getOrgName());
            }
            if (!IUtils.isEmpty(organization.getOrgCode())) {
                lambdaQueryWrapper.like(Organization::getOrgCode, organization.getOrgCode());
            }
            if (!IUtils.isEmpty(organization.getStatus())) {
                lambdaQueryWrapper.eq(Organization::getStatus, organization.getStatus());
            }
            if (organization.getParentId() != null) {
                lambdaQueryWrapper.eq(Organization::getParentId, organization.getParentId());
            }
        }
        
        lambdaQueryWrapper.orderByAsc(Organization::getSort);
        return organizationMapper.selectList(lambdaQueryWrapper);
    }

    //根据ID查看机构
    public Organization getOrganizationById(Long id) {
        if (id == null) {
            return null;
        }
        return organizationMapper.selectById(id);
    }

    //删除机构
    public int deleteOrganization(Long id) {
        if (id == null) {
            return 0;
        }
        //先检查是否有子机构
        LambdaQueryWrapper<Organization> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Organization::getParentId, id);
        List<Organization> childOrganizations = organizationMapper.selectList(lambdaQueryWrapper);
        if (childOrganizations != null && !childOrganizations.isEmpty()) {
            //有子机构，不允许删除
            return -1;
        }
        return organizationMapper.deleteById(id);
    }

    //修改机构
    public int updateOrganization(Organization organization) {
        if (organization == null || organization.getId() == null) {
            return 0;
        }
        
        //检查机构是否存在
        Organization oldOrganization = organizationMapper.selectById(organization.getId());
        if (oldOrganization == null) {
            return 0;
        }
        
        //检查机构名称或代码是否重复
        LambdaQueryWrapper<Organization> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.and(wrapper -> 
            wrapper.eq(Organization::getOrgName, organization.getOrgName())
                  .or()
                  .eq(Organization::getOrgCode, organization.getOrgCode())
        );
        lambdaQueryWrapper.ne(Organization::getId, organization.getId());
        List<Organization> list = organizationMapper.selectList(lambdaQueryWrapper);
        if (list != null && !list.isEmpty()) {
            //存在重复的机构名称或代码
            return -1;
        }
        
        return organizationMapper.updateById(organization);
    }

    //新增机构
    public int saveOrganization(Organization organization) {
        if (organization == null || IUtils.isEmpty(organization.getOrgName()) || IUtils.isEmpty(organization.getOrgCode())) {
            return 0;
        }
        
        //检查机构名称或代码是否重复
        LambdaQueryWrapper<Organization> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.and(wrapper -> 
            wrapper.eq(Organization::getOrgName, organization.getOrgName())
                  .or()
                  .eq(Organization::getOrgCode, organization.getOrgCode())
        );
        List<Organization> list = organizationMapper.selectList(lambdaQueryWrapper);
        if (list != null && !list.isEmpty()) {
            //存在重复的机构名称或代码
            return -1;
        }
        
        if (organization.getStatus() == null) {
            organization.setStatus("Y"); // 默认启用
        }
        
        return organizationMapper.insert(organization);
    }
}
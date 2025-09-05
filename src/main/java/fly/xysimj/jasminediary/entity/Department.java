package fly.xysimj.jasminediary.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @program: JasmineDiary
 * @ClassName Department
 * @description: 部门实体类
 * @author: 徐杨顺
 * @create: 2022-08-29 15:30
 * @Version 1.0
 **/
@Data
@TableName("xys_sys_department")
public class Department extends BaseEntity {
    /**
     * 主键
     */
    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 部门名称
     */
    @Schema(description = "部门名称")
    private String departmentName;

    /**
     * 父部门ID
     */
    @Schema(description = "父部门ID")
    private Long parentId;

    /**
     * 部门负责人
     */
    @Schema(description = "部门负责人")
    private String leader;

    /**
     * 联系电话
     */
    @Schema(description = "联系电话")
    private String phone;

    /**
     * 部门描述
     */
    @Schema(description = "部门描述")
    private String description;

    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer sort;

    /**
     * 状态 Y-启用 N-禁用
     */
    @Schema(description = "状态")
    private String status;
}
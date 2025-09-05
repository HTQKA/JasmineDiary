package fly.xysimj.jasminediary.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @program: JasmineDiary
 * @ClassName Organization
 * @description: 机构实体类
 * @author: 徐杨顺
 * @create: 2022-08-29 15:35
 * @Version 1.0
 **/
@Data
@TableName("xys_sys_organization")
public class Organization extends BaseEntity {
    /**
     * 主键
     */
    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 机构名称
     */
    @Schema(description = "机构名称")
    private String orgName;

    /**
     * 机构代码
     */
    @Schema(description = "机构代码")
    private String orgCode;

    /**
     * 父机构ID
     */
    @Schema(description = "父机构ID")
    private Long parentId;

    /**
     * 机构负责人
     */
    @Schema(description = "机构负责人")
    private String leader;

    /**
     * 联系电话
     */
    @Schema(description = "联系电话")
    private String phone;

    /**
     * 地址
     */
    @Schema(description = "地址")
    private String address;

    /**
     * 机构描述
     */
    @Schema(description = "机构描述")
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
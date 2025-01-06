package fly.xysimj.jasminediary.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: JasmineDiary
 * @ClassName BaseEntity
 * @description: 基础实体类
 * @author: 徐杨顺
 * @create: 2022-07-06 14:16
 * @Version 1.0
 **/

@Data
public class BaseEntity implements Serializable {

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date createTime;

    /**
     * 创建者
     */
    @ApiModelProperty(value = "创建者", hidden = true)
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 创建者
     */
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建者", hidden = true)
    private String createById;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新时间", hidden = true)
    private Date lastUpdateTime;

    /**
     * 更新者
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新者", hidden = true)
    private String lastUpdateBy;

    /**
     * 更新者
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新者ID", hidden = true)
    private String lastUpdateById;

    /**
     * 逻辑删除 0-未删除 1-已删除
     */
    @ApiModelProperty(value = "逻辑删除 0-未删除 1-已删除", hidden = true)
    @TableLogic(value = "0", delval = "1")
    private String deleteFlag;
    private static final long serialVersionUID = 1L;
}

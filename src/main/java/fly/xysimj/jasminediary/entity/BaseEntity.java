package fly.xysimj.jasminediary.entity;

import java.util.Date;
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
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date createDate;

    /**
     * 创建者
     */
    @ApiModelProperty(value = "创建者", hidden = true)
    private String createBy;

    /**
     * 创建者
     */
    @ApiModelProperty(value = "创建者", hidden = true)
    private String createById;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", hidden = true)
    private Date lastUpdateDate;

    /**
     * 更新者
     */
    @ApiModelProperty(value = "更新者", hidden = true)
    private String lastUpdateBy;

    /**
     * 更新者
     */
    @ApiModelProperty(value = "更新者ID", hidden = true)
    private String lastUpdateById;

    private static final long serialVersionUID = 1L;
}

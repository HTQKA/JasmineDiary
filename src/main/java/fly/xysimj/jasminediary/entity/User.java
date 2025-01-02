package fly.xysimj.jasminediary.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * @program: JasmineDiary
 * @ClassName User
 * @description: 用户实体类
 * @author: 徐杨顺
 * @create: 2021-12-09 13:56
 * @Version 1.0
 **/
@Data
@ApiModel(value = "用户实体类", description = "用户实体类")
public class User {
    @ApiModelProperty(value = "用户ID", dataType = "Integer", required = true, example = "1")
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private Date createTime;
    private Date updateTime;
    @Schema(description = "逻辑删除标识", example = "0", required = true)
    @TableLogic
    private Integer deleteFlag;
    private Integer status;
    private Date lastLoginDate;
    private String lastLoginIp;
    private String lastLoginAddress;
    private String loginNum;
}

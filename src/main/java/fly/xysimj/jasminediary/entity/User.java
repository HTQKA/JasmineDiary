package fly.xysimj.jasminediary.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
public class User extends BaseEntity{
    @ApiModelProperty(value = "用户ID", dataType = "Integer", required = true, example = "1")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private Integer status;
    private Date lastLoginDate;
    private String lastLoginIp;
    private String lastLoginAddress;
    private String loginNum;
}

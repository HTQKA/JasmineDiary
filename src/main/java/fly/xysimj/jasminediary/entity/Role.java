package fly.xysimj.jasminediary.entity;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @TableName xys_sys_role
 */
@Data
@Getter
public class Role extends BaseEntity implements Serializable {
    /**
     * 主键
     */
    private String id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色等级
     */
    private Integer roleLevel;

    /**
     * 角色描述
     */
    private String note;

    /**
     * 最后修改人
     */
    private String lmUser;

    /**
     * 最后修改时间
     */
    private Date lmTime;

    /**
     * 状态,Y启用 ,N禁用
     */
    private String status;

    /**
     *
     */
    private String xys1;

    /**
     *
     */
    private String xys2;

    /**
     *
     */
    private String xys3;



    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Role other = (Role) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getRoleName() == null ? other.getRoleName() == null : this.getRoleName().equals(other.getRoleName()))
            && (this.getRoleLevel() == null ? other.getRoleLevel() == null : this.getRoleLevel().equals(other.getRoleLevel()))
            && (this.getNote() == null ? other.getNote() == null : this.getNote().equals(other.getNote()))
            && (this.getLmUser() == null ? other.getLmUser() == null : this.getLmUser().equals(other.getLmUser()))
            && (this.getLmTime() == null ? other.getLmTime() == null : this.getLmTime().equals(other.getLmTime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getXys1() == null ? other.getXys1() == null : this.getXys1().equals(other.getXys1()))
            && (this.getXys2() == null ? other.getXys2() == null : this.getXys2().equals(other.getXys2()))
            && (this.getXys3() == null ? other.getXys3() == null : this.getXys3().equals(other.getXys3()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRoleName() == null) ? 0 : getRoleName().hashCode());
        result = prime * result + ((getRoleLevel() == null) ? 0 : getRoleLevel().hashCode());
        result = prime * result + ((getNote() == null) ? 0 : getNote().hashCode());
        result = prime * result + ((getLmUser() == null) ? 0 : getLmUser().hashCode());
        result = prime * result + ((getLmTime() == null) ? 0 : getLmTime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getXys1() == null) ? 0 : getXys1().hashCode());
        result = prime * result + ((getXys2() == null) ? 0 : getXys2().hashCode());
        result = prime * result + ((getXys3() == null) ? 0 : getXys3().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", roleName=").append(roleName);
        sb.append(", roleLevel=").append(roleLevel);
        sb.append(", note=").append(note);
        sb.append(", lmUser=").append(lmUser);
        sb.append(", lmTime=").append(lmTime);
        sb.append(", status=").append(status);
        sb.append(", xys1=").append(xys1);
        sb.append(", xys2=").append(xys2);
        sb.append(", xys3=").append(xys3);
       // sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}

package fly.xysimj.jasminediary.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @TableName xys_sys_auth
 */
@Data
public class Auth implements Serializable {
    /**
     * 主键
     */
    private String id;

    /**
     * 父权限id
     */
    private String pid;

    /**
     * 权限标识
     */
    private String actionKey;

    /**
     * 权限url
     */
    private String action;

    /**
     * 权限名称
     */
    private String title;

    /**
     * 所属模块
     */
    private String module;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 是否是菜单YorN
     */
    private String menu;

    /**
     * 菜单I18N
     */
    private String menuKey;

    /**
     * 菜单icon
     */
    private String menuIcon;

    /**
     * 最后修改人
     */
    private String lmUser;

    /**
     * 最后修改时间
     */
    private Date lmTime;

    /**
     * 权限等级
     */
    private Integer level;

    /**
     * 是否有特殊权限Y,N
     */
    private String isfunc;

    /**
     * 权限状态Y,N
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

    private static final long serialVersionUID = 1L;

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
        Auth other = (Auth) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getActionKey() == null ? other.getActionKey() == null : this.getActionKey().equals(other.getActionKey()))
            && (this.getAction() == null ? other.getAction() == null : this.getAction().equals(other.getAction()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getModule() == null ? other.getModule() == null : this.getModule().equals(other.getModule()))
            && (this.getPriority() == null ? other.getPriority() == null : this.getPriority().equals(other.getPriority()))
            && (this.getMenu() == null ? other.getMenu() == null : this.getMenu().equals(other.getMenu()))
            && (this.getMenuKey() == null ? other.getMenuKey() == null : this.getMenuKey().equals(other.getMenuKey()))
            && (this.getMenuIcon() == null ? other.getMenuIcon() == null : this.getMenuIcon().equals(other.getMenuIcon()))
            && (this.getLmUser() == null ? other.getLmUser() == null : this.getLmUser().equals(other.getLmUser()))
            && (this.getLmTime() == null ? other.getLmTime() == null : this.getLmTime().equals(other.getLmTime()))
            && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()))
            && (this.getIsfunc() == null ? other.getIsfunc() == null : this.getIsfunc().equals(other.getIsfunc()))
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
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getActionKey() == null) ? 0 : getActionKey().hashCode());
        result = prime * result + ((getAction() == null) ? 0 : getAction().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getModule() == null) ? 0 : getModule().hashCode());
        result = prime * result + ((getPriority() == null) ? 0 : getPriority().hashCode());
        result = prime * result + ((getMenu() == null) ? 0 : getMenu().hashCode());
        result = prime * result + ((getMenuKey() == null) ? 0 : getMenuKey().hashCode());
        result = prime * result + ((getMenuIcon() == null) ? 0 : getMenuIcon().hashCode());
        result = prime * result + ((getLmUser() == null) ? 0 : getLmUser().hashCode());
        result = prime * result + ((getLmTime() == null) ? 0 : getLmTime().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        result = prime * result + ((getIsfunc() == null) ? 0 : getIsfunc().hashCode());
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
        sb.append(", pid=").append(pid);
        sb.append(", actionKey=").append(actionKey);
        sb.append(", action=").append(action);
        sb.append(", title=").append(title);
        sb.append(", module=").append(module);
        sb.append(", priority=").append(priority);
        sb.append(", menu=").append(menu);
        sb.append(", menuKey=").append(menuKey);
        sb.append(", menuIcon=").append(menuIcon);
        sb.append(", lmUser=").append(lmUser);
        sb.append(", lmTime=").append(lmTime);
        sb.append(", level=").append(level);
        sb.append(", isfunc=").append(isfunc);
        sb.append(", status=").append(status);
        sb.append(", xys1=").append(xys1);
        sb.append(", xys2=").append(xys2);
        sb.append(", xys3=").append(xys3);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}

package com.qiu.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/10/31 13:27
 * @description 用户权限
 */

@Entity
@Table(name = "role")
public class Role implements Serializable {
    private Integer roleId;         //角色id
    private String roleName;        //角色昵称
    private String roleDescribe;    //角色描述
    private Boolean roleState;      //是否启用

    public Role() {
    }
    public Role(Integer roleId, String roleName, String roleDescribe, Boolean roleState) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleDescribe = roleDescribe;
        this.roleState = roleState;
    }

    @Id
    public Integer getRoleId() {
        return roleId;
    }
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescribe() {
        return roleDescribe;
    }
    public void setRoleDescribe(String roleDescribe) {
        this.roleDescribe = roleDescribe;
    }

    public Boolean getRoleState() {
        return roleState;
    }
    public void setRoleState(Boolean roleState) {
        this.roleState = roleState;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", roleDescribe='" + roleDescribe + '\'' +
                ", roleState=" + roleState +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(roleId, role.roleId) &&
                Objects.equals(roleName, role.roleName) &&
                Objects.equals(roleDescribe, role.roleDescribe) &&
                Objects.equals(roleState, role.roleState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, roleName, roleDescribe, roleState);
    }
}

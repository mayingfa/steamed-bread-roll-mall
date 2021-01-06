package com.qiu.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/10/31 15:17
 * @description 用户表 与 角色表的关联表
 */

@Entity
@Table(name = "user_role")
public class UserRole implements Serializable {
    private Integer userId;
    private Integer roleId;

    public UserRole() {
    }
    public UserRole(Integer userId, Integer roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }
    @Id
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Id
    public Integer getRoleId() {
        return roleId;
    }
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "userId=" + userId +
                ", roleId=" + roleId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole = (UserRole) o;
        return userId.equals(userRole.userId) &&
                roleId.equals(userRole.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, roleId);
    }
}

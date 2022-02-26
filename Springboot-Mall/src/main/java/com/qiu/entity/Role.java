package com.qiu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2020/10/31 13:27
 * @description 用户权限
 */
@Data
@Entity
@Table(name = "role")
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {
    /**
     * 角色id
     */
    @Id
    private Integer roleId;

    /**
     * 角色昵称
     */
    private String roleName;

    /**
     * 角色描述
     */
    private String roleDescribe;

    /**
     * 是否启用
     */
    private Boolean roleState;
}

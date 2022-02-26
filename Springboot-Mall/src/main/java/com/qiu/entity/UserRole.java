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
 * @date 2020/10/31 15:17
 * @description 用户表 与 角色表的关联表
 */
@Data
@Entity
@Table(name = "user_role")
@NoArgsConstructor
@AllArgsConstructor
public class UserRole implements Serializable {
    @Id
    private Integer userId;
    private Integer roleId;
}

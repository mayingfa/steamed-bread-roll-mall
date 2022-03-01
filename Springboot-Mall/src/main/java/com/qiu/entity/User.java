package com.qiu.entity;

import com.qiu.constant.UserStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Captain
 */
@Data
@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    /**
     * 用户id
     */
    @Id
    private Integer userId;

    /**
     * 账号
     */
    private String accountNumber;

    /**
     * 昵称
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别
     */
    private String userSex;

    /**
     * 手机号
     */
    private String telephone;

    /**
     * 注册时间
     */
    private Date creatTime;

    /**
     * 最后登录时间
     */
    private Date loginTime;

    /**
     * 用户状态
     */
    private Boolean userState;

    /**
     * 简介
     */
    private String summary;

    /**
     * 用户地址
     */
    private String userAddress;

    /**
     * 头像链接
     */
    private String avatarUrl;

    /**
     * 背景图片链接
     */
    private String backgroundUrl;

    /**
     * 用户身份，管理员还是顾客
     */
    private UserStatusEnum status;
}

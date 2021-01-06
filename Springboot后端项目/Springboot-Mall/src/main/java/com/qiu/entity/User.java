package com.qiu.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "user")
public class User implements Serializable{
    private Integer userId;         //用户id
    private String accountNumber;   //帐号
    private String userName;        //昵称
    private String password;        //密码
    private String userSex;         //性别
    private String telephone;       //手机号
    private Date creatTime;         //注册时间
    private Date loginTime;         //最后登录时间
    private Boolean userState;      //用户状态
    private String summary;         //简介
    private String userAddress;     //用户地址
    private String avatarUrl;       //头像链接
    private String backgroundUrl;   //背景图片链接

    public User() {}

    public User(Integer userId, String accountNumber, String userName, String password, String userSex, String telephone, Date creatTime, Date loginTime, Boolean userState, String summary, String userAddress, String avatarUrl, String backgroundUrl) {
        this.userId = userId;
        this.accountNumber = accountNumber;
        this.userName = userName;
        this.password = password;
        this.userSex = userSex;
        this.telephone = telephone;
        this.creatTime = creatTime;
        this.loginTime = loginTime;
        this.userState = userState;
        this.summary = summary;
        this.userAddress = userAddress;
        this.avatarUrl = avatarUrl;
        this.backgroundUrl = backgroundUrl;
    }

    @Id
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Boolean getUserState() {
        return userState;
    }

    public void setUserState(Boolean userState) {
        this.userState = userState;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public void setBackgroundUrl(String backgroundUrl) {
        this.backgroundUrl = backgroundUrl;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", accountNumber='" + accountNumber + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", userSex='" + userSex + '\'' +
                ", telephone='" + telephone + '\'' +
                ", creatTime=" + creatTime +
                ", loginTime=" + loginTime +
                ", userState=" + userState +
                ", summary='" + summary + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", backgroundUrl='" + backgroundUrl + '\'' +
                '}';
    }
}

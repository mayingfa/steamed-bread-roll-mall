package com.qiu.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/11/25 22:02
 * @description 商品退货表 实体类
 */
@Entity
@Table(name = "return_goods")
public class ReturnGoods {
    private Integer returnId;
    private Date applyTime;
    private String userNumber;
    private String userName;
    private Double returnPrice;
    private String returnState;
    private Date dealTime;
    private String orderNo;
    private String operatorNumber;
    private String operatorName;
    private String returnReason;
    public ReturnGoods() {}
    public ReturnGoods(Integer returnId, Date applyTime, String userNumber, String userName, Double returnPrice, String returnState, Date dealTime, String orderNo, String operatorNumber, String operatorName, String returnReason) {
        this.returnId = returnId;
        this.applyTime = applyTime;
        this.userNumber = userNumber;
        this.userName = userName;
        this.returnPrice = returnPrice;
        this.returnState = returnState;
        this.dealTime = dealTime;
        this.orderNo = orderNo;
        this.operatorNumber = operatorNumber;
        this.operatorName = operatorName;
        this.returnReason = returnReason;
    }

    @Id
    public Integer getReturnId() {
        return returnId;
    }

    public void setReturnId(Integer returnId) {
        this.returnId = returnId;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Double getReturnPrice() {
        return returnPrice;
    }

    public void setReturnPrice(Double returnPrice) {
        this.returnPrice = returnPrice;
    }

    public String getReturnState() {
        return returnState;
    }

    public void setReturnState(String returnState) {
        this.returnState = returnState;
    }

    public Date getDealTime() {
        return dealTime;
    }

    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOperatorNumber() {
        return operatorNumber;
    }

    public void setOperatorNumber(String operatorNumber) {
        this.operatorNumber = operatorNumber;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getReturnReason() {
        return returnReason;
    }

    public void setReturnReason(String returnReason) {
        this.returnReason = returnReason;
    }

    @Override
    public String toString() {
        return "ReturnGoods{" +
                "returnId=" + returnId +
                ", applyTime=" + applyTime +
                ", userNumber='" + userNumber + '\'' +
                ", userName='" + userName + '\'' +
                ", returnPrice=" + returnPrice +
                ", returnState='" + returnState + '\'' +
                ", dealTime=" + dealTime +
                ", orderNo='" + orderNo + '\'' +
                ", operatorNumber='" + operatorNumber + '\'' +
                ", operatorName='" + operatorName + '\'' +
                ", returnReason='" + returnReason + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReturnGoods that = (ReturnGoods) o;
        return Objects.equals(returnId, that.returnId) &&
                Objects.equals(applyTime, that.applyTime) &&
                Objects.equals(userNumber, that.userNumber) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(returnPrice, that.returnPrice) &&
                Objects.equals(returnState, that.returnState) &&
                Objects.equals(dealTime, that.dealTime) &&
                Objects.equals(orderNo, that.orderNo) &&
                Objects.equals(operatorNumber, that.operatorNumber) &&
                Objects.equals(operatorName, that.operatorName) &&
                Objects.equals(returnReason, that.returnReason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(returnId, applyTime, userNumber, userName, returnPrice, returnState, dealTime, orderNo, operatorNumber, operatorName, returnReason);
    }
}

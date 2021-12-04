package com.qiu.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/11/18 10:12
 * @description 订单表 实体类
 */
@Entity
@Table(name = "order")
public class Order {
    private Integer orderId;    //订单id
    private String orderNo;     //订单编号
    private Date orderTime;     //下单时间
    private String productNo;   //商品编号
    private String productSpecs; //商品规格
    private String userAccount; //用户账户
    private String userName;    //用户昵称
    private String contactWay;  //联系方式
    private Double payPrice;    //支付金额
    private Integer payAmount;  //购买数量
    private String payType;     //支付方式
    private String orderFrom;   //订单来源
    private String orderState;  //订单状态
    private String acceptAddress;   //收货地址
    private Boolean returnState;

    public Order(){}
    public Order(Integer orderId, String orderNo, Date orderTime, String productNo, String productSpecs, String userAccount, String userName, String contactWay, Double payPrice, Integer payAmount, String payType, String orderFrom, String orderState, String acceptAddress, Boolean returnState) {
        this.orderId = orderId;
        this.orderNo = orderNo;
        this.orderTime = orderTime;
        this.productNo = productNo;
        this.productSpecs = productSpecs;
        this.userAccount = userAccount;
        this.userName = userName;
        this.contactWay = contactWay;
        this.payPrice = payPrice;
        this.payAmount = payAmount;
        this.payType = payType;
        this.orderFrom = orderFrom;
        this.orderState = orderState;
        this.acceptAddress = acceptAddress;
        this.returnState = returnState;
    }

    @Id
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getProductSpecs() {
        return productSpecs;
    }

    public void setProductSpecs(String productSpecs) {
        this.productSpecs = productSpecs;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContactWay() {
        return contactWay;
    }

    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
    }

    public Double getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(Double payPrice) {
        this.payPrice = payPrice;
    }

    public Integer getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Integer payAmount) {
        this.payAmount = payAmount;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getOrderFrom() {
        return orderFrom;
    }

    public void setOrderFrom(String orderFrom) {
        this.orderFrom = orderFrom;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public String getAcceptAddress() {
        return acceptAddress;
    }

    public void setAcceptAddress(String acceptAddress) {
        this.acceptAddress = acceptAddress;
    }

    public Boolean getReturnState() {
        return returnState;
    }

    public void setReturnState(Boolean returnState) {
        this.returnState = returnState;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderNo='" + orderNo + '\'' +
                ", orderTime=" + orderTime +
                ", productNo='" + productNo + '\'' +
                ", productSpecs='" + productSpecs + '\'' +
                ", userAccount='" + userAccount + '\'' +
                ", userName='" + userName + '\'' +
                ", contactWay='" + contactWay + '\'' +
                ", payPrice=" + payPrice +
                ", payAmount=" + payAmount +
                ", payType='" + payType + '\'' +
                ", orderFrom='" + orderFrom + '\'' +
                ", orderState='" + orderState + '\'' +
                ", acceptAddress='" + acceptAddress + '\'' +
                ", returnState=" + returnState +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId) &&
                Objects.equals(orderNo, order.orderNo) &&
                Objects.equals(orderTime, order.orderTime) &&
                Objects.equals(productNo, order.productNo) &&
                Objects.equals(productSpecs, order.productSpecs) &&
                Objects.equals(userAccount, order.userAccount) &&
                Objects.equals(userName, order.userName) &&
                Objects.equals(contactWay, order.contactWay) &&
                Objects.equals(payPrice, order.payPrice) &&
                Objects.equals(payAmount, order.payAmount) &&
                Objects.equals(payType, order.payType) &&
                Objects.equals(orderFrom, order.orderFrom) &&
                Objects.equals(orderState, order.orderState) &&
                Objects.equals(acceptAddress, order.acceptAddress) &&
                Objects.equals(returnState, order.returnState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, orderNo, orderTime, productNo, productSpecs, userAccount, userName, contactWay, payPrice, payAmount, payType, orderFrom, orderState, acceptAddress, returnState);
    }
}

package com.qiu.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/11/26 17:38
 * @description 物流表
 */
@Entity
@Table(name = "logistics")
public class Logistics {
    private Integer logisticId;
    private String orderNo;
    private String sender;
    private String senderTel;
    private String senderAdd;
    private String receiver;
    private String receiverTel;
    private String receiverAdd;
    private String parcelName;

    public Logistics() {}
    public Logistics(Integer logisticId, String orderNo, String sender, String senderTel, String senderAdd, String receiver, String receiverTel, String receiverAdd, String parcelName) {
        this.logisticId = logisticId;
        this.orderNo = orderNo;
        this.sender = sender;
        this.senderTel = senderTel;
        this.senderAdd = senderAdd;
        this.receiver = receiver;
        this.receiverTel = receiverTel;
        this.receiverAdd = receiverAdd;
        this.parcelName = parcelName;
    }

    @Id
    public Integer getLogisticId() {
        return logisticId;
    }

    public void setLogisticId(Integer logisticId) {
        this.logisticId = logisticId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSenderTel() {
        return senderTel;
    }

    public void setSenderTel(String senderTel) {
        this.senderTel = senderTel;
    }

    public String getSenderAdd() {
        return senderAdd;
    }

    public void setSenderAdd(String senderAdd) {
        this.senderAdd = senderAdd;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiverTel() {
        return receiverTel;
    }

    public void setReceiverTel(String receiverTel) {
        this.receiverTel = receiverTel;
    }

    public String getReceiverAdd() {
        return receiverAdd;
    }

    public void setReceiverAdd(String receiverAdd) {
        this.receiverAdd = receiverAdd;
    }

    public String getParcelName() {
        return parcelName;
    }

    public void setParcelName(String parcelName) {
        this.parcelName = parcelName;
    }

    @Override
    public String toString() {
        return "Logistics{" +
                "logisticId=" + logisticId +
                ", orderNo='" + orderNo + '\'' +
                ", sender='" + sender + '\'' +
                ", senderTel='" + senderTel + '\'' +
                ", senderAdd='" + senderAdd + '\'' +
                ", receiver='" + receiver + '\'' +
                ", receiverTel='" + receiverTel + '\'' +
                ", receiverAdd='" + receiverAdd + '\'' +
                ", parcelName='" + parcelName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Logistics logistics = (Logistics) o;
        return Objects.equals(logisticId, logistics.logisticId) &&
                Objects.equals(orderNo, logistics.orderNo) &&
                Objects.equals(sender, logistics.sender) &&
                Objects.equals(senderTel, logistics.senderTel) &&
                Objects.equals(senderAdd, logistics.senderAdd) &&
                Objects.equals(receiver, logistics.receiver) &&
                Objects.equals(receiverTel, logistics.receiverTel) &&
                Objects.equals(receiverAdd, logistics.receiverAdd) &&
                Objects.equals(parcelName, logistics.parcelName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logisticId, orderNo, sender, senderTel, senderAdd, receiver, receiverTel, receiverAdd, parcelName);
    }
}

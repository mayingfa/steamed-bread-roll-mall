package com.qiu.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/11/25 10:31
 * @description 采购表 实体类
 */
@Entity
@Table(name = "purchase")
public class Purchase {
    private Integer purchaseId;         //采购id
    private String purchaseNo;           //采购编号
    private String purchaseNumber;      //采购数量
    private Date purchaseTime;          //采购时间
    private Date receiptTime;           //收货时间
    private String productNo;           //商品编号
    private String productName;         //商品名称
    private String supplierNo;          //供应商编号
    private String supplierName;        //供应商名称
    private String accountNumber;       //后台操作员帐号
    private String userName;            //后台操作员名称
    private Boolean receiptStatus;      //是否收货

    public Purchase() {
    }
    public Purchase(Integer purchaseId, String purchaseNo, String purchaseNumber, Date purchaseTime, Date receiptTime, String productNo, String productName, String supplierNo, String supplierName, String accountNumber, String userName, Boolean receiptStatus) {
        this.purchaseId = purchaseId;
        this.purchaseNo = purchaseNo;
        this.purchaseNumber = purchaseNumber;
        this.purchaseTime = purchaseTime;
        this.receiptTime = receiptTime;
        this.productNo = productNo;
        this.productName = productName;
        this.supplierNo = supplierNo;
        this.supplierName = supplierName;
        this.accountNumber = accountNumber;
        this.userName = userName;
        this.receiptStatus = receiptStatus;
    }

    @Id
    public Integer getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Integer purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getPurchaseNo() {
        return purchaseNo;
    }

    public void setPurchaseNo(String purchaseNo) {
        this.purchaseNo = purchaseNo;
    }

    public String getPurchaseNumber() {
        return purchaseNumber;
    }

    public void setPurchaseNumber(String purchaseNumber) {
        this.purchaseNumber = purchaseNumber;
    }

    public Date getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(Date purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public Date getReceiptTime() {
        return receiptTime;
    }

    public void setReceiptTime(Date receiptTime) {
        this.receiptTime = receiptTime;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSupplierNo() {
        return supplierNo;
    }

    public void setSupplierNo(String supplierNo) {
        this.supplierNo = supplierNo;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
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

    public Boolean getReceiptStatus() {
        return receiptStatus;
    }

    public void setReceiptStatus(Boolean receiptStatus) {
        this.receiptStatus = receiptStatus;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "purchaseId=" + purchaseId +
                ", purchaseNo='" + purchaseNo + '\'' +
                ", purchaseNumber='" + purchaseNumber + '\'' +
                ", purchaseTime=" + purchaseTime +
                ", receiptTime=" + receiptTime +
                ", productNo='" + productNo + '\'' +
                ", productName='" + productName + '\'' +
                ", supplierNo='" + supplierNo + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", userName='" + userName + '\'' +
                ", receiptStatus=" + receiptStatus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return Objects.equals(purchaseId, purchase.purchaseId) &&
                Objects.equals(purchaseNo, purchase.purchaseNo) &&
                Objects.equals(purchaseNumber, purchase.purchaseNumber) &&
                Objects.equals(purchaseTime, purchase.purchaseTime) &&
                Objects.equals(receiptTime, purchase.receiptTime) &&
                Objects.equals(productNo, purchase.productNo) &&
                Objects.equals(productName, purchase.productName) &&
                Objects.equals(supplierNo, purchase.supplierNo) &&
                Objects.equals(supplierName, purchase.supplierName) &&
                Objects.equals(accountNumber, purchase.accountNumber) &&
                Objects.equals(userName, purchase.userName) &&
                Objects.equals(receiptStatus, purchase.receiptStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchaseId, purchaseNo, purchaseNumber, purchaseTime, receiptTime, productNo, productName, supplierNo, supplierName, accountNumber, userName, receiptStatus);
    }
}

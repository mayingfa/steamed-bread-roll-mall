package com.qiu.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/11/25 7:55
 * @description 供应商表 实体类
 */

@Entity
@Table(name = "supplier")
public class Supplier {
    private Integer supplierId;
    private String supplierNo;
    private String supplierName;
    private String productType;
    private String principal;   //负责人
    private String contactWay;  //联系方式
    private Boolean status;     //是否可用

    public Supplier() {
    }
    public Supplier(Integer supplierId, String supplierNo, String supplierName, String productType, String principal, String contactWay, Boolean status) {
        this.supplierId = supplierId;
        this.supplierNo = supplierNo;
        this.supplierName = supplierName;
        this.productType = productType;
        this.principal = principal;
        this.contactWay = contactWay;
        this.status = status;
    }

    @Id
    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
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

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getContactWay() {
        return contactWay;
    }

    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "supplierId=" + supplierId +
                ", supplierNo='" + supplierNo + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", productType='" + productType + '\'' +
                ", principal='" + principal + '\'' +
                ", contactWay='" + contactWay + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supplier supplier = (Supplier) o;
        return Objects.equals(supplierId, supplier.supplierId) &&
                Objects.equals(supplierNo, supplier.supplierNo) &&
                Objects.equals(supplierName, supplier.supplierName) &&
                Objects.equals(productType, supplier.productType) &&
                Objects.equals(principal, supplier.principal) &&
                Objects.equals(contactWay, supplier.contactWay) &&
                Objects.equals(status, supplier.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(supplierId, supplierNo, supplierName, productType, principal, contactWay, status);
    }
}

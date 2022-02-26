package com.qiu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2020/11/25 10:31
 * @description 采购表
 */
@Data
@Entity
@Table(name = "purchase")
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {
    /**
     * 采购id
     */
    @Id
    private Integer purchaseId;

    /**
     * 采购编号
     */
    private String purchaseNo;

    /**
     * 采购数量
     */
    private String purchaseNumber;

    /**
     * 采购时间
     */
    private Date purchaseTime;

    /**
     * 收货时间
     */
    private Date receiptTime;

    /**
     * 商品编号
     */
    private String productNo;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 供应商编号
     */
    private String supplierNo;

    /**
     * 供应商名称
     */
    private String supplierName;

    /**
     * 后台操作员账号
     */
    private String accountNumber;

    /**
     * 后台操作员名称
     */
    private String userName;

    /**
     * 是否收货
     */
    private Boolean receiptStatus;
}

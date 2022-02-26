package com.qiu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2020/11/25 7:55
 * @description 供应商表
 */
@Data
@Entity
@Table(name = "supplier")
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {
    @Id
    private Integer supplierId;
    private String supplierNo;
    private String supplierName;
    private String productType;

    /**
     * 负责人
     */
    private String principal;

    /**
     * 联系方式
     */
    private String contactWay;

    /**
     * 是否可用
     */
    private Boolean status;
}

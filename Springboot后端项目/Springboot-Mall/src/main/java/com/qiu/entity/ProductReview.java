package com.qiu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2021/1/4 10:49
 * @description 商品评价表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_review")
public class ProductReview {
    @Id
    private Integer reviewId;
    private String accountNumber;
    private String productNo;
    private String productSpecs;
    private String orderNo;
    private Date reviewTime;
    private Double starLevel;
    private String productReview;

}

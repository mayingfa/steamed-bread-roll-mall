package com.qiu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2021/1/2 9:14
 * @description 购物车实体类
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {
    @Id
    private Integer cartId;
    private String accountNumber;
    private Integer productId;
    private Integer payAmount;
    private String productSpecs;
}

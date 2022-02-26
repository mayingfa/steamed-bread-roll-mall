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
 * @date 2020/11/13 14:02
 * @description 商品表实体类
 */

@Data
@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    /**
     * 商品id
     */
    @Id
    private Integer productId;

    /**
     * 商品编号
     */
    private String productNo;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品分类
     */
    private String productType;

    /**
     * 商品描述
     */
    private String productDescribe;

    /**
     * 商品品牌
     */
    private String productBrand;

    /**
     * 商品进价
     */
    private Double inPrice;

    /**
     * 商品售价
     */
    private Double outPrice;

    /**
     * 商品库存
     */
    private Integer productStock;

    /**
     * 最低库存
     */
    private Integer lowestStock;

    /**
     * 是否缺货
     */
    private Boolean isStockOut;

    /**
     * 是否新品
     */
    private Boolean isNew;

    /**
     * 是否上架
     */
    private Boolean isSale;

    /**
     * 上架时间
     */
    private Date saleTime;

    /**
     * 图片链接
     */
    private String productUrl;
}

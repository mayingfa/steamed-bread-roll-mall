package com.qiu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2020/11/12 16:48
 * @description 商品品牌 实体
 */
@Data
@Entity
@Table(name = "product_brand")
@NoArgsConstructor
@AllArgsConstructor
public class ProductBrand implements Serializable {
    @Id
    private Integer brandId;
    private String brandName;
    private String brandDescribe;
}

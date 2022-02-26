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
 * @date 2020/11/17 16:46
 * @description 商品表与规格表的关联实体类
 */
@Data
@Entity
@Table(name = "product_specs")
@NoArgsConstructor
@AllArgsConstructor
public class ProductSpecs {
    @Id
    private Integer productId;
    private Integer specsId;
}

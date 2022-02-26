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
 * @date 2020/11/12 15:27
 * @description 商品分类 实体
 */
@Data
@Entity
@Table(name = "product_type")
@NoArgsConstructor
@AllArgsConstructor
public class ProductType implements Serializable {
    @Id
    private Integer typeId;
    private String typeName;
    private String typeDescribe;
    private String typeUrlLeft;
    private String typeUrlTop;
}

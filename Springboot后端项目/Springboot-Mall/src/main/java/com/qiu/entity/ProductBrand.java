package com.qiu.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/11/12 16:48
 * @description 商品品牌 实体
 */
@Entity
@Table(name = "product_brand")
public class ProductBrand implements Serializable{
    private int brandId;
    private String brandName;
    private String brandDescribe;

    public ProductBrand(){}
    public ProductBrand(int brandId, String brandName, String brandDescribe) {
        this.brandId = brandId;
        this.brandName = brandName;
        this.brandDescribe = brandDescribe;
    }

    @Id
    public int getBrandId() {
        return brandId;
    }
    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandDescribe() {
        return brandDescribe;
    }
    public void setBrandDescribe(String brandDescribe) {
        this.brandDescribe = brandDescribe;
    }

    @Override
    public String toString() {
        return "ProductBrand{" +
                "brandId=" + brandId +
                ", brandName='" + brandName + '\'' +
                ", brandDescribe='" + brandDescribe + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductBrand that = (ProductBrand) o;
        return brandId == that.brandId &&
                Objects.equals(brandName, that.brandName) &&
                Objects.equals(brandDescribe, that.brandDescribe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brandId, brandName, brandDescribe);
    }
}

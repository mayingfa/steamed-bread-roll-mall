package com.qiu.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/11/17 16:46
 * @description 商品表与规格表的关联实体类
 */

@Entity
@Table(name = "product_specs")
public class ProductSpecs {
    private Integer productId;
    private Integer specsId;

    public ProductSpecs(){}
    public ProductSpecs(Integer productId, Integer specsId) {
        this.productId = productId;
        this.specsId = specsId;
    }
    @Id
    public Integer getProductId() {
        return productId;
    }
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getSpecsId() {
        return specsId;
    }
    public void setSpecsId(Integer specsId) {
        this.specsId = specsId;
    }

    @Override
    public String toString() {
        return "ProductSpecs{" +
                "productId=" + productId +
                ", specsId=" + specsId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductSpecs that = (ProductSpecs) o;
        return Objects.equals(productId, that.productId) &&
                Objects.equals(specsId, that.specsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, specsId);
    }
}

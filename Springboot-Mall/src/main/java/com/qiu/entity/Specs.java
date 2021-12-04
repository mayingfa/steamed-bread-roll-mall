package com.qiu.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/11/17 15:57
 * @description 商品规格表   eg：服装 M、X、XL尺码等信息
 */

@Entity
@Table(name = "specs")
public class Specs implements Serializable {
    private Integer specsId;
    private String specsName;
    private String productType;

    public Specs(){}
    public Specs(Integer specsId, String specsName, String productType) {
        this.specsId = specsId;
        this.specsName = specsName;
        this.productType = productType;
    }
    @Id
    public Integer getSpecsId() {
        return specsId;
    }

    public void setSpecsId(Integer specsId) {
        this.specsId = specsId;
    }

    public String getSpecsName() {
        return specsName;
    }

    public void setSpecsName(String specsName) {
        this.specsName = specsName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    @Override
    public String toString() {
        return "Specs{" +
                "specsId=" + specsId +
                ", specsName='" + specsName + '\'' +
                ", productType='" + productType + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Specs specs = (Specs) o;
        return Objects.equals(specsId, specs.specsId) &&
                Objects.equals(specsName, specs.specsName) &&
                Objects.equals(productType, specs.productType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(specsId, specsName, productType);
    }
}

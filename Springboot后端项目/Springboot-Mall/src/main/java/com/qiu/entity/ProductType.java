package com.qiu.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/11/12 15:27
 * @description 商品分类 实体
 */
@Entity
@Table(name = "product_type")
public class ProductType implements Serializable {
    private int typeId;
    private String typeName;
    private String typeDescribe;
    private String typeUrlLeft;
    private String typeUrlTop;

    public ProductType() {
    }
    public ProductType(int typeId, String typeName, String typeDescribe, String typeUrlLeft, String typeUrlTop) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.typeDescribe = typeDescribe;
        this.typeUrlLeft = typeUrlLeft;
        this.typeUrlTop = typeUrlTop;
    }

    @Id
    public int getTypeId() {
        return typeId;
    }
    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeDescribe() {
        return typeDescribe;
    }
    public void setTypeDescribe(String typeDescribe) {
        this.typeDescribe = typeDescribe;
    }

    public String getTypeUrlLeft() {
        return typeUrlLeft;
    }
    public void setTypeUrlLeft(String typeUrlLeft) {
        this.typeUrlLeft = typeUrlLeft;
    }

    public String getTypeUrlTop() {
        return typeUrlTop;
    }
    public void setTypeUrlTop(String typeUrlTop) {
        this.typeUrlTop = typeUrlTop;
    }

    @Override
    public String toString() {
        return "ProductType{" +
                "typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                ", typeDescribe='" + typeDescribe + '\'' +
                ", typeUrlLeft='" + typeUrlLeft + '\'' +
                ", typeUrlTop='" + typeUrlTop + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductType that = (ProductType) o;
        return typeId == that.typeId &&
                Objects.equals(typeName, that.typeName) &&
                Objects.equals(typeDescribe, that.typeDescribe) &&
                Objects.equals(typeUrlLeft, that.typeUrlLeft) &&
                Objects.equals(typeUrlTop, that.typeUrlTop);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeId, typeName, typeDescribe, typeUrlLeft, typeUrlTop);
    }
}

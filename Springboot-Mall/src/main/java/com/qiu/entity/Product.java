package com.qiu.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/11/13 14:02
 * @description 商品表实体类
 */

@Entity
@Table(name = "product")
public class Product {
    private Integer productId;  //商品id
    private String productNo;   //商品编号
    private String productName; //商品名称
    private String productType; //商品分类
    private String productDescribe;     //商品描述
    private String productBrand;    //商品品牌
    private Double inPrice;     //商品进价
    private Double outPrice;    //商品售价
    private Integer productStock;   //商品库存
    private Integer lowestStock;   //最低库存
    private Boolean isStockOut; //是否缺货
    private Boolean isNew;  //是否新品
    private Boolean isSale; //是否上架
    private Date saleTime;  //上架时间
    private String productUrl;  //图片链接

    public Product() {

    }
    public Product(Integer productId, String productNo, String productName, String productType, String productDescribe, String productBrand, Double inPrice, Double outPrice, Integer productStock, Integer lowestStock, Boolean isStockOut, Boolean isNew, Boolean isSale, Date saleTime, String productUrl) {
        this.productId = productId;
        this.productNo = productNo;
        this.productName = productName;
        this.productType = productType;
        this.productDescribe = productDescribe;
        this.productBrand = productBrand;
        this.inPrice = inPrice;
        this.outPrice = outPrice;
        this.productStock = productStock;
        this.lowestStock = lowestStock;
        this.isStockOut = isStockOut;
        this.isNew = isNew;
        this.isSale = isSale;
        this.saleTime = saleTime;
        this.productUrl = productUrl;
    }

    @Id
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductDescribe() {
        return productDescribe;
    }

    public void setProductDescribe(String productDescribe) {
        this.productDescribe = productDescribe;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public Double getInPrice() {
        return inPrice;
    }

    public void setInPrice(Double inPrice) {
        this.inPrice = inPrice;
    }

    public Double getOutPrice() {
        return outPrice;
    }

    public void setOutPrice(Double outPrice) {
        this.outPrice = outPrice;
    }

    public Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

    public Integer getLowestStock() {
        return lowestStock;
    }

    public void setLowestStock(Integer lowestStock) {
        this.lowestStock = lowestStock;
    }

    public Boolean getIsStockOut() {
        return isStockOut;
    }

    public void setIsStockOut(Boolean stockOut) {
        isStockOut = stockOut;
    }

    public Boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(Boolean aNew) {
        isNew = aNew;
    }

    public Boolean getIsSale() {
        return isSale;
    }

    public void setIsSale(Boolean sale) {
        isSale = sale;
    }

    public Date getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(Date saleTime) {
        this.saleTime = saleTime;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productNo='" + productNo + '\'' +
                ", productName='" + productName + '\'' +
                ", productType='" + productType + '\'' +
                ", productDescribe='" + productDescribe + '\'' +
                ", productBrand='" + productBrand + '\'' +
                ", inPrice=" + inPrice +
                ", outPrice=" + outPrice +
                ", productStock=" + productStock +
                ", lowestStock=" + lowestStock +
                ", isStockOut=" + isStockOut +
                ", isNew=" + isNew +
                ", isSale=" + isSale +
                ", saleTime=" + saleTime +
                ", productUrl='" + productUrl + '\'' +
                '}';
    }
}

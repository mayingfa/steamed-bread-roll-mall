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
 * @date 2020/12/31 15:58
 * @description 商品轮播图表
 */
@Data
@Entity
@Table(name = "banner")
@NoArgsConstructor
@AllArgsConstructor
public class Banner {
    @Id
    private Integer bannerId;
    private String productName;
    private String productUrl;
    private String bannerUrl;
}

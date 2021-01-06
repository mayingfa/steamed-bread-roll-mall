package com.qiu.service;

import com.qiu.entity.ProductReview;
import com.qiu.entity.ShoppingCart;

import java.util.List;
import java.util.Map;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2021/1/4 11:09
 * @description 商品评价 表
 */
public interface ProductReviewService {
    //通过ID查询
    ProductReview selectById(Integer reviewId);
    //查询所有
    List<Map<String,Object>> selectAll(String productNo);

    //插入一条数据
    Boolean insertData(ProductReview productReview);
    //通过ID更新
    Boolean updateById(ProductReview productReview);
    //通过ID删除
    Boolean deleteById(Integer reviewId);
}

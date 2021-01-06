package com.qiu.dao;

import com.qiu.entity.ProductReview;

import java.util.List;
import java.util.Map;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2021/1/4 10:52
 * @description 商品评价
 */
public interface ProductReviewDao extends BaseDao<ProductReview> {
    //查询所有
    List<Map<String,Object>> selectAll(String productNo);

}

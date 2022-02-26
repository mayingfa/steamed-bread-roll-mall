package com.qiu.dao;

import com.qiu.entity.ProductReview;

import java.util.List;
import java.util.Map;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2021/1/4 10:52
 * @description 商品评价
 */
public interface ProductReviewDao extends BaseDao<ProductReview> {
    /**
     * 查询所有
     *
     * @param productNo 商品编号
     * @return 商品评论信息
     */
    List<Map<String, Object>> selectAll(String productNo);

}

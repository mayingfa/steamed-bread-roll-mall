package com.qiu.service;

import com.qiu.entity.ProductReview;

import java.util.List;
import java.util.Map;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2021/1/4 11:09
 * @description 商品评价业务逻辑
 */
public interface ProductReviewService {
    /**
     * 根据评价ID查询
     *
     * @param reviewId 评价ID
     * @return 商品评价
     */
    ProductReview selectById(Integer reviewId);

    /**
     * 查询商品评价信息
     *
     * @param productNo 商品编号
     * @return 商品评价
     */
    List<Map<String, Object>> selectAll(String productNo);

    /**
     * 新增商品评价
     *
     * @param productReview 商品评价
     * @return 是否添加成功
     */
    Boolean insertData(ProductReview productReview);

    /**
     * 更新商品评价
     *
     * @param productReview 商品评价
     * @return 是否更新成功
     */
    Boolean updateById(ProductReview productReview);

    /**
     * 删除商品评价
     *
     * @param reviewId 评价编号
     * @return 是否删除成功
     */
    Boolean deleteById(Integer reviewId);
}

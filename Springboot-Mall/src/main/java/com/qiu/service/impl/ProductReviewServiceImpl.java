package com.qiu.service.impl;

import com.qiu.dao.ProductReviewDao;
import com.qiu.entity.ProductReview;
import com.qiu.service.ProductReviewService;
import com.qiu.util.general.WordFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2021/1/4 11:09
 * @description 商品评价业务逻辑
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class ProductReviewServiceImpl implements ProductReviewService {
    @Autowired
    private ProductReviewDao productReviewDao;

    @Override
    public ProductReview selectById(Integer reviewId) {
        return productReviewDao.selectById(reviewId);
    }

    @Override
    public List<Map<String, Object>> selectAll(String productNo) {
        return productReviewDao.selectAll(productNo);
    }

    @Override
    public Boolean insertData(ProductReview productReview) {
        productReview.setReviewTime(new Date());
        String review = WordFilter.replaceWords(productReview.getProductReview());
        productReview.setProductReview(review);
        return productReviewDao.insertData(productReview);
    }

    @Override
    public Boolean updateById(ProductReview productReview) {
        return productReviewDao.updateById(productReview);
    }

    @Override
    public Boolean deleteById(Integer reviewId) {
        return productReviewDao.deleteById(reviewId);
    }


}

package com.qiu.service;

import com.qiu.dao.ProductReviewDao;
import com.qiu.entity.ProductReview;
import com.qiu.util.general.WordFilter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2021/1/4 11:09
 * @description 商品评价 service 实现类
 */

@Service
@Transactional
public class ProductReviewServiceImpl implements ProductReviewService {
    private final ProductReviewDao productReviewDao;
    public ProductReviewServiceImpl(ProductReviewDao productReviewDao) {
        this.productReviewDao = productReviewDao;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public ProductReview selectById(Integer reviewId) {
        return productReviewDao.selectById(reviewId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
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

package com.qiu.controller;

import com.qiu.entity.Order;
import com.qiu.entity.ProductReview;
import com.qiu.service.OrderService;
import com.qiu.service.ProductReviewService;
import com.qiu.util.general.CommonResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2021/1/4 11:12
 * @description 商品评价 业务类
 */
@RestController
@CrossOrigin
public class ProductReviewController {
    final ProductReviewService productReviewService;
    final OrderService orderService;

    public ProductReviewController(ProductReviewService productReviewService, OrderService orderService) {
        this.productReviewService = productReviewService;
        this.orderService = orderService;
    }


    /**
     * 添加商品评论
     *
     * @param productReview 商品评论
     */
    @RequestMapping(value = "/productReview/add")
    public CommonResult addProductReview(ProductReview productReview) {
        if (productReviewService.insertData(productReview)) {
            Integer orderId = orderService.selectIdByKey(productReview.getOrderNo());
            Order order = new Order();
            order.setOrderId(orderId);
            order.setOrderState("已评价");
            orderService.updateById(order);
            return CommonResult.success("商品评论添加成功", productReview);
        } else {
            return CommonResult.error("商品评论添加失败");
        }
    }

    @RequestMapping(value = "/productReview/update")
    public CommonResult updateProductReview(ProductReview productReview) {
        if (productReviewService.updateById(productReview)) {
            return CommonResult.success("商品评论修改成功", productReview);
        } else {
            return CommonResult.error("商品评论修改失败");
        }
    }

    @RequestMapping(value = "/productReview/deleteById")
    public CommonResult deleteProductReview(Integer reviewId) {
        if (productReviewService.deleteById(reviewId)) {
            return CommonResult.success("商品评论删除成功", "reviewId: " + reviewId);
        } else {
            return CommonResult.error("商品评论删除失败");
        }
    }


    @RequestMapping(value = "/productReview/findAll")
    public CommonResult findAllProductReview(String productNo) {
        List<Map<String, Object>> productReviewInfo = productReviewService.selectAll(productNo);
        if (productReviewInfo != null) {
            return CommonResult.success("商品评论查询成功", productReviewInfo);
        } else {
            return CommonResult.error("商品评论查询失败");
        }
    }

    @RequestMapping(value = "/productReview/findById")
    public CommonResult findById(Integer reviewId) {
        ProductReview productReview = productReviewService.selectById(reviewId);
        if (productReview != null) {
            return CommonResult.success("商品评论查询成功", productReview);
        } else {
            return CommonResult.error("商品评论查询失败");
        }
    }

}

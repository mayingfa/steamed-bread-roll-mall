package com.qiu.controller;

import com.qiu.service.OrderService;
import com.qiu.service.ProductService;
import com.qiu.service.ReturnGoodsService;
import com.qiu.util.general.CommonResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/12/3 16:21
 * @description 数据分析，商品数量，订单数量等分析业务
 */
@CrossOrigin
@RestController
public class OverViewController {
    final OrderService orderService;
    final ProductService productService;
    final ReturnGoodsService returnGoodsService;

    public OverViewController(OrderService orderService, ProductService productService, ReturnGoodsService returnGoodsService) {
        this.orderService = orderService;
        this.productService = productService;
        this.returnGoodsService = returnGoodsService;
    }

    @RequestMapping(value = "/view/dataInfo")
    public CommonResult dataInfo() {
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> productMap = productService.productOverview();
        Map<String, Object> orderMap = orderService.orderOverview();
        Map<String, Object> returnGoodsMap = returnGoodsService.returnGoodsOverview();
        if (productMap != null) {
            resultMap.putAll(productMap);
        }
        if (orderMap != null) {
            resultMap.putAll(orderMap);
        }
        if (returnGoodsMap != null) {
            resultMap.putAll(returnGoodsMap);
        }
        if (resultMap.size() != 0) {
            return CommonResult.success("查询成功", resultMap);
        } else {
            return CommonResult.error("查询失败");
        }
    }

    @RequestMapping(value = "/view/orderChartDATE")
    public CommonResult orderChartDATE(String startTime, String endTime) {
        Map<String, Object> data = new HashMap<>();
        List<Map<String, Object>> lineData = orderService.selectChartDATE(startTime, endTime);
        List<Map<String, Object>> ringData = orderService.selectProductTypeChart(startTime, endTime);
        Map<String, Object> countData = orderService.selectCountAndAmount(startTime, endTime);
        if (!lineData.isEmpty()) {
            data.put("lineData", lineData);
        }
        if (!ringData.isEmpty()) {
            data.put("ringData", ringData);
        }
        if (!countData.isEmpty()) {
            data.put("countData", countData);
        }
        if (!data.isEmpty()) {
            return CommonResult.success("查询成功", data);
        } else {
            return CommonResult.error("查询失败");
        }
    }

}

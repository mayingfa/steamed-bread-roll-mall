package com.qiu.controller;

import com.qiu.service.OrderService;
import com.qiu.service.ProductService;
import com.qiu.service.ReturnGoodsService;
import com.qiu.util.general.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2020/12/3 16:21
 * @description 数据分析，商品数量，订单数量等分析业务
 */
@CrossOrigin
@RestController
public class OverViewController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ReturnGoodsService returnGoodsService;

    @RequestMapping(value = "/view/dataInfo")
    public CommonResult dataInfo() {
        Map<String, Object> resultMap = new HashMap<>(3);
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
        return CommonResult.success("查询成功", resultMap);
    }

    @RequestMapping(value = "/view/order-chart-date")
    public CommonResult orderChartDate(String startTime, String endTime) {
        Map<String, Object> data = new HashMap<>(3);
        List<Map<String, Object>> lineData = orderService.selectChartDate(startTime, endTime);
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
        return CommonResult.success("查询成功", data);
    }
}

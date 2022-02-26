package com.qiu.controller;

import com.alibaba.fastjson.JSON;
import com.qiu.entity.Logistics;
import com.qiu.entity.Order;
import com.qiu.entity.Product;
import com.qiu.service.LogisticsService;
import com.qiu.service.OrderService;
import com.qiu.service.ProductService;
import com.qiu.util.general.CommonResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2020/10/28 18:11
 * @description 订单相关业务
 */

@RestController
@CrossOrigin
public class OrderController {
    private static final String VIP = "Vip";
    private static final String COLLECT_GOODS_STATE = "已收货";

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private LogisticsService logisticsService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @RequestMapping(value = "/order/findById")
    public CommonResult findOrderById(Integer orderId) {
        Order order = orderService.selectById(orderId);
        if (orderId != null) {
            return CommonResult.success("订单信息查询成功", order);
        }
        return CommonResult.error("订单信息查询失败");
    }

    @RequestMapping(value = "/order/findOrderInfo")
    public CommonResult findOrderInfo(String userAccount) {
        List<Map<String, Object>> orderMap = orderService.selectAllOrder(userAccount);
        if (orderMap != null) {
            return CommonResult.success("订单信息查询成功", orderMap);
        }
        return CommonResult.error("订单信息查询失败");
    }

    @RequestMapping(value = "/order/findAll")
    public CommonResult findAllOrder() {
        List<Order> orders = orderService.selectAll();
        if (orders != null) {
            return CommonResult.success("订单信息查询成功", orders);
        }
        return CommonResult.error("订单信息查询失败");
    }

    @RequestMapping(value = "/order/findCount")
    public CommonResult findCount() {
        Integer count = orderService.selectCount();
        if (count != null) {
            return CommonResult.success("订单数量查询成功", count);
        }
        return CommonResult.error("订单数量查询失败");
    }


    @RequestMapping(value = "/order/add")
    public CommonResult addOrder(Order order) {
        if (order != null) {
            if (order.getProductNo().contains(VIP)) {
                return handleMemberOrders(order);
            }
            return handleMerchandiseOrders(order);
        } else {
            return CommonResult.error("订单数据不完整");
        }
    }

    @RequestMapping(value = "/order/cartOrder")
    public CommonResult cartOrder(String orderNo, String ordersInfo, String cartIds) {
        List<String> cartIdList = JSON.parseArray(cartIds, String.class);
        List<Order> orders = JSON.parseArray(ordersInfo, Order.class);
        if (orders != null) {
            ArrayList<String> orderInfo = new ArrayList<>();
            ArrayList<String> productInfo = new ArrayList<>();
            for (Order order : orders) {
                Product product = productService.selectByKey(order.getProductNo());
                Integer productStock = product.getProductStock();
                Integer payAmount = order.getPayAmount();
                if (productStock >= payAmount) {
                    Product newProduct = new Product();
                    newProduct.setProductId(product.getProductId());
                    int newStock = productStock - payAmount;
                    newProduct.setProductStock(newStock);
                    newProduct.setIsStockOut(newStock < product.getLowestStock());
                    // 如果库存小于等于0，自动下架
                    newProduct.setIsSale(newStock > 0);
                    if (productService.updateById(newProduct) && orderService.insertData(order)) {
                        orderInfo.add(order.getOrderNo());
                        productInfo.add(order.getProductNo());
                    }
                }
            }
            if (!orderInfo.isEmpty()) {
                String cartIdsInfo = StringUtils.join(cartIdList.toArray(), ",");
                String orderNoInfo = StringUtils.join(orderInfo, ",");
                String productNoInfo = StringUtils.join(productInfo, ",");
                redisTemplate.opsForValue().set(orderNo, orderNoInfo, 24, TimeUnit.HOURS);
                redisTemplate.opsForValue().set("cartId" + orderNo, cartIdsInfo, 24, TimeUnit.HOURS);
                return CommonResult.success("创建订单成功", productNoInfo);
            }
            return CommonResult.error("创建订单失败，请查看商品库存是否满足购买数量");
        } else {
            return CommonResult.error("订单数据不完整");
        }
    }

    @RequestMapping(value = "/order/update")
    public CommonResult updateOrder(Order order) {
        if (orderService.updateById(order)) {
            return CommonResult.success("修改订单成功", order);
        }
        return CommonResult.error("修改订单失败");
    }

    @RequestMapping(value = "/order/delete")
    public CommonResult deleteOrder(Integer orderId) {
        if (orderService.deleteById(orderId)) {
            return CommonResult.success("删除订单成功", "订单id：" + orderId);
        }
        return CommonResult.error("删除订单失败");
    }

    @RequestMapping(value = "/order/receipt")
    public CommonResult updateOrder(Integer orderId) {
        Order order = new Order();
        order.setOrderId(orderId);
        order.setOrderState(COLLECT_GOODS_STATE);
        if (orderService.updateById(order)) {
            return CommonResult.success("商品收货成功", order);
        }
        return CommonResult.error("商品收货失败");
    }

    @RequestMapping(value = "/orderDetail/orderInfo")
    public CommonResult orderInfo(String orderNo) {
        ArrayList<Object> resultList = new ArrayList<>();
        Order order = orderService.selectByKey(orderNo);
        Logistics logistics = logisticsService.selectOrderNo(orderNo);
        if (order != null) {
            resultList.add(order);
        }
        if (logistics != null) {
            resultList.add(logistics);
        }
        return CommonResult.success("订单详情查询成功", resultList);
    }

    /**
     * 处理会员订单
     *
     * @param order 订单信息
     */
    private CommonResult handleMemberOrders(Order order) {
        if (orderService.insertData(order)) {
            return CommonResult.success("创建订单成功", order);
        } else {
            return CommonResult.error("创建订单失败");
        }
    }

    /**
     * 处理商品订单
     *
     * @param order 订单信息
     */
    private CommonResult handleMerchandiseOrders(Order order) {
        Product product = productService.selectByKey(order.getProductNo());
        Integer productStock = product.getProductStock();
        Integer payAmount = order.getPayAmount();
        boolean isOk = productStock >= payAmount;
        if (isOk) {
            Product newProduct = new Product();
            newProduct.setProductId(product.getProductId());
            int newStock = productStock - payAmount;
            newProduct.setProductStock(newStock);
            newProduct.setIsStockOut(newStock < product.getLowestStock());
            // 如果库存小于等于0，自动下架
            newProduct.setIsSale(newStock > 0);
            if (productService.updateById(newProduct)) {
                if (orderService.insertData(order)) {
                    redisTemplate.opsForValue().set(order.getOrderNo(), order.getOrderNo(), 24, TimeUnit.HOURS);
                    return CommonResult.success("创建订单成功", order);
                } else {
                    return CommonResult.error("创建订单失败");
                }
            } else {
                return CommonResult.error("创建订单失败");
            }
        } else {
            return CommonResult.error("商品库存不足");
        }
    }
}

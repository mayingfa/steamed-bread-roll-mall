package com.qiu.controller;

import com.alipay.api.AlipayApiException;
import com.qiu.entity.Order;
import com.qiu.service.AlipayService;
import com.qiu.service.OrderService;
import com.qiu.service.ShoppingCartService;
import com.qiu.util.general.CommonResult;
import com.qiu.util.general.PropertiesUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * @author Captain
 */
@Slf4j
@Controller
@CrossOrigin
@RequestMapping("/alipay")
public class AlipayController {
    private static final String VIP = "Vip";
    private static final String OPEN_SUCCESS = "开通成功";
    private static final String WAIT_SEND = "待发货";
    private static final String CART_ID = "cartId";
    private static final String OUT_TRADE_NO = "out_trade_no";
    private static final String TRADE_SUCCESS = "TRADE_SUCCESS";
    private static final String TRADE_STATUS = "trade_status";
    private static final String TRADE_TIME = "gmt_payment";
    private static final String TRADE_NAME = "subject";
    private static final String TRADE_AMOUNT = "buyer_pay_amount";

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private AlipayService alipayService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private final String hostAddress;

    public AlipayController() {
        this.hostAddress = PropertiesUtil.getCallback();
    }

    @RequestMapping("/")
    public String index() {
        return "index.html";
    }

    @ResponseBody
    @PostMapping(value = "/create", produces = "text/html;charset=utf-8")
    public String create(@RequestParam("orderNo") String orderNo,
                         @RequestParam("orderName") String orderName,
                         @RequestParam("payPrice") String payPrice) {
        return alipayService.create(orderNo, orderName, payPrice);
    }

    @ResponseBody
    @PostMapping(value = "/vip", produces = "text/html;charset=utf-8")
    public String create(@RequestParam("orderNo") String orderNo,
                         @RequestParam("orderName") String orderName,
                         @RequestParam("payPrice") String payPrice,
                         @RequestParam("serialNumber") String serialNumber) {
        //开通vip的序列号暂时存入redis中
        redisTemplate.opsForValue().set(orderNo, serialNumber, 2, TimeUnit.HOURS);
        return alipayService.create(orderNo, orderName, payPrice);
    }


    @ResponseBody
    @RequestMapping(value = "/refund")
    public CommonResult refund(@RequestParam("orderNo") String orderNo, @RequestParam("payPrice") String payPrice) {
        try {
            alipayService.refund(orderNo, payPrice);
        } catch (AlipayApiException e) {
            log.error("【支付宝支付】退款失败", e);
            return CommonResult.success("退款失败");
        }
        return CommonResult.success("退款成功");
    }

    @GetMapping(value = "/success")
    public void success(@RequestParam Map<String, String> map, HttpServletResponse response) {
        try {
            String tradeNo = map.get(OUT_TRADE_NO);
            if (tradeNo.contains(VIP)) {
                openMember(response, tradeNo);
            } else {
                updateProductStatus(response, tradeNo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping(value = "/notify")
    public void payNotify(@RequestParam Map<String, String> map) {
        if (TRADE_SUCCESS.equals(map.get(TRADE_STATUS))) {
            String payTime = map.get(TRADE_TIME);
            String tradeNo = map.get(OUT_TRADE_NO);
            String tradeName = map.get(TRADE_NAME);
            String payAmount = map.get(TRADE_AMOUNT);
            log.info("[支付成功] {交易时间：{},订单号：{},订单名称：{},交易金额：{}}", payTime, tradeNo, tradeName, payAmount);
        }
    }

    /**
     * 支付成功，更新商品状态为待发货
     *
     * @param response HTTP响应
     * @param tradeNo  订单编号
     * @throws IOException IO异常信息
     */
    private void updateProductStatus(HttpServletResponse response, String tradeNo) throws IOException {
        String tradeNos = redisTemplate.opsForValue().get(tradeNo);
        if (StringUtils.isNotBlank(tradeNos)) {
            redisTemplate.delete(tradeNo);
            String[] ordersNo = tradeNos.split(",");
            for (String orderNo : ordersNo) {
                Integer orderId = orderService.selectIdByKey(orderNo);
                Order order = new Order();
                order.setOrderId(orderId);
                order.setOrderState(WAIT_SEND);
                orderService.updateById(order);
            }
        }
        String cartIdInfos = redisTemplate.opsForValue().get(CART_ID + tradeNo);
        if (StringUtils.isNotBlank(cartIdInfos)) {
            redisTemplate.delete(cartIdInfos);
            String[] cartIds = cartIdInfos.split(",");
            for (String cartId : cartIds) {
                if (StringUtils.isNotBlank(cartId)) {
                    shoppingCartService.deleteById(Integer.parseInt(cartId));
                }
            }
        }
        response.sendRedirect("http://" + hostAddress + "/#/myOrder");
    }

    /**
     * 开通会员
     *
     * @param response HTTP响应
     * @param tradeNo  订单编号
     * @throws IOException IO异常信息
     */
    private void openMember(HttpServletResponse response, String tradeNo) throws IOException {
        Integer orderId = orderService.selectIdByKey(tradeNo);
        Order order = new Order();
        order.setOrderId(orderId);
        order.setOrderState(OPEN_SUCCESS);
        String serialNumber = redisTemplate.opsForValue().get(tradeNo);
        if (serialNumber != null) {
            response.sendRedirect("http://" + hostAddress + "/#/personalCenter?serialNumber=" + serialNumber);
            redisTemplate.delete(tradeNo);
        } else {
            response.sendRedirect("http://" + hostAddress + "/#/personalCenter?serialNumber=" + "ERROR");
        }
        orderService.updateById(order);
    }
}

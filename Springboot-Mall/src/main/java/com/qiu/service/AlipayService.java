package com.qiu.service;


import com.alipay.api.AlipayApiException;

/**
 * @author Captain
 */
public interface AlipayService {
    /**
     * 创建支付订单
     *
     * @param orderId   订单编号
     * @param orderName 订单名称
     * @param payPrice  支付金额
     * @return 支付表单
     */
    String create(String orderId, String orderName, String payPrice);

    /**
     * 订单退款
     *
     * @param orderId  订单编号
     * @param payPrice 退款金额
     * @throws AlipayApiException 支付异常
     */
    void refund(String orderId, String payPrice) throws AlipayApiException;
}
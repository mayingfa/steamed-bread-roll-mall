package com.qiu.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.qiu.config.AlipayConfig;
import com.qiu.service.AlipayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Captain
 * @description 支付宝业务逻辑
 */
@Slf4j
@Service
public class AlipayServiceImpl implements AlipayService {
    private static final String PRODUCTCODE = "QUICK_WAP_WAY";

    @Override
    public String create(String orderId, String orderName, String payPrice) {
        AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID,
                AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT,
                AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY,
                AlipayConfig.SIGNTYPE);
        AlipayTradeWapPayRequest payRequest = new AlipayTradeWapPayRequest();
        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
        model.setOutTradeNo(orderId);
        model.setSubject(orderName);
        model.setTotalAmount(payPrice);
        model.setProductCode(PRODUCTCODE);
        payRequest.setBizModel(model);
        payRequest.setReturnUrl(AlipayConfig.RETURN_URL);
        payRequest.setNotifyUrl(AlipayConfig.NOTIFY_URL);
        try {
            return client.pageExecute(payRequest).getBody();
        } catch (AlipayApiException e) {
            log.error("[支付宝] 支付失败", e);
        }
        return null;
    }


    @Override
    public void refund(String orderId, String payPrice) throws AlipayApiException {
        AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID,
                AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET,
                AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);
        AlipayTradeRefundRequest payRequest = new AlipayTradeRefundRequest();
        AlipayTradeRefundModel model = new AlipayTradeRefundModel();
        model.setOutTradeNo(orderId);
        model.setRefundAmount(payPrice);
        payRequest.setBizModel(model);
        client.execute(payRequest).getBody();
    }
}
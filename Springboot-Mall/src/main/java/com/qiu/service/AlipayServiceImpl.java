package com.qiu.service;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.qiu.config.AlipayConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AlipayServiceImpl implements AlipayService {
    //和支付宝签约的产品码 固定值
    private static final String PRODUCTCODE = "QUICK_WAP_WAY";
    //支付成功标识(可退款的签约是TRADE_SUCCESS，不可退款的签约是TRADE_FINISHED)
    //private static final String TRADE_SUCCESS = "TRADE_SUCCESS";

    @Override
    public String create(String orderId,String orderName,String payPrice) {
        AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY,AlipayConfig.SIGNTYPE);
        AlipayTradeWapPayRequest alipay_request=new AlipayTradeWapPayRequest();
        // 封装请求支付信息
        AlipayTradeWapPayModel model= new AlipayTradeWapPayModel();
        //订单编号，不可重复
        model.setOutTradeNo(orderId);
        //订单名称
        model.setSubject(orderName);
        //订单金额
        model.setTotalAmount(payPrice);
        //产品码
        model.setProductCode(PRODUCTCODE);
        alipay_request.setBizModel(model);
        //支付成功后跳转的地址
        alipay_request.setReturnUrl(AlipayConfig.return_url);
        //异步通知地址
        alipay_request.setNotifyUrl(AlipayConfig.notify_url);
        // form表单生产
        String result = "";
        try {
            // 调用SDK生成表单
            result = client.pageExecute(alipay_request).getBody();
        } catch (AlipayApiException e) {
            log.info("【支付宝支付】支付失败 error={}", e.toString());
        }
        return result;
    }

 
    @Override
    public void refund(String orderId, String payPrice) throws AlipayApiException {
        AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ZHIFUBAO_PUBLIC_KEY,AlipayConfig.SIGNTYPE);
        AlipayTradeRefundRequest alipay_request = new AlipayTradeRefundRequest();
        AlipayTradeRefundModel model=new AlipayTradeRefundModel();
        //退款的订单Id，也可以设置流水号
        model.setOutTradeNo(orderId);
        //退款金额
        model.setRefundAmount(payPrice);
        alipay_request.setBizModel(model);
        String alipay_response = client.execute(alipay_request).getBody();
        log.info("[支付退款] response={}", alipay_response);
    }
}
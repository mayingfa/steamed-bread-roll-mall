package com.qiu.controller;
import com.alipay.api.AlipayApiException;
import com.qiu.entity.Order;
import com.qiu.service.AlipayService;
import com.qiu.service.OrderService;
import com.qiu.util.general.CommonResult;
import com.qiu.util.general.PropertiesUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@CrossOrigin
@RequestMapping("/alipay")
@Slf4j
@Controller
public class AlipayController {
    private final AlipayService alipayService;
    private final OrderService orderService;
    private final RedisTemplate<String,String> redisTemplate;
    private final String hostAddress;
    public AlipayController(AlipayService alipayService,OrderService orderService,RedisTemplate<String,String> redisTemplate) {
        this.alipayService = alipayService;
        this.orderService = orderService;
        this.redisTemplate = redisTemplate;
        this.hostAddress= PropertiesUtil.getServerHost() +":"+PropertiesUtil.getPort();
    }

    @RequestMapping("/")
    private String index(){
        return "index.html";
    }

    /*
    https://qiustudy.utools.club/alipay/create?orderNo=10060&orderName=花卷商城-华为手机支付订单&payPrice=4000
    */
    @ResponseBody
    @PostMapping(value = "/create", produces = "text/html;charset=utf-8")
    public String create(@RequestParam("orderNo") String orderNo,
                         @RequestParam("orderName") String orderName,
                         @RequestParam("payPrice") String payPrice) {
        //发起支付
        return alipayService.create(orderNo, orderName, payPrice);
    }

    @ResponseBody
    @PostMapping(value = "/vip", produces = "text/html;charset=utf-8")
    public String create(@RequestParam("orderNo") String orderNo,
                         @RequestParam("orderName") String orderName,
                         @RequestParam("payPrice") String payPrice,
                         @RequestParam("serialNumber") String serialNumber) {

        //开通vip的序列号暂时存入redis中
        redisTemplate.opsForValue().set(orderNo,serialNumber,2, TimeUnit.HOURS);
        //发起支付
        return alipayService.create(orderNo, orderName, payPrice);
    }


    /*
    https://qiustudy.utools.club/alipay/refund?orderNo=10060&payPrice=4000
     */
    @ResponseBody
    @RequestMapping(value = "/refund")
    public CommonResult refund(@RequestParam("orderNo") String orderNo,
                               @RequestParam("payPrice") String payPrice) {
        //发起支付
        try {
            alipayService.refund(orderNo,payPrice);
        } catch (AlipayApiException e) {
            log.info("【支付宝支付】退款失败 error={}", e.toString());
        }
        return CommonResult.success("退款成功");
    }

    @GetMapping(value = "/paySuccess")
    @ResponseBody
    public void success(@RequestParam Map<String, String> map,HttpServletResponse response) {
        try{
            String tradeNo =map.get("out_trade_no");
            if(tradeNo.contains("Vip")){
                Integer orderId = orderService.selectIdByKey(tradeNo);
                Order order = new Order();
                order.setOrderId(orderId);
                order.setOrderState("开通成功");
                String serialNumber =  redisTemplate.opsForValue().get(tradeNo);
                if(serialNumber !=null){
                    response.sendRedirect("http://"+hostAddress+"/?#/personalCenter?serialNumber="+serialNumber);
                    redisTemplate.delete(tradeNo);
                }else{
                    response.sendRedirect("http://"+hostAddress+"/?#/personalCenter?serialNumber="+"ERROR");
                }
                orderService.updateById(order);
            }else{
                String Str =  redisTemplate.opsForValue().get(tradeNo);
                redisTemplate.delete(tradeNo);
                assert Str != null;
                String[] ordersNo = Str.split(",");
                System.out.println(Arrays.toString(ordersNo));
                for (String orderNo : ordersNo) {
                    Integer orderId = orderService.selectIdByKey(orderNo);
                    Order order = new Order();
                    order.setOrderId(orderId);
                    order.setOrderState("待发货");
                    orderService.updateById(order);
                }
                response.sendRedirect("http://"+hostAddress+"/?#/myOrder");
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }


    @ResponseBody
    @PostMapping(value = "/payNotify")
    public void payNotify(@RequestParam Map<String, String> map) {
        String tradeStatus = map.get("trade_status");
        if(tradeStatus.equals("TRADE_SUCCESS")){
            String payTime = map.get("gmt_payment");
            String tradeNo =map.get("out_trade_no");
            String tradeName = map.get("subject");
            String payAmount = map.get("buyer_pay_amount");
            log.info("[支付成功] {交易时间：{},订单号：{},订单名称：{},交易金额：{}}",payTime,tradeNo,tradeName,payAmount);
        }
    }
}

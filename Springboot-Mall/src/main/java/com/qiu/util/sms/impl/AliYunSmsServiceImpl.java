package com.qiu.util.sms.impl;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.qiu.util.sms.AliYunSmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Captain
 */
@Slf4j
@Service
public class AliYunSmsServiceImpl implements AliYunSmsService {

    /**
     * accessKeyID
     */
    @Value("${sms.aliyun.accessKeyID}")
    private String accessKeyId;

    /**
     * accessKeySecret
     */
    @Value("${sms.aliyun.accessKeySecret}")
    private String accessKeySecret;

    /**
     * 短信api的请求地址  固定
     */
    @Value("${sms.aliyun.domain}")
    private String domain;

    /**
     * 指定地域名称 短信API cn-hangzhou 固定
     */
    @Value("${sms.aliyun.regionId}")
    private String regionId;

    /**
     * 短信签名
     */
    @Value("${sms.aliyun.signName}")
    private String signName;

    /**
     * 短信模板
     */
    @Value("${sms.aliyun.templateCode}")
    private String templateCode;

    @Override
    public boolean sendSms(String phone, String message) {
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);
        // 创建通用的请求对象
        CommonRequest request = new CommonRequest();
        // 指定请求方式
        request.setSysMethod(MethodType.POST);
        // 短信api的请求地址  固定
        request.setSysDomain(domain);
        //签名算法版本  固定
        request.setSysVersion("2017-05-25");
        //请求 API 的名称
        request.setSysAction("SendSms");
        // 发送的手机号
        request.putQueryParameter("PhoneNumbers", phone);
        // 您的申请签名
        request.putQueryParameter("SignName", signName);
        // 您申请的模板 code
        request.putQueryParameter("TemplateCode", templateCode);
        Map<String, Object> params = new HashMap<>(1);
        params.put("code", message);
        // 放入参数  需要把 map转换为json格式  使用fastJson进行转换
        request.putQueryParameter("TemplateParam", JSON.toJSONString(params));
        try {
            CommonResponse response = client.getCommonResponse(request);
            if (response.getHttpResponse().isSuccess()) {
                log.info("发送短信成功！");
                return true;
            } else {
                log.warn("发送短信失败！");
                return false;
            }
        } catch (ClientException e) {
            log.error("阿里云发送短信时发生异常");
        }
        return false;
    }
}
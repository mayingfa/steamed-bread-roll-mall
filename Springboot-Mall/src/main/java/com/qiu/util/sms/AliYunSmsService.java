package com.qiu.util.sms;

public interface AliYunSmsService {

    /**
     * 发送短信的接口
     *
     * @param phone 手机号
     * @param message     消息
     * @return boolean 是否发送成功
     */
    boolean sendSms(String phone, String message);
}
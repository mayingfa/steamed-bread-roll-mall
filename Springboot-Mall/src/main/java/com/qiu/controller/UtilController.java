package com.qiu.controller;

import com.aliyuncs.utils.StringUtils;
import com.qiu.util.general.CommonResult;
import com.qiu.util.general.MyUtils;
import com.qiu.util.mail.IMailService;
import com.qiu.util.sms.AliYunSmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2020/10/29 15:11
 * @description 发送邮件、产生验证码图片、发送短信等工具类操作
 */
@CrossOrigin
@RestController
public class UtilController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private AliYunSmsService aliYunSmsService;

    @Autowired
    private IMailService mailService;

    /**
     * 发送邮箱随机验证码
     *
     * @param email 邮箱地址
     */
    @RequestMapping(value = "/allow/sendHtmlCode")
    public CommonResult sendHtmlCode(String email) {
        try {
            String code = MyUtils.getCode(6);
            Map<String, Object> data = new HashMap<>(2);
            data.put("code", code);
            data.put("year", Calendar.getInstance().get(Calendar.YEAR));
            redisTemplate.opsForValue().set(email, code, 10, TimeUnit.MINUTES);
            mailService.sendTemplateMail(email, "操作验证码", "mail", data);
            return CommonResult.success("验证码已发送，网络有延迟请稍等~");
        } catch (Exception e) {
            return CommonResult.error("发送验证码时发生异常,请稍后重试！");
        }
    }

    /**
     * 发送注册成功邮箱
     *
     * @param email    邮箱地址
     * @param name     用户名
     * @param password 密码
     */
    @RequestMapping(value = "/allow/sendHtmlRegister")
    public CommonResult sendRegisterInfo(String email, String name, String password) {
        try {
            Map<String, Object> data = new HashMap<>(4);
            data.put("account", email);
            data.put("name", name);
            data.put("password", password);
            data.put("year", Calendar.getInstance().get(Calendar.YEAR));
            mailService.sendTemplateMail(email, "注册成功", "register", data);
            return CommonResult.success("注册信息发送成功");
        } catch (Exception e) {
            return CommonResult.error("注册信息发送失败");
        }
    }

    /**
     * 发送角色更新邮件
     *
     * @param email    邮箱地址
     * @param userName 用户名
     * @param roleInfo 角色信息
     */
    @RequestMapping(value = "/allow/sendHtmlRole")
    public CommonResult sendRoleInfo(String email, String userName, String roleInfo) {
        try {
            Map<String, Object> data = new HashMap<>(3);
            data.put("userName", userName);
            data.put("roleInfo", roleInfo);
            data.put("year", Calendar.getInstance().get(Calendar.YEAR));
            mailService.sendTemplateMail(email, "权限管理", "role", data);
            return CommonResult.success("授权信息发送成功");
        } catch (Exception e) {
            return CommonResult.error("授权信息发送失败");
        }
    }

    /**
     * 重置密码
     *
     * @param email    邮箱地址
     * @param password 密码
     */
    @RequestMapping(value = "/allow/sendHtmlResetPwd")
    public CommonResult sendResetPwd(String email, String password) {
        try {
            Map<String, Object> data = new HashMap<>(2);
            data.put("password", password);
            data.put("year", Calendar.getInstance().get(Calendar.YEAR));
            mailService.sendTemplateMail(email, "重置密码", "resetpwd", data);
            return CommonResult.success("新密码信息发送成功");
        } catch (Exception e) {
            return CommonResult.error("新密码信息发送失败");
        }
    }


    /**
     * 验证输入的验证码是否正确
     *
     * @param key  验证的依据
     * @param code 验证码
     */
    @RequestMapping(value = "/allow/checkCode")
    public CommonResult checkCode(String key, String code) {
        String codeK = redisTemplate.opsForValue().get(key);
        if (codeK == null) {
            return CommonResult.error("验证码不存在或已过期，请重新发送！");
        }
        if (codeK.equals(code)) {
            redisTemplate.delete(key);
            return CommonResult.success("验证码成功！", true);
        } else {
            return CommonResult.success("验证码错误！", false);
        }
    }

    /**
     * 用于绑定手机号操作，发送验证码
     *
     * @param phone 发送的手机号
     */
    @RequestMapping(value = "/util/smsCode")
    public CommonResult aliYunSmsCode(String phone) {
        if (!StringUtils.isEmpty(redisTemplate.opsForValue().get(phone))) {
            return CommonResult.error(phone + "的验证码还未过期！");
        }
        //生成验证码并存储到redis中
        String code = MyUtils.getCode(6);
        if (aliYunSmsService.sendSms(phone, code)) {
            redisTemplate.opsForValue().set(phone, code, 10, TimeUnit.MINUTES);
            return CommonResult.success("验证码已发送，网络有延迟请稍等~");
        } else {
            return CommonResult.error("验证码发送失败，请稍后重试~");
        }
    }
}

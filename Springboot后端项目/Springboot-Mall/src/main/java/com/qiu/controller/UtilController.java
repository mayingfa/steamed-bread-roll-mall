package com.qiu.controller;

import com.aliyuncs.utils.StringUtils;
import com.qiu.util.general.CommonResult;
import com.qiu.util.general.MyUtils;
import com.qiu.util.mail.IMailService;
import com.qiu.util.sms.AliYunSmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/10/29 15:11
 * @description 发送邮件、产生验证码图片、发送短信等工具类操作
 */

@RestController
@CrossOrigin
public class UtilController {
    private final RedisTemplate<String,String> redisTemplate;
    private final AliYunSmsService aliYunSmsService;
    private final IMailService mailService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    public UtilController(RedisTemplate<String,String> redisTemplate, IMailService mailService, AliYunSmsService aliYunSmsService) {
        this.mailService = mailService;
        this.aliYunSmsService = aliYunSmsService;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 发送一个简单的文本邮件
     * @param sendTo  收件人
     */
    @RequestMapping(value = "/util/send")
    public String sendmail(String sendTo) {
        mailService.sendSimpleMail(sendTo,"主题：你好普通邮件","内容：第一封邮件");
        return "发送成功！";
    }

    /**
     * 发送一个邮箱随机验证码
     * @param sendTo  收件人
     */
    @RequestMapping(value = "/allow/sendHtmlCode")
    public CommonResult sendHtmlCode(String sendTo){
        try {
            String code = MyUtils.getCode(6);
            Map<String,Object> data = new HashMap<>();
            data.put("code",code);
            data.put("year", Calendar.getInstance().get(Calendar.YEAR));
            redisTemplate.opsForValue().set(sendTo,code,10, TimeUnit.MINUTES);
            mailService.sendTemplateMail(sendTo,"操作验证码","mail", data);
            return CommonResult.success("验证码已发送，网络有延迟请稍等~");
        }catch (Exception e){
            return CommonResult.error("发送验证码时发生异常,请稍后重试！");
        }
    }

    @RequestMapping(value = "/allow/sendHtmlRegister")
    public CommonResult sendRegisterInfo(String sendTo,String pwd,String name){
        try {
            Map<String,Object> data = new HashMap<>();
            data.put("account",sendTo);
            data.put("password",pwd);
            data.put("name",name);
            data.put("year", Calendar.getInstance().get(Calendar.YEAR));
            mailService.sendTemplateMail(sendTo,"注册成功","register", data);
            return CommonResult.success("注册信息发送成功");
        }catch (Exception e){
            return CommonResult.error("注册信息发送失败");
        }
    }

    @RequestMapping(value = "/allow/sendHtmlRole")
    public CommonResult sendRoleInfo(String sendTo,String userName,String roleInfo){
        try {
            Map<String,Object> data = new HashMap<>();
            data.put("userName",userName);
            data.put("roleInfo",roleInfo);
            data.put("year", Calendar.getInstance().get(Calendar.YEAR));
            mailService.sendTemplateMail(sendTo,"权限管理","role", data);
            return CommonResult.success("授权信息发送成功");
        }catch (Exception e){
            return CommonResult.error("授权信息发送失败");
        }
    }

    @RequestMapping(value = "/allow/sendHtmlResetPwd")
    public CommonResult sendResetPwd(String sendTo,String pwd){
        try {
            Map<String,Object> data = new HashMap<>();
            data.put("password",pwd);
            data.put("year", Calendar.getInstance().get(Calendar.YEAR));
            mailService.sendTemplateMail(sendTo,"重置密码","resetpwd", data);
            return CommonResult.success("新密码信息发送成功");
        }catch (Exception e){
            return CommonResult.error("新密码信息发送失败");
        }
    }


    /**
     * 发送一个邮箱附件
     * @param sendTo  收件人
     */
    @RequestMapping(value = "/util/sendFile")
    public String sendmailFile(String sendTo, String content){
        try {
            String filePath="F:\\IDEA\\JAVA_Program\\Springboot-Mall\\src\\main\\resources\\static\\1.zip";
            mailService.sendAttachmentsMail(sendTo,"文件操作", content,filePath);
            return "发送成功！";
        }catch (Exception e){
            e.printStackTrace();
            return  "发送邮件时发生异常,稍后请重新发送！";
        }
    }


    /**
     * 验证输入的验证码是否正确
     * @param key  验证的依据
     * @param code  验证码
     */
    @RequestMapping(value = "/allow/checkCode")
    public CommonResult checkCode(String key,String code){
        String codeK = redisTemplate.opsForValue().get(key);
        if(codeK==null){
            return CommonResult.error("验证码不存在或已过期，请重新发送！");
        }
        if(codeK.equals(code)){
            redisTemplate.delete(key);
            return CommonResult.success("验证码成功！",true);
        }else{
            return CommonResult.success("验证码错误！",false);
        }
    }

    /**
     *  用于绑定手机号操作，发送验证码
     * @param phone  发送的手机号
     */
    @RequestMapping(value = "/util/smsCode")
    public CommonResult aliYunSmsCode(String phone){
        String code = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code)){
            return CommonResult.error(phone + "的验证码还未过期！");
        }
        //生成验证码并存储到redis中
        code = MyUtils.getCode(6);
        boolean isSend = aliYunSmsService.sendSms(phone, code);
        if(isSend){
            redisTemplate.opsForValue().set(phone,code,10, TimeUnit.MINUTES);
            return CommonResult.success("验证码已发送，网络有延迟请稍等~");
        }else{
            return CommonResult.error("验证码发送失败，请稍后重试~");
        }
    }
}

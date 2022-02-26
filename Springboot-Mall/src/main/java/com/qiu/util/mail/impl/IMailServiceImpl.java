package com.qiu.util.mail.impl;

import com.qiu.util.mail.IMailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Map;

/**
 * @author Captain
 */
@Slf4j
@Async
@Service
public class IMailServiceImpl implements IMailService {

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender mailSender;

    /**
     * 邮件昵称
     */
    @Value("${spring.mail.nickname}")
    private String nickname;

    /**
     * 发送的邮箱地址
     */
    @Value("${spring.mail.username}")
    private String from;


    /**
     * 简单文本邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     */
    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        //创建SimpleMailMessage对象
        SimpleMailMessage message = new SimpleMailMessage();
        //邮件发送人
        message.setFrom(nickname + '<' + from + '>');
        //邮件接收人
        message.setTo(to);
        //邮件主题
        message.setSubject(subject);
        //邮件内容
        message.setText(content);
        //发送邮件
        mailSender.send(message);
        log.info("邮件已经发送");
    }

    /**
     * html邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     */
    @Override
    public void sendHtmlMail(String to, String subject, String content) throws Exception {
        //获取MimeMessage对象
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        messageHelper = new MimeMessageHelper(message, true);
        //邮件发送人
        message.setFrom(nickname + '<' + from + '>');
        //邮件接收人
        messageHelper.setTo(to);
        //邮件主题
        message.setSubject(subject);
        //邮件内容，html格式
        messageHelper.setText(content, true);
        mailSender.send(message);
        log.info("邮件已经发送");
    }

    /**
     * 带附件的邮件
     *
     * @param to       收件人
     * @param subject  主题
     * @param content  内容
     * @param filePath 附件
     */
    @Override
    public void sendAttachmentsMail(String to, String subject, String content, String filePath) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(nickname + '<' + from + '>');
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);
        FileSystemResource file = new FileSystemResource(new File(filePath));
        String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
        helper.addAttachment(fileName, file);
        log.info("邮件已经发送");
    }


    /**
     * 发送模板邮件 使用thymeleaf模板
     *
     * @param to      收件人
     * @param subject 主题
     * @param data    模板内容
     */
    @Async
    @Override
    public void sendTemplateMail(String to, String subject, String template, Map<String, Object> data) throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
        // 发送人的邮箱
        messageHelper.setFrom(nickname + '<' + from + '>');
        // 发给谁，对方邮箱
        messageHelper.setTo(to);
        // 邮件标题
        messageHelper.setSubject(subject);
        Context context = new Context();
        //定义模板数据
        context.setVariables(data);
        //获取thymeleaf的html模板
        String emailContent = templateEngine.process(template, context); //指定模板路径
        messageHelper.setText(emailContent, true);
        mailSender.send(mimeMessage);
        log.info("邮件已经发送");
    }
}

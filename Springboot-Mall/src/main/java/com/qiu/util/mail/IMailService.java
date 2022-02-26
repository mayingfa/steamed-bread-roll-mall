package com.qiu.util.mail;

import java.util.Map;

/**
 * @author Captain
 * 发送邮箱
 */
public interface IMailService {
    /**
     * 发送文本邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     */
    void sendSimpleMail(String to, String subject, String content);

    /**
     * 发送HTML邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     * @throws Exception 异常信息
     */
    void sendHtmlMail(String to, String subject, String content) throws Exception;

    /**
     * 发送带附件的邮件
     *
     * @param to       收件人
     * @param subject  主题
     * @param content  内容
     * @param filePath 附件
     * @throws Exception 异常信息
     */
    void sendAttachmentsMail(String to, String subject, String content, String filePath) throws Exception;


    /**
     * 发送模板邮件
     *
     * @param to       收件人
     * @param subject  主题
     * @param template 邮件模板
     * @param data     模板参数
     * @throws Exception 异常信息
     */
    void sendTemplateMail(String to, String subject, String template, Map<String, Object> data) throws Exception;
}
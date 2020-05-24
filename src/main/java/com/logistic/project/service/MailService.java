package com.logistic.project.service;

import com.logistic.project.exception.LogisticException;

public interface MailService {

    /**
     * 发送纯文本邮件
     * @param toAddr 发送给谁
     * @param title 标题
     * @param content 内容
     */
    void sendTextMail(String toAddr, String title, String content) throws LogisticException;

    /**
     * 发送 html 邮件
     * @param toAddr
     * @param title
     * @param content 内容（HTML）
     */
    void sendHtmlMail(String toAddr, String title, String content) throws LogisticException;

    /**
     *  发送待附件的邮件
     * @param toAddr
     * @param title
     * @param content
     * @param filePath 附件地址
     */
    void sendAttachmentsMail(String toAddr, String title, String content, String filePath) throws LogisticException;

    /**
     *  发送文本中有静态资源（图片）的邮件
     * @param toAddr
     * @param title
     * @param content
     * @param rscPath 资源路径
     * @param rscId 资源id (可能有多个图片)
     */
    void sendInlineResourceMail(String toAddr, String title, String content, String rscPath, String rscId) throws LogisticException;

}

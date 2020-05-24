package com.logistic.project.service.Impl;

import com.logistic.project.exception.LogisticException;
import com.logistic.project.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
@Slf4j
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    // 注入常量
    @Value("${mail.fromMail.addr}")
    private String from;

    @Override
    public void sendTextMail(String toAddr, String title, String content) throws LogisticException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(toAddr);
        message.setSubject(title);
        message.setText(content);

        try {
            mailSender.send(message);
            log.info("Text邮件已经发送。");
        } catch (Exception e) {
            log.error("发送Text邮件时发生异常！", e);
            throw new LogisticException("发送Text邮件时发生异常！");
        }
    }

    @Override
    public void sendHtmlMail(String toAddr, String title, String content) throws LogisticException {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(toAddr);
            helper.setSubject(title);
            helper.setText(content, true);

            mailSender.send(message);
            log.info("html邮件发送成功");
        } catch (MessagingException e) {
            log.error("发送html邮件时发生异常！", e);
            throw new LogisticException("发送html邮件时发生异常！");
        }

    }

    @Override
    public void sendAttachmentsMail(String toAddr, String title, String content, String filePath) throws LogisticException {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(toAddr);
            helper.setSubject(title);
            helper.setText(content, true);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, file);
            //helper.addAttachment("test"+fileName, file);

            mailSender.send(message);
            log.info("带附件的邮件已经发送。");
        } catch (MessagingException e) {
            log.error("发送带附件的邮件时发生异常！");
            throw new LogisticException("发送带附件的邮件时发生异常！");
        }

    }

    @Override
    public void sendInlineResourceMail(String toAddr, String title, String content, String rscPath, String rscId) throws LogisticException {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(toAddr);
            helper.setSubject(title);
            helper.setText(content, true);

            FileSystemResource res = new FileSystemResource(new File(rscPath));
            helper.addInline(rscId, res);

            mailSender.send(message);
            log.info("嵌入静态资源的邮件已经发送。");
        } catch (MessagingException e) {
            log.error("发送嵌入静态资源的邮件时发生异常！");
            throw new LogisticException("发送嵌入静态资源的邮件时发生异常！");
        }
    }
}

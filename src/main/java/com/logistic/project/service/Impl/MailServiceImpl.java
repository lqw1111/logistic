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
            log.info("Text Mail Send Success");
        } catch (Exception e) {
            log.error("Error Sending TEXT Mail", e);
            throw new LogisticException("Error Sending TEXT Mail");
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
            log.info("html Mail Send Success");
        } catch (MessagingException e) {
            log.error("Error Sending HTML Mail", e);
            throw new LogisticException("Error Sending HTML Mail");
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
            log.info("ATTACH Mail Send Success");
        } catch (MessagingException e) {
            log.error("Error Sending ATTACH Mail");
            throw new LogisticException("Error Sending ATTACH Mail");
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
            log.info("STATIC Mail Send Success");
        } catch (MessagingException e) {
            log.error("Error Sending STATIC Mail");
            throw new LogisticException("Error Sending STATIC Mail");
        }
    }
}

package com.logistic.project;

import com.logistic.project.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BasemailApplicationTests {

    @Autowired
    private MailService mailService;

    @Autowired
    private TemplateEngine templateEngine;

    /**
     * 测试 文本邮件
     * @throws Exception
     */
    @Test
    public void testSimpleMail() throws Exception {
        mailService.sendTextMail("369487836@qq.com","测试文本邮箱发送","你好你好！");
    }

    /**
     * 测试 html 邮箱
     * @throws Exception
     */
    @Test
    public void testHtmlMail() throws Exception {
        String content="<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail("369487836@qq.com","test simple mail",content);
    }

    @Test
    public void sendAttachmentsMail() throws Exception {
        String filePath="C:\\\\Users\\\\Administrator\\\\Desktop\\\\java并发学习.txt";
        mailService.sendAttachmentsMail("369487836@qq.com", "主题：带附件的邮件", "有附件，请查收！", filePath);
    }


    @Test
    public void sendInlineResourceMail() throws Exception {
        String rscId = "neo006";
        String content="<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "/Users/luoqinwei/Desktop/logisticSystem/logistic_project/README.md";

        mailService.sendInlineResourceMail("369487836@qq.com", "主题：这是有图片的邮件", content, imgPath, rscId);
    }


    @Test
    public void sendTemplateMail() throws Exception {
        //创建邮件正文
        Context context = new Context();
        context.setVariable("id", "006");

        // 传递 emailTemplate.html 模板需要的值，并将模板转换为 String
        String emailContent = templateEngine.process("emailTemplate", context);

        mailService.sendHtmlMail("369487836@qq.com","主题：这是模板邮件",emailContent);
    }


}

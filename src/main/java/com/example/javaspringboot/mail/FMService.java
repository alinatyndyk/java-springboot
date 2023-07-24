package com.example.javaspringboot.mail;

import com.example.javaspringboot.Enums.EMail;
import com.example.javaspringboot.configs.MailConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class FMService {

    private JavaMailSender javaMailSender;
    private Configuration freemarkerConfig;
    private MailConfig mailConfig;


    public void sendEmail(String recipientEmail, EMail templateName, Map<String, Object> variables) throws MessagingException, IOException, TemplateException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setTo(recipientEmail);

        HashMap<String, String> result = mailConfig.parser(templateName);

        helper.setSubject(result.get("subject"));

        Template template = freemarkerConfig.getTemplate(result.get("templateName"));
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, variables);

        helper.setText(html, true);

        javaMailSender.send(message);
    }

    public void sendEmailWithAttachment(String recipientEmail, EMail templateName, Map<String, Object> variables, File file) throws MessagingException, IOException, TemplateException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(recipientEmail);

        HashMap<String, String> result = mailConfig.parser(templateName);

        helper.setSubject(result.get("subject"));

        Template template = freemarkerConfig.getTemplate(result.get("templateName"));
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, variables);

//        File file = ResourceUtils.getFile(filePath);  todo add path!!
//        FileSystemResource file = new FileSystemResource(file);
//        helper.addAttachment(file.getFilename(), file);

        FileSystemResource fileSystemResource = new FileSystemResource(file);

        helper.addAttachment("avatar.jpg", fileSystemResource );
        helper.setText(html, true);

        javaMailSender.send(message);
    }
}


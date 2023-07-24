package com.example.javaspringboot.mail;

import com.example.javaspringboot.Enums.EMail;
import com.example.javaspringboot.configs.MailConfig;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class MailerService {

    private JavaMailSender javaMailSender;
    private MailConfig mailConfig;

    @SneakyThrows
    private String loadTemplateFromFile(String filePath, Map<String, Object> variables) {
        File file = ResourceUtils.getFile(filePath);
        String content = new String(Files.readAllBytes(file.toPath()));

        for (Map.Entry<String, Object> entry : variables.entrySet()) {
            String placeholder = "${" + entry.getKey() + "}";
            String value = entry.getValue().toString();
            content = content.replace(placeholder, value);
        }

        return content;
    }

    @SneakyThrows
    public void sendEmail(String email, EMail template, Map<String, Object> variables) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setTo(email);

        HashMap<String, String> result = mailConfig.parser(template);

        mimeMessageHelper.setSubject(result.get("subject"));

        String templatePath = new String("src/main/java/com/example/javaspringboot/mail/templates/" + result.get("templateName"));
        String templateContent = loadTemplateFromFile(templatePath, variables);

        mimeMessageHelper.setText(templateContent, true);

        javaMailSender.send(mimeMessage);

    }


}

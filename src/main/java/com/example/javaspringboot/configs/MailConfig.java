package com.example.javaspringboot.configs;

import com.example.javaspringboot.Enums.EMail;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@AllArgsConstructor
public class MailConfig {

    public HashMap<String, String> parser(EMail templateName) {

        HashMap<EMail, HashMap<String, String>> languages = new HashMap<>();

        languages.put(EMail.WELCOME, new HashMap<>() {{
            put("subject", "Welcome to our platform!");
            put("templateName", "welcome.ftl");
        }});

        languages.put(EMail.USERS, new HashMap<>() {{
            put("subject", "USERS SUBJECT");
            put("templateName", "users.html");
        }});

        return languages.get(templateName);

    }
}

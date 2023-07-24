package com.example.javaspringboot.configs;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        String path = "file:///" + System.getProperty("user.home") + File.separator + "springboot-lib" + File.separator;

        registry
                .addResourceHandler("users/avatar/**")
                .addResourceLocations(path);
    }

//    public SpringTemplateEngine templateEngine() {
//        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//        templateEngine.setTemplateResolver(new ClassLoaderTemplateResolver());
//        return templateEngine;
//    }

}

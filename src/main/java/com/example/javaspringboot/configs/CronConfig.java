package com.example.javaspringboot.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@Async
public class CronConfig {

//    @Scheduled(fixedDelay = 1000) // cron ******
//    public void hello () {
//        System.out.println("Hello");
//    }

}

package com.maximus.webms;

import com.maximus.webms.services.WebBalanceService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
@EnableFeignClients
public class WebMsApplication {

    public static void main(String[] args) {

        SpringApplication.run(WebMsApplication.class, args);
//        ApplicationContext context =
//                new AnnotationConfigApplicationContext("com.maximus.webms");
//
//        WebBalanceService webBalanceService = context.getBean(WebBalanceService.class);
    }

}

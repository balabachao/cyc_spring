package com.yanshen.sign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableScheduling
public class SignApplication {

    public static void main(String[] args) {
        SpringApplication.run(SignApplication.class, args);
        RestTemplate template = new RestTemplate();
        Map<String, String> params = new HashMap<>();
        String push = "https://api.day.app/XGJxUAwQRa4ARoKTN7Q4aB/Spring签到服务/"+"启动成功";
        template.postForEntity(push, params,String.class);
    }
}


package com.example.demo2.SpringContainer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigruationB {

    @Bean
    public Person A(){
        Person personA = new Person();
        personA.setName("我的B");
        return personA;
    }
}

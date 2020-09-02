package com.example.demo2.SpringContainer;

import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public Person A(){
        Person personA = new Person();
        personA.setName("我的A");
        return personA;
    }
}

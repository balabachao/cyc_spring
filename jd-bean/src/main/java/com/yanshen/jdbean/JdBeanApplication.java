package com.yanshen.jdbean;

import com.yanshen.jdbean.exception.ExceptionFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan("com.yanshen.jdbean")
@MapperScan("com.yanshen.jdbean.mapper")
@EnableConfigurationProperties
@EnableScheduling
@EnableEurekaClient
public class JdBeanApplication {

    @Bean
    public FilterRegistrationBean<ExceptionFilter> testFilterRegistration() {
        FilterRegistrationBean<ExceptionFilter> registration = new FilterRegistrationBean<ExceptionFilter>();
        registration.setFilter(new ExceptionFilter());// 添加过滤器
        registration.addUrlPatterns("/*");// 设置过滤路径，/*所有路径
        registration.setName("ExceptionFilter");// 设置优先级
        registration.setOrder(1);// 设置优先级
        return registration;
    }
    public static void main(String[] args) {
        SpringApplication.run(JdBeanApplication.class, args);
    }

}

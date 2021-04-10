package com.yanshen.jdbean;

import com.yanshen.jdbean.exception.ExceptionFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PreDestroy;

@SpringBootApplication
@ComponentScan("com.yanshen.jdbean")
@MapperScan("com.yanshen.jdbean.mapper")
@EnableConfigurationProperties
@EnableScheduling
@EnableEurekaClient
public class JdBeanApplication  implements ApplicationRunner {
    /**
     * 自定义filter
     * @return
     */
    @Bean
    public FilterRegistrationBean<ExceptionFilter> testFilterRegistration() {
        FilterRegistrationBean<ExceptionFilter> registration = new FilterRegistrationBean<ExceptionFilter>();
        registration.setFilter(new ExceptionFilter());// 添加过滤器
        registration.addUrlPatterns("/*");// 设置过滤路径，/*所有路径
        registration.setName("ExceptionFilter");// 设置优先级
        registration.setOrder(1);// 设置优先级
        return registration;
    }

    public void pushBark(){
        ResponseEntity<String> response = null;
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.day.app/4ppVFBUZxhEnxPzumvtsdF/服务状态/Bean服务已启动";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        response =restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, new HttpEntity<String>(new HttpHeaders()), String.class);
        System.out.println(response);
    }
    public static void main(String[] args) {
        SpringApplication.run(JdBeanApplication.class, args);
    }
    @PreDestroy
    public void destory() {
        System.out.println("在程序启动时执行");
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("在程序关闭时执行");
        pushBark();
    }
}

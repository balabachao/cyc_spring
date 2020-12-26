package com.yanshen.jdbean.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;

/**
 * 打印sql
 * @author songyuanming
 *
 */
@Configuration
public class MybatisPlusConfig {

    @Bean(name = "performanceInterceptor")
    @Profile("dev")
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }
}

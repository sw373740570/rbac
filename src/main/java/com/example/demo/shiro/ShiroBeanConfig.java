package com.example.demo.shiro;

import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroBeanConfig {

    /**
     * 保证实现了Shiro内部lifecycle函数的bean执行
     * 坑：需单独出shiroConfig,否则会引起bean注入先后问题导致空指针异常
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
}

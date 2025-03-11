package com.baishun.mystudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @description: ${description}
 * @Author BAISHUN
 * @Date 2024/9/19 9:58
 */
@SpringBootApplication
@EnableCaching
public class InfrastructureApplication {
    public static void main(String[] args) {
        SpringApplication.run(InfrastructureApplication.class, args);
    }
}
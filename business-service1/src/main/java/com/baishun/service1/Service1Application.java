package com.baishun.service1;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 微服务1
 *
 * @Author BAISHUN
 * @Date: 2024/5/16 15:05
 */
@SpringBootApplication
@EnableDubbo
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 30)
public class Service1Application {
    public static void main(String[] args) {
        SpringApplication.run(Service1Application.class, args);
    }
}

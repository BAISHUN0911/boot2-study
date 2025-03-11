package com.baishun.service1.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.session.SessionProperties;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * SpringBoot程序启动检查
 *
 * @Author BAISHUN
 * @Date: 2024/5/24 15:39
 */
@Component
@Slf4j
public class StartCheck {
    @Autowired
    private SessionProperties sessionProperties;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private PlatformTransactionManager transactionManager;

    public StartCheck(RedisConnectionFactory redisConnectionFactory) {
        log.info("=====> current redis connectionFactory: {}", redisConnectionFactory.getClass().getCanonicalName());
    }

    @PostConstruct
    public void init() {
        System.out.println(sessionProperties);
        log.info("=====> current dataSource: {}", dataSource);
    }
}

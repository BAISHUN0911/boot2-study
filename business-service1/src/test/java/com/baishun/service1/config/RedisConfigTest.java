package com.baishun.service1.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
@Slf4j
class RedisConfigTest {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 测试向redis数据库中设置一个key value
     */
    @Test
    void testSetValue() {
        redisTemplate.opsForValue().set("test1", "value1");
        log.info("redis 数据插入成功");
    }

    @Test
    void testGetValue() {
        String key = "test1";
        String value = (String) redisTemplate.opsForValue().get(key);
        log.info("key {}, 对应的value: {}", key, value);
    }
}
package com.baishun.service1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @description: 通用Controller
 * @Author BAISHUN
 * @Date 2024/8/21 16:53
 */
@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {

    private static final String REQUEST_COUNT = "REQUEST_COUNT_";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // 创建一个固定大小的线程池
    private final ExecutorService executor = Executors.newFixedThreadPool(8);

    @GetMapping("/record")
    public String record(@RequestParam("userId") String userId) {
        String key = REQUEST_COUNT + userId;
        Long count;
        if (Boolean.FALSE.equals(redisTemplate.hasKey(key))) {
            log.info("redis 中不存在该key, 需设置过期时间");
            redisTemplate.opsForValue().increment(key);
            redisTemplate.expire(key, 60, TimeUnit.SECONDS);
        } else {
            count = redisTemplate.opsForValue().increment(key, 1);
            if (count > 3) {
                log.warn("发现疑似恶意用户，用户id {}", userId);
                return "error";
            }
        }

        return "record success";
    }
}

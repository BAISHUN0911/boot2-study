package com.baishun.mystudy.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @Author shengy
 * @Date 2024/10/15 16:55
 */
@Service
public class UserService {


    @Cacheable(cacheNames = "getUser", key = "#userId", condition = "#userId != -1")
    public String getUser(Long userId) throws InterruptedException {
        Thread.sleep(3000);
        return "abc";
    }

    /**
     * 该方法会清除对应的缓存
     */
    @CacheEvict(cacheNames = "getUser", key = "#userId")
    public void updateUser(Long userId) {
    }
}

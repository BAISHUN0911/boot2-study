package org.example.mystudy.gateway.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author BAISHUN
 * @Date: 2024/5/16 15:20
 */
@RestController
@Slf4j
public class HealthController {

    @GetMapping("/service/health")
    @Cacheable
    public String health() {
        String str = m1("s", "tr");
        return "success";
    }

    /**
     *
     * @param arg1
     * @param arg2
     * @return
     */
    private String m1(String arg1, String arg2) {
        return arg1 + arg2;
    }

}

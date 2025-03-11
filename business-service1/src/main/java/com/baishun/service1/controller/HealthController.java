package com.baishun.service1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 健康检查
 * @Author BAISHUN
 * @Date 2024/8/21 16:39
 */
@RestController
@RequestMapping("/healthCheck")
public class HealthController {
    @GetMapping
    public String healthCheck() {
        return "OK";
    }
}

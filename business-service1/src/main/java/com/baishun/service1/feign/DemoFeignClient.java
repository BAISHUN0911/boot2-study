package com.baishun.service1.feign;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * 代码描述
 *
 * @Author BAISHUN
 * @Date: 2024/7/17 22:01
 */
@FeignClient(name = "xxxservice", url = "http://127.0.0.1:8080")
public interface DemoFeignClient {
}

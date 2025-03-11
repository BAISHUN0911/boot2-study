package com.baishun.mystudy.demotest.demo;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 代码描述
 *
 * @Author BAISHUN
 * @Date: 2024/5/29 11:17
 */
@Component
public class DemoConfig {

    @PostConstruct
    public void init() {
        System.out.println(this.getClass().getName() + " init");
        System.out.println("=====DemoConfig=====");
    }

}

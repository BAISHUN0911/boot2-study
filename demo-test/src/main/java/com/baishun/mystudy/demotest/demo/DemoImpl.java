package com.baishun.mystudy.demotest.demo;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 代码描述
 *
 * @Author BAISHUN
 * @Date: 2024/5/29 11:24
 */
@Component
public class DemoImpl extends DemoConfig{
    private String name;
    @PostConstruct
    public void setField() {
        this.name = "jack";
    }
}

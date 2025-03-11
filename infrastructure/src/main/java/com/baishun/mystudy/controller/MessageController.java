package com.baishun.mystudy.controller;

import com.baishun.mystudy.mq.RocketmqMessageProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @Author shengy
 * @Date 2024/10/12 10:40
 */
@RestController
public class MessageController {

    @Autowired
    private RocketmqMessageProducerService rocketmqMessageProducerService;

    @PostMapping("/send")
    public String sendMessage(@RequestBody String message) {
        rocketmqMessageProducerService.sendMessage("TestTopic", message);
        return "Message sent successfully!";
    }
}

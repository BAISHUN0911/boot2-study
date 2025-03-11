package com.baishun.mystudy.mq;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: rocketmq发送消息
 * @Author shengy
 * @Date 2024/10/12 10:30
 */
@Service

public class RocketmqMessageProducerService {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void sendMessage(String topic, String message) {
        rocketMQTemplate.convertAndSend(topic, message);
    }

}

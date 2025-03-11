package com.baishun.mystudy.mq;

import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @description: 消息监听
 * @Author shengy
 * @Date 2024/10/12 10:37
 */
@Component
@RocketMQMessageListener(topic = "TestTopic", consumerGroup = "consumer-group-0")
public class RocketmqMessageListener implements RocketMQListener<MessageExt> {


    @Override
    public void onMessage(MessageExt messageExt) {
        // 处理接收到的消息
        System.out.println("Received message: " + new String(messageExt.getBody()));
    }
}

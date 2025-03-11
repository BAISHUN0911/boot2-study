package com.baishun.mystudy.demotest.mq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @description:
 * @Author BAISHUN
 * @Date 2024/9/18 23:06
 */
public class ConsumerNoProxy {
    public static void main(String[] args) throws MQClientException, InterruptedException {
        // 1. 创建一个消费者，并指定消费者组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("ConsumerGroup");

        // 2. 设置NameServer地址，使用你的NameServer地址
        consumer.setNamesrvAddr("172.17.13.74:9876");

        // 3. 订阅一个或多个Topic，并指定Tag来过滤消息
        consumer.subscribe("TopicTest", "*");

        // 4. 注册回调函数，处理消息
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for (MessageExt msg : msgs) {
                    System.out.printf("Receive message: %s %n", new String(msg.getBody()));
                }
                // 标记消息成功消费
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        // 5. 启动消费者
        consumer.start();
        System.out.printf("Consumer Started.%n");
        Thread.sleep(Integer.MAX_VALUE);
    }
}

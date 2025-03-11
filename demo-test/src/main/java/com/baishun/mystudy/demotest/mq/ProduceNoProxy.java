package com.baishun.mystudy.demotest.mq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * @description:
 * @Author BAISHUN
 * @Date 2024/9/18 17:42
 */
public class ProduceNoProxy {
    public static void main(String[] args) {
        DefaultMQProducer producer = new DefaultMQProducer("ProducerGroup");
        producer.setNamesrvAddr("172.17.13.74:9876");
        producer.setSendMsgTimeout(30 * 1000);
        try {
            producer.start();
            Message msg = new Message("TopicTest", "TagA", "Hello RocketMQ2".getBytes());
            SendResult sendResult = producer.send(msg);
            System.out.println("send msg success, msg: " + sendResult);
        } catch (MQClientException e) {
            throw new RuntimeException(e);
        } catch (MQBrokerException e) {
            throw new RuntimeException(e);
        } catch (RemotingException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        producer.shutdown();
    }
}

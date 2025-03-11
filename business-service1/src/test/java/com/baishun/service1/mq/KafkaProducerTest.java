package com.baishun.service1.mq;

import com.alibaba.fastjson.JSON;
import com.baishun.service1.po.Apple;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@ExtendWith(MockitoExtension.class)
@SpringBootTest
class KafkaProducerTest {
    @Autowired
    private KafkaProducer kafkaProducer;

    @Test
    void test() {
        Apple apple = new Apple("hongfushi", 10);
        String obj2String = JSON.toJSONString(apple);
        System.out.println(obj2String);
    }

    @Test
    void testSendMsg() throws InterruptedException {
        Apple apple = new Apple("hongfushi", 100);
        kafkaProducer.send(apple);
        Thread.sleep(1000 * 10);
    }
}
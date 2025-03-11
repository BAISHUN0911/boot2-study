package com.baishun.mystudy.demotest.controller;

import com.baishun.mystudy.demotest.ws.WebSocketEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 长轮询的简单模拟、SSE服务器消息推送、WebSocket协议
 * @Author BAISHUN
 * @Date 2024/7/30 16:25
 */
@RestController
@Slf4j
public class MessageController {
    private String message = null;

    private static final Map<String, SseEmitter> sseEmitterMap = new ConcurrentHashMap<>();

    @Autowired
    private WebSocketEndpoint webSocketEndpoint;

    @GetMapping("/poll")
    public String pollMessage() throws InterruptedException {
        synchronized (this) {
            if (message == null) {
                // 如果消息为空，则等待，模拟长轮询的超时机制
                wait(60000);
                log.info("timeout");
            }
            String response = message;
            message = null;                 // 重置消息
            return response != null ? response : "null";
        }
    }

    // 方法用于设置新消息，通常由其他部分推送消息时调用
    public synchronized void setMessage(String message) {
        this.message = message;
        notify(); // 通知等待中的客户端
    }

    @GetMapping("/sendMsg/{msg}")
    public String sendMessage(@PathVariable String msg) {
        setMessage(msg);
        log.info("sendMsg: " + msg);
        return "send msg over";
    }

    @GetMapping("/sse/connect")
    public SseEmitter connect(@RequestParam("userId") String userId) {
        return MessageController.getSseEmitter(userId);
    }

    /**
     * 创建连接
     */
    public static SseEmitter getSseEmitter(String userId) {
        try {
            // 设置超时时间，0表示不过期。默认30秒
            SseEmitter sseEmitter = new SseEmitter(0L);
            // 注册回调
//            sseEmitter.onCompletion(completionCallBack(userId));
//            sseEmitter.onError(errorCallBack(userId));
//            sseEmitter.onTimeout(timeoutCallBack(userId));
            sseEmitterMap.put(userId, sseEmitter);
//            count.getAndIncrement();
            return sseEmitter;
        } catch (Exception e) {
            log.info("创建新的sse连接异常，当前用户：{}", userId);
        }
        return null;
    }

    @PostMapping("/sse/send")
    public void sendMsgBySse(@RequestParam("userId") String userId, @RequestParam("msg") String msg) {
        MessageController.sendMessage(userId, msg);
    }

    /**
     * 给指定用户发送消息
     */
    public static void sendMessage(String userId, String message) {
        if (sseEmitterMap.containsKey(userId)) {
            try {
                SseEmitter sseEmitter = sseEmitterMap.get(userId);
                sseEmitter.send(message);
            } catch (IOException e) {
                log.error("用户[{}]推送异常:{}", userId, e.getMessage());
                // 发生异常可能是因为该客户端已经断开连接，所以要删除对应的sse信息
                removeUser(userId);
            }
        }
    }

    private static void removeUser(String userId) {
        sseEmitterMap.remove(userId);
    }

    @PostMapping("/ws/send")
    public String sendMsgByWebSocket(@RequestParam("userId") String userId, @RequestParam("msg") String msg) {
        webSocketEndpoint.sendOneMessage(userId, msg);
        return "send msg over";
    }

}

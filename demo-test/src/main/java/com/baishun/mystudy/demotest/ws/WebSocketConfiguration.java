package com.baishun.mystudy.demotest.ws;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfiguration {

    /**
     * 扫描标注了 @ServerEndpoint 注解的类，并在应用启动时注册这些端点，以便它们可以接收和处理 WebSocket 请求。
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}


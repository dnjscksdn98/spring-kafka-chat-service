package com.study.SpringBootKafkaChat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker  // 웹소켓 서버 활성화
public class WebSocketConfigurations implements WebSocketMessageBrokerConfigurer {

    /**
     * 클라이언트가 웹 소켓 서버에 연결하는 데 사용할 웹 소켓 엔드 포인트를 등록합니다.
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry
                .addEndpoint("/chatting")  // 웹소켓연결 주소
                .setAllowedOrigins("*")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");  // 메시지를 보낼 URL - /app/message
        registry.enableSimpleBroker("/topic");
    }
}

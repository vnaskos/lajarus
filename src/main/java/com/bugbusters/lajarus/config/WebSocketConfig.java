package com.bugbusters.lajarus.config;

import com.bugbusters.lajarus.websocket.GameHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * Websocket endpoint configuration
 * 
 * @author Vasilis Naskos
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(gameHandler(), "/lajarus").setAllowedOrigins("*");
    }

    @Bean
    public GameHandler gameHandler() {
        return new GameHandler();
    }
}

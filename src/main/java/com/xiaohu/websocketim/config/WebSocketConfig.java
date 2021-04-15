package com.xiaohu.websocketim.config;

import com.xiaohu.websocketim.config.Interceptor.WebSocketInterceptor;
import com.xiaohu.websocketim.handle.TextMessageHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @author xiaohu
 * @version 1.0
 * @date 2021/4/14 10:36
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private WebSocketInterceptor webSocketInterceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(webSocketHandler(),"/ws/{uid}").setAllowedOrigins("*").addInterceptors(webSocketInterceptor);
    }

    @Bean
    public WebSocketHandler webSocketHandler(){
        return new TextMessageHandle();
    }
}

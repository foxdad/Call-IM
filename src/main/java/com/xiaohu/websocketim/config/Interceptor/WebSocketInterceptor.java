package com.xiaohu.websocketim.config.Interceptor;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.net.URI;
import java.util.Map;

/**
 * @author xiaohu
 * @version 1.0
 * @date 2021/4/14 10:37
 */
@Component
public class WebSocketInterceptor implements HandshakeInterceptor {
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {

        String url = request.getURI().getPath();
        int lastIndexOf = url.lastIndexOf("/");
        Long userId = Long.valueOf(url.substring(lastIndexOf+1));
        map.put("uid",userId);
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {

        System.out.println("拦截器握手成功");
    }
}

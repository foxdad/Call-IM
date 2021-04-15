package com.xiaohu.websocketim.handle;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaohu.websocketim.entity.vo.MessageVo;
import com.xiaohu.websocketim.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.w3c.dom.Text;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xiaohu
 * @version 1.0
 * @date 2021/4/14 10:47
 */
@Component
public class TextMessageHandle extends TextWebSocketHandler {
    Map<Long,WebSocketSession> MAP = new ConcurrentHashMap<>();

    @Autowired
    private RedisTemplate<String,String>  redisTemplate;
    @Autowired
    private MessageService messageService;




    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //连接就存储到map里面
        Long key = (Long)session.getAttributes().get("uid");
        MAP.put(key,session);

        session.sendMessage(new TextMessage("欢迎连接IM"));
    }

    //读写锁
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {


        //json
//        Object uid = session.getAttributes().get("uid");
        String payload = message.getPayload();
        MessageVo messageVo = JSON.parseObject(payload, MessageVo.class);
        //判断发送用户在Redis里面是否有呢？存储用户跟客服的对应关系
        String sendId = redisTemplate.opsForValue().get(messageVo.getSendUser().getId().toString());
        if (!StringUtils.hasText(sendId)) {
            redisTemplate.opsForValue().set(messageVo.getSendUser().getId().toString(),messageVo.getReceiverUser().getId().toString());
        }

        WebSocketSession receiverSession = MAP.get(messageVo.getReceiverUser().getId());
        //持久化
        Boolean flag = messageService.saveMessage(messageVo);
        receiverSession.sendMessage(new TextMessage(messageVo.getMessage()));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        session.sendMessage(new TextMessage("服务器错误"));
        System.out.println("异常出现了");
    }

    @Override
    protected void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {
        System.out.println("调用了");
        super.handlePongMessage(session, message);
    }


    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("关闭了");
    }
}

package com.xiaohu.websocketim.service.impl;

import com.xiaohu.websocketim.entity.Message;
import com.xiaohu.websocketim.entity.User;
import com.xiaohu.websocketim.entity.vo.MessageVo;
import com.xiaohu.websocketim.service.MessageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author xiaohu
 * @version 1.0
 * @date 2021/4/14 20:35
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Override
    public Boolean saveMessage(MessageVo message) {
        //判断是否是恶意发送
        String receiverId = redisTemplate.opsForValue().get(message.getSendUser().getId().toString());
        if (!StringUtils.hasText(receiverId)) {
            return false;
        }
        Message saveMessage = new Message();
        BeanUtils.copyProperties(message,saveMessage);
        //封装发送用户
        Message result = mongoTemplate.save(saveMessage);
        return result!=null? true:false;
    }
}

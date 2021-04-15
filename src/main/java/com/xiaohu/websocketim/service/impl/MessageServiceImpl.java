package com.xiaohu.websocketim.service.impl;

import com.xiaohu.websocketim.entity.Message;
import com.xiaohu.websocketim.entity.User;
import com.xiaohu.websocketim.entity.vo.MessageVo;
import com.xiaohu.websocketim.service.MessageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

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
    public Message saveMessage(MessageVo message) {
        //判断是否是恶意发送
        String receiverId = redisTemplate.opsForValue().get(message.getSendUser().getId().toString());
        if (!StringUtils.hasText(receiverId)) {
            return null;
        }
        Message saveMessage = new Message();
        BeanUtils.copyProperties(message,saveMessage);
        saveMessage.setSendDate(new Date());
        saveMessage.setStatus(0);
        saveMessage.setReadDate(null);
        saveMessage.setFlagDelete(0);
        //封装发送用户
        Message result = mongoTemplate.save(saveMessage);
        return result;
    }

    /**
     * 分页查询聊天记录
     * @param sendId
     * @param receiverId
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public List<Message> selectPageMessage(Long sendId, Long receiverId, Integer page, Integer pageSize) {
        //sendUser
        Criteria send = Criteria.where("sendUser.id").is(sendId).and("receiverUser.id").is(receiverId);
        //receiverUser
        Criteria receiver = Criteria.where("sendUser.id").is(sendId).and("receiverUser.id").is(receiverId);

        PageRequest pageRequest = PageRequest.of(page-1,pageSize, Sort.by(Sort.Direction.DESC,"send_date"));

        Criteria criteria = new Criteria().orOperator(send,receiver);
        Query query = new Query(criteria).with(pageRequest);
        List<Message> messageList = mongoTemplate.find(query, Message.class);

        return null;
    }
}

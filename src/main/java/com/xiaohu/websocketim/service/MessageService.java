package com.xiaohu.websocketim.service;

import com.xiaohu.websocketim.entity.Message;
import com.xiaohu.websocketim.entity.vo.MessageVo;

import java.util.List;

/**
 * @author xiaohu
 * @version 1.0
 * @date 2021/4/14 20:35
 */
public interface MessageService {

    public Message saveMessage(MessageVo message);
    public List<Message> selectPageMessage(Long sendId, Long receiverId,Integer page ,Integer pageSize );
//    public Boolean delectMessage();
//    public Boolean update();
}

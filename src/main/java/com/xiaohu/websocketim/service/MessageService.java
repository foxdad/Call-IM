package com.xiaohu.websocketim.service;

import com.xiaohu.websocketim.entity.Message;
import com.xiaohu.websocketim.entity.vo.MessageVo;

/**
 * @author xiaohu
 * @version 1.0
 * @date 2021/4/14 20:35
 */
public interface MessageService {

    public Boolean saveMessage(MessageVo message);
//    public void selectPageMessage();
//    public Boolean delectMessage();
//    public Boolean update();
}

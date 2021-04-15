package com.xiaohu.websocketim;

import com.alibaba.fastjson.JSON;
import com.xiaohu.websocketim.entity.User;
import com.xiaohu.websocketim.entity.vo.MessageVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WebsocketImApplicationTests {

    @Test
    void contextLoads() {
        User user = new User(1L,"小狐");
        User user2 = new User(2L,"小明");

//        MessageVo messageVo = new MessageVo("我是消息",user,user2);
//        System.out.println(JSON.toJSON(messageVo));
//        MessageVo messageVo1 = JSON.parseObject(JSON.toJSON(messageVo).toString(), MessageVo.class);
//        System.out.println();
    }

}

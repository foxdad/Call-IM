package com.xiaohu.websocketim.entity.vo;

import com.xiaohu.websocketim.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author xiaohu
 * @version 1.0
 * @date 2021/4/14 18:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageVo implements Serializable {

    private String message;
    private User sendUser;
    private User receiverUser;
    //TODO 当前时间
}

package com.xiaohu.websocketim.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * @author xiaohu
 * @version 1.0
 * @date 2021/4/14 18:31
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collation = "message")
public class Message {

    @Id
    private ObjectId objectId;
    private String message;
    @Indexed
    private Integer status;
    @Field("send_date")
    private Date sendDate;
    @Field("read_date")
    private Date readDate;
    @Indexed
    private User sendUser;
    @Indexed
    private User receiverUser;
    @Indexed
    private Integer flagDelete;
}

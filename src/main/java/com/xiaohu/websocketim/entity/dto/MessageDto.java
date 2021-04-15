package com.xiaohu.websocketim.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.io.Serializable;

/**
 * @author xiaohu
 * @version 1.0
 * @date 2021/4/15 11:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto implements Serializable {

    private ObjectId objectId;

    private String message;
}

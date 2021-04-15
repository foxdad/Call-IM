package com.xiaohu.websocketim.constant;

/**
 * @author xiaohu
 * @version 1.0
 * @date 2021/4/15 10:42
 */
public enum ResultEnum {

    SUCCESS(200,"修改成功"),
    FAILURE(400,"修改失败");

    public int code;

    public String message;

     ResultEnum(int code ,String message){
        this.code = code;
        this.message = message;
    }

}

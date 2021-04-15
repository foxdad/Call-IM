package com.xiaohu.websocketim.entity;

import com.xiaohu.websocketim.constant.ResultEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xiaohu
 * @version 1.0
 * @date 2021/4/15 10:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    private int code;

    private String message;

    private Object data;

    public Result(int code,String message) {
        this.code = code;
        this.message = message;
    }

    public static Result success (int code,String message) {
        return new Result(code,message);
    }

    public static Result failure(int code,String message) {
        return new Result(code,message);
    }






}

package com.xiaohu.websocketim.controller;

import com.xiaohu.websocketim.constant.ResultEnum;
import com.xiaohu.websocketim.entity.Result;
import com.xiaohu.websocketim.utils.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaohu
 * @version 1.0
 * @date 2021/4/15 9:50
 */
@Api(tags = "客服调动接口")
@RequestMapping("/call")
@RestController
public class CallController {

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 客服转接功能依靠缓存来实现
     * @param sourceId 目标用户
     * @param targetId 目标客服
     * TODO 还有需要完善的地方  通知目标客服的页面刷新
     */
    @ApiOperation("客服转接功能")
    @GetMapping("/updateThroughCall/{sourceId}/{targetId}")
    public Result updateThroughCall(@PathVariable("sourceId") String sourceId, @PathVariable("targetId") String targetId) {
        String sourceUser = redisUtils.get(sourceId);
        //没有用户的行为
        if (!StringUtils.hasText(sourceUser)) {
            return Result.failure(ResultEnum.FAILURE_SPLIT.code, ResultEnum.FAILURE_SPLIT.message);
        }
        //修改
        boolean result = redisUtils.set(sourceId, targetId);
        if (result) {
            return Result.success(ResultEnum.SUCCESS.code,ResultEnum.SUCCESS.message);
        }
        //修改失败
        return Result.success(ResultEnum.SUCCESS.code, ResultEnum.SUCCESS.message);
    }


}

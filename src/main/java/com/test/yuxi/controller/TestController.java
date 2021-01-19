package com.test.yuxi.controller;

import com.test.yuxi.redis.RedisUtil;
import com.test.yuxi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Vector;
import java.util.WeakHashMap;

@Slf4j  // 代替 Logger logger = LoggerFactory.getLogger(xxxx.class);
@RestController
@RequestMapping("/TestController")
public class TestController {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserService userService;

    @RequestMapping("/test")
    @ResponseBody
    public String userTest(){

        StringBuilder stringBuilder = new StringBuilder();

        log.error("logger.error 日志打印正常");
        log.info("logger.info 日志打印正常");
        System.out.println("执行 Test 中"); stringBuilder.append("方法执行正常 \n");

        /*redisUtil.set("0123456789", "测试Redis");
        if (redisUtil.get("0123456789") != null){
            System.out.println("Redis 运行正常 "); stringBuilder.append("Redis运行正常");
        }else{
            System.out.println("Redis 异常！！ "); stringBuilder.append("Redis运行异常 ");
        }*/
        System.out.println(userService.userQueryFor001().getJob());
        System.out.println("数据库连接正常， 获取 001 用户正常");

        return stringBuilder.append("\n OK").append("\n 数据库连接正常").toString();
    }


    @RequestMapping("/chrildrenChar")
    @ResponseBody
    public ArrayList charsSum(){
        int[] intArrs = {1, -2, 3, 5, -2, 6, -1};
        ArrayList list = new ArrayList();
        int max; int temp; int start; int end;
        for (int i = 0; i < i; i++) {

        }
        return null;
    }


}

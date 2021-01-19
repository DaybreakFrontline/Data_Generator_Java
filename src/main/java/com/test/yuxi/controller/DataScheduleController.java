package com.test.yuxi.controller;

import com.test.yuxi.pojo.RequestPojo;
import com.test.yuxi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/DataScheduleController")
public class DataScheduleController {

    @Resource
    private UserService userService;

    @PostMapping("/startInsert")
    @ResponseBody
    public String startInsert(@RequestBody RequestPojo requestPojo) throws InterruptedException {

        Integer dataCount = requestPojo.getDataCount();
        Integer threadCount = requestPojo.getThreadCount();
        Date dateEnd = requestPojo.getDateEnd();

        String s = InsertThread.runInsertData(dataCount, threadCount, dateEnd);
        System.out.println(s);
        return "开始执行";
    }

    @ResponseBody
    @PostMapping("/userCount")
    public String userCount(){

        return "K_USER_BASIC表内共有 " + userService.queryUserCount() / 10000 + "万条数据";

    }

}


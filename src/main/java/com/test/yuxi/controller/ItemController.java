package com.test.yuxi.controller;

import com.test.yuxi.pojo.ItemInfo;
import com.test.yuxi.redis.RedisUtil;
import com.test.yuxi.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@RestController
@RunWith(JUnit4.class)
@RequestMapping("/ItemController")
public class ItemController {

    @Autowired
    private RedisUtil RedisUtil;

    @Autowired
    private ItemService itemService;

    @ResponseBody
    @PostMapping("/insertItemInfo")
    public String insertItemInfo(@RequestBody ItemInfo itemInfo){
        String result = null;
        if (itemInfo != null){
            log.info(itemInfo.toString());
            result = itemService.insertItem(itemInfo);
        }
        return result;
    }

    @ResponseBody
    @PostMapping("/queryItemForAll")
    public List<Object> queryItemForAll(@RequestBody ItemInfo itemInfo){
        List<Object> result = null;

        if (itemInfo != null){
            // 先从Redis查询
            result = RedisUtil.lGet("ItemAllForYuxi", 0, -1);
            if(result == null || result.size() < 1){
                log.info("Redis 没有查出数据 queryItemForAll");
                itemService.selectItemForAll();
            }else{
                log.info("Redis OK");
                log.info(result.toString());
                return result;
            }
        }
        return result;
    }

    @ResponseBody
    @PostMapping("/queryItemForOne")
    public ItemInfo queryItemForOne(@RequestBody ItemInfo itemInfo){
        ItemInfo result = null;
        if (itemInfo != null){
            // 先从Redis查询
            result = (ItemInfo) RedisUtil.get(itemInfo.getItemId());
            // 判断是否存在
            if(result == null){
                // 查库，查完装进redis
                result = itemService.selectItemForOne(itemInfo.getItemId());
                return result;
            }else{
                return result;
            }
        }
        return null;
    }



}

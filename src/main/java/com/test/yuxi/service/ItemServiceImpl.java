package com.test.yuxi.service;

import com.test.yuxi.mapper.ItemInfoMapper;
import com.test.yuxi.pojo.ItemInfo;
import com.test.yuxi.redis.RedisUtil;
import com.test.yuxi.utils.SnowflakeIdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import java.util.List;
import java.util.logging.Logger;

@Slf4j
@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ItemInfoMapper itemInfoMapper;

    @Override
    public List<ItemInfo> selectItemForAll() {

        // 查询全部
        List<ItemInfo> itemInfos = itemInfoMapper.queryAllItem();
        // 放入Redis
        if(itemInfos != null || itemInfos.size() < 1){
            itemInfos.forEach(itemInfo -> {
                redisUtil.set("ItemAllForYuxi", itemInfo);
            });
        }
        return itemInfos;
    }

    @Override
    public ItemInfo selectItemForOne(String itemId) {

        return null;
    }

    @Override
    public Integer changeItem(ItemInfo itemInfo) {
        return null;
    }

    @Override
    public Integer deleteItem(ItemInfo itemInfo) {
        return null;
    }

    @Override
    public String insertItem(ItemInfo itemInfo) {
        Integer count = 0;
        if(itemInfo == null){
            return "-2";
        }{
            // 主键ID
            String itemId = SnowflakeIdWorker.IDGenerator().toString();
            itemInfo.setItemId(itemId);
            log.info(itemInfo.toString());
            count = itemInfoMapper.addItem(itemInfo);
            // 可以拿到主键ID
            log.info("主键ID:" + itemInfo.getItemId());
            if(count > 0){
                // 装进Redis 超时时间300s
                redisUtil.set(itemId, itemInfo,300);
                // 装进全部Item
                redisUtil.set("ItemAllForYuxi", itemInfo,300000);
                redisUtil.lSet("ItemAllForYuxi", itemInfo);
                return itemInfo.getItemId();
            }else{
                return "-1";
            }
        }
    }
}

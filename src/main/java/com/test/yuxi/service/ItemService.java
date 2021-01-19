package com.test.yuxi.service;

import com.test.yuxi.pojo.ItemInfo;

import java.util.List;

public interface ItemService {

    public List<ItemInfo> selectItemForAll();

    public ItemInfo selectItemForOne(String itemId);

    public Integer changeItem(ItemInfo itemInfo);

    public Integer deleteItem(ItemInfo itemInfo);

    public String insertItem(ItemInfo itemInfo);


}

package com.test.yuxi.mapper;

import com.test.yuxi.pojo.ItemInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ItemInfoMapper {

    public Integer addItem(ItemInfo itemInfo);

    public Integer deleteItem(ItemInfo itemInfo);

    public Integer modifyItem(ItemInfo itemInfo);

    public ItemInfo queryItem(ItemInfo itemInfo);

    public List<ItemInfo> queryAllItem();

}

package com.test.yuxi.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ItemInfo implements Serializable {

    private String itemId;

    private String itemName;

    private String itemMesege;

    private String itemPrice;

    private String itemTime;


}

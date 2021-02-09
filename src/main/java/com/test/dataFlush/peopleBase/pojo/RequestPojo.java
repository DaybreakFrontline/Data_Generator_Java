package com.test.dataFlush.peopleBase.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class RequestPojo {

    // 总共插入多少条数据    单位 万
    private Integer dataCount;

    // 使用多少个线程插入    单位 个
    private Integer threadCount;

    // 插入到几点结束
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date dateEnd;


}

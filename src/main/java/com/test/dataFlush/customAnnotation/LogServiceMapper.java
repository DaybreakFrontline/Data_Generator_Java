package com.test.dataFlush.customAnnotation;

import com.test.dataFlush.peopleBase.pojo.UserBasic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LogServiceMapper {

    @Results({
            @Result(property = "id", column = "ID"),
            @Result(property = "functionName", column = "FUNCTION_NAME"),
            @Result(property = "methodEntryTime", column = "METHOD_ENTRY_TIME")
    })
    @Select("select * from K_LOG_INFO")
    UserBasic selectLogInfo(Integer id);

    @Select("SELECT COUNT(*) FROM K_LOG_INFO")
    Integer selectLogCount();

}

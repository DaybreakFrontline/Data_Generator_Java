package com.test.dataFlush.peopleBase.mapper;

import com.test.dataFlush.peopleBase.pojo.UserBasic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserServiceMapper {

    @Results({
            @Result(property = "id", column = "ID"),
            @Result(property = "name", column = "NAME"),
            @Result(property = "addres", column = "ADDRES"),
            @Result(property = "job", column = "JOB"),
            @Result(property = "phone", column = "PHONE"),
            @Result(property = "time", column = "time"),
            @Result(property = "identity", column = "IDENTITY")
    })
    @Select("select NAME from K_USER_BASIC WHERE ID = #{id} LIMIT 0, 10 ")
    UserBasic selectInfoById(Integer id);

    @Select("SELECT COUNT(*) FROM K_USER_BASIC")
    Integer selectUserCount();

}

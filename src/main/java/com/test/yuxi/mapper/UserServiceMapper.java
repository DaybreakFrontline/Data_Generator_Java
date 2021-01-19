package com.test.yuxi.mapper;

import com.test.yuxi.pojo.UserBasic;
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
            @Result(property = "remark", column = "REMARK"),
            @Result(property = "phone", column = "PHONE"),
            @Result(property = "time", column = "TIME"),
            @Result(property = "life", column = "LIFE")
    })
    @Select("select * from K_USER_BASIC WHERE ID = #{id} ")
    public UserBasic selectUserFor001(String id);

    @Select("SELECT COUNT(*) FROM K_USER_BASIC")
    public Integer selectUserCount();

}

package com.test.yuxi.service;

import com.test.yuxi.mapper.UserServiceMapper;
import com.test.yuxi.pojo.UserBasic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserServiceMapper userServiceMapper;

    @Override
    public UserBasic userQueryFor001() {
        return userServiceMapper.selectUserFor001("001");
    }

    @Override
    public Integer queryUserCount() {
        return userServiceMapper.selectUserCount();
    }


}

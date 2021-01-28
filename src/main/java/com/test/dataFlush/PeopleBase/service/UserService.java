package com.test.dataFlush.PeopleBase.service;

import com.test.dataFlush.PeopleBase.pojo.UserBasic;

import java.util.Date;


public interface UserService {

    public Integer queryUserCount();

    public String runInsertData(int ins, int threadCount, Date dateEnd);
}

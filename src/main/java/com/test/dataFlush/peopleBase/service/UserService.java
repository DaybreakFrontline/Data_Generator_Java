package com.test.dataFlush.peopleBase.service;

import java.util.Date;


public interface UserService {

    public Integer queryUserCount();

    public String runInsertData(int ins, int threadCount, Date dateEnd);
}

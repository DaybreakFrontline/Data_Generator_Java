package com.test.dataFlush.PeopleBase.service;

import com.test.dataFlush.PeopleBase.mapper.UserServiceMapper;
import com.test.dataFlush.PeopleBase.utils.*;
import com.test.dataFlush.PeopleBase.utils.nameGenerator.JobGenerator;
import com.test.dataFlush.PeopleBase.utils.nameGenerator.NameMaker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

@Service
public class UserServiceImpl extends Thread implements UserService{

    @Resource
    private UserServiceMapper userServiceMapper;

    @Value("spring.datasource.url")
    private String URL;

    @Value("spring.datasource.username")
    private String USERNAME;

    @Value("spring.datasource.password")
    private String PASSWORD;

    @Value("spring.datasource.driver-class-name")
    private String  CLASSNAME;

    public static Integer i1;
    public static Date dateEnds;

    @Override
    public Integer queryUserCount() {
        return userServiceMapper.selectUserCount();
    }


    @Override
    public String runInsertData(int ins, int threadCount, Date dateEnd) {
        i1 = ins;
        dateEnds = dateEnd;
        for (int i = 0; i < threadCount; i++) {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new UserServiceImpl().start();
        }
        return threadCount + "个线程，每个线程" + i1 + "条数据开始插入。 预计共插入" + ins + "万条数据";
    }

    @Override
    public void run(){
        String url = URL;
        String name = USERNAME;
        String user = CLASSNAME;
        String password = PASSWORD;
        Connection conn = null;
        try {
            Class.forName(name);
            conn = DriverManager.getConnection(url, user, password);
            //关闭自动提交
            conn.setAutoCommit(false);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        long begin = new Date().getTime();
        String sql = "INSERT INTO K_USER_BASIC (ID,NAME,ADDRES,JOB,PHONE,TIME,IDENTITY, CREATE_DATE) VALUES\n" +
                "(?,?,?,?,?,DATE_FORMAT(?,\"%Y-%m-%d %T\") , ?, NOW())";
        try {
            StringBuilder sqls = new StringBuilder();
            conn.setAutoCommit(false);
            PreparedStatement pst = conn.prepareStatement(sql);
            System.out.println(i1 / 100);
            //编写事务
            for(int i = 1; i <= i1 / 100; i++){

                for(int j = 1 ;j <= 2000; j++){

                    String a = SnowflakeIdWorker.IDGenerator().toString();
                    String b = NameMaker.generateName();
                    String c = CityGenerator.getProCity();
                    String d = JobGenerator.jobGen();
                    String e = PhoneGenerator.haoma();
                    String f = RandomDate.testRondomDate();
                    String g = PersenId.generateID();

                    pst.setString(1, a);      // id 32位
                    pst.setString(2, b);      // name
                    pst.setString(3, c);      // addres
                    pst.setString(4, d);      // JOB
                    pst.setString(5, e);      // phone
                    pst.setString(6, f);      // time
                    pst.setString(7, g);      // 身份证号
                    pst.addBatch();//批量添加信息

                }
                // 执行批量操作
                pst.executeBatch();
                // 提交事务
                conn.commit();

                // 判断时间是否结束
                Date dataNow = new Date();
                if (dataNow.after(dateEnds)){
                    break;
                }
            }
            pst.close();
            conn.close();
            System.out.println("全部结束！！！！");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 结束时间
        Long end = new Date().getTime();
        // 耗时
        System.out.println(Thread.currentThread().getName() + " 1万条数据插入花费时间 : " + (end - begin) / 1000 + " s"+"  插入完成");
    }
}





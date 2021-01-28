package com.test.dataFlush.PeopleBase.utils;
import com.test.dataFlush.PeopleBase.utils.nameGenerator.JobGenerator;
import com.test.dataFlush.PeopleBase.utils.nameGenerator.NameMaker;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

@Component
public class InsertDataForRandom {

    public static String run(Integer count, Integer threadCount, Date dateEnd){

        // 每一次循环插入1万条数据
        for (int i = 0; i < count; i++) {
        }

        return null;
    }

    public static String insertDb(){
        String url = DataBaseSource.url;    //数据库连接地址
        String name = DataBaseSource.name;
        String user = DataBaseSource.user;
        String password = DataBaseSource.password;//密码
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
        String sql = "INSERT INTO K_USER_BASIC (ID,NAME,ADDRES,JOB,PHONE,TIME,IDENTITY) VALUES\n" +
                "(?,?,?,?,?,DATE_FORMAT(?,\"%Y-%m-%d %T\") ,?)";
        try {
            StringBuilder sqls = new StringBuilder();
            conn.setAutoCommit(false);
            PreparedStatement pst = conn.prepareStatement(sql);
            //编写事务
            for(int i = 1; i <= 4; i++){

                for(int j = 1 ;j <= 2500; j++){

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

                    System.out.println(Thread.currentThread().getName() + "  第" + i + "段，第" + j + "条：" + a + " " + b + " " + c + " " + d + " " + e + " " + f + " " + g + " ");
                }
                // 执行批量操作
                pst.executeBatch();
                // 提交事务
                conn.commit();
            }
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 结束时间
        Long end = new Date().getTime();
        // 耗时
        System.out.println(Thread.currentThread().getName() + " 1万条数据插入花费时间 : " + (end - begin) / 1000 + " s"+"  插入完成");

        return null;
    }



}

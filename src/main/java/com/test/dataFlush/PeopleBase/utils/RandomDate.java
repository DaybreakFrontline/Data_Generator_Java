package com.test.dataFlush.PeopleBase.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RandomDate {

    public static void main(String[] args) {
        testRondomDate();
    }

    public static String testRondomDate() {
        Date date = randomDate("2000-01-01","2020-12-31");
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }


    /**
     * 获取随机日期
     * @param beginDate 起始日期，格式为：yyyy-MM-dd
     * @param endDate 结束日期，格式为：yyyy-MM-dd
     * @return
     */
    private static Date randomDate(String beginDate,String endDate){
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date start = format.parse(beginDate);
            Date end = format.parse(endDate);

            if(start.getTime() >= end.getTime()){
                return null;
            }

            long date = random(start.getTime(),end.getTime());

            return new Date(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static long random(long begin,long end){
        long rtn = begin + (long)(Math.random() * (end - begin));
        if(rtn == begin || rtn == end){
            return random(begin,end);
        }
        return rtn;
    }


}

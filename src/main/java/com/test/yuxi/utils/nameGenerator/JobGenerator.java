package com.test.yuxi.utils.nameGenerator;

public class JobGenerator {

    private static String[] job = {"老师","医生","司机","人事","创业者","程序员","护士","工人","商人","保洁","设计师","分析师","天文学家","数学家","画家","歌手","快递员","机械师","保安","清洁工","服务员","售票员","运动员","飞行员","主持人","模特儿","律师","会计","作家","厨师","记者","军人","导游","推销员","赛车手"  };

    public static void main(String[] args) {
        System.out.println(job[(int) (Math.random() * job.length)]);
    }

    public static String jobGen(){
        return job[(int) (Math.random() * job.length)];
    }


}

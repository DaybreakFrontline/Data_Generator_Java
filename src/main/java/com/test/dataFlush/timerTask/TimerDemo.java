package com.test.dataFlush.timerTask;

import com.test.dataFlush.peopleBase.mapper.UserServiceMapper;
import com.test.dataFlush.peopleBase.pojo.UserBasic;
import com.test.dataFlush.redis.CtimsModelEnum;
import com.test.dataFlush.redis.RedisUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class TimerDemo {

    @Resource
    private UserServiceMapper userServiceMapper;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    private RedisUtils redisUtils;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 300000) //通过@Scheduled声明该方法是计划任务，使用fixedRate属性每隔固定时间执行
    public void reportCurrentTime() {
        String messageId = String.valueOf(UUID.randomUUID());
        System.out.println("每隔5分钟执行一次 " + dateFormat.format(new Date()) + "  " + messageId);
        // 数据库链接状态
        UserBasic userBasic = userServiceMapper.selectInfoById(1);
        try {
            if (userBasic.getName().equals("潘九峰")){
                System.out.println("MySQL 正常" + "  " + messageId);
            }
        }catch (Exception e){
            System.out.println("MySQL Error" + e.getMessage() + "  " + messageId);
        }

        // MQ链接状态
        try {
            System.out.println("RabbitMQ " + sendDirectMessage() + "  " + messageId);
        }catch (Exception e){
            System.out.println("RabbitMQ " + e.getMessage() + "  " + messageId);
        }

        // Redis连接状态
        System.out.println("Redis " + redisLink() + "  " + messageId);

    }

    //使用cron属性可按照指定时间执行，本例指的是每天20点07分执行
    @Scheduled(cron = "0 07 20 ? * *" )
    //cron是UNIX和类UNIX(Linux)系统下的定时任务
    public void fixTimeExecution(){
        System.out.println("在指定时间 "+dateFormat.format(new Date())+" 执行");

    }

    private String sendDirectMessage(){
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "Hello RabbitMQ !";
        String creatTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("creatTime", creatTime);

        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        try {
            rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting", map);
        }catch (Exception e){
            e.printStackTrace();
            return "Error";
        }
        return "正常";
    }

    private String redisLink(){
        try {
            String token = String.valueOf(UUID.randomUUID());
            redisUtils.set(CtimsModelEnum.CTIMS_COMM_CAP, "RedisLinkerTest", token);
            String redisLinkerTest = redisUtils.get(CtimsModelEnum.CTIMS_COMM_CAP, "RedisLinkerTest");
            if (token.equals(redisLinkerTest)){
                return "正常";
            }else{
                return "Error";
            }
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }



}
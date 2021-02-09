package com.test.dataFlush.rabbitMQ;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 测试RabbitMQ的接口
 */
@RestController
@RequestMapping("/rabbitMQ_test")
public class SendRabbitMQTest {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendDirectMessage")
    public String sendDirectMessage(){
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
        return "OK";
    }

}

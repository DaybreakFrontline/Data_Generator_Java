package com.test.dataFlush;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ServletComponentScan("com.test.yuxi.**")
@EnableScheduling   // 开启计划任务
@SpringBootApplication
public class YuxiApplication {

    public static void main(String[] args) {
        SpringApplication.run(YuxiApplication.class, args);
    }

}

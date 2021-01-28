package com.test.dataFlush;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan("com.test.yuxi.**")
// @MapperScan("com.test.yuxi.mapper")
@SpringBootApplication
public class YuxiApplication {

    public static void main(String[] args) {
        SpringApplication.run(YuxiApplication.class, args);
    }

}

package com.test.dataFlush.customAnnotation;

import com.test.dataFlush.peopleBase.pojo.UserBasic;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  测试自定义注解的控制层接口
 */
@RestController
@RequestMapping("/customAnnotation")
public class CustomAnnotationController {

    /**
     * @param userBasic
     * @return
     */
    @AnnotationLog("=====日治")
    @RequestMapping("/trialErrorFunction")
    public String trialErrorFunction(@RequestBody UserBasic userBasic){
        System.out.println("userBasic:" + userBasic);
        return "OK";
    }

}

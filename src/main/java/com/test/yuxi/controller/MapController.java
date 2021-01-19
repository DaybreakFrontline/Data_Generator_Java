package com.test.yuxi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@Slf4j  // 代替 Logger logger = LoggerFactory.getLogger(xxxx.class);
@Controller
@RequestMapping("/MapController")
public class MapController {

    @RequestMapping("/chinaMap")
    @ResponseBody
    public String chinaMap(){
        ModelAndView modelAndView = new ModelAndView();

        return "chinaMap";
    }



}

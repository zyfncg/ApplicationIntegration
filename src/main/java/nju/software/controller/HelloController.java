package nju.software.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ZhangYF on 2017/5/25.
 */
@Controller
@RequestMapping(value = "/hello")
public class HelloController {

    @RequestMapping(value = "/hello")
    public String say(){
        System.out.println("GET Info");
        return "index";
    }
}

package nju.software.controller;

import nju.software.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ZhangYF on 2017/5/25.
 */
@Controller
@RequestMapping(value = "/hello")
public class HelloController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/hello")
    public String say(ModelMap model){
        System.out.println("GET Info");
        model.addAttribute("id",studentService.getStudent(10001));
        return "index";
    }
}

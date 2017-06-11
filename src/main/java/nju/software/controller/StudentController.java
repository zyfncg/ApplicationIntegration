package nju.software.controller;

import nju.software.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by ZhangYF on 2017/6/4.
 */
@Controller
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/courses")
    @ResponseBody
    public String getAllCourses(HttpServletRequest request){
        int institutionId = Integer.parseInt(request.getParameter("institutionId"));
        return courseService.getAllCourse(institutionId);
    }

    @RequestMapping(value = "/studyCourses")
    @ResponseBody
    public String getStudyCourses(HttpServletRequest request){
        int institutionId = Integer.parseInt(request.getParameter("institutionId"));
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        return courseService.getStudyCourse(institutionId, studentId);
    }

    @RequestMapping(value = "/chooseCourse",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> chooseCourses(HttpServletRequest request){
        return null;
    }

    @RequestMapping(value = "/dropCourse",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> dropCourses(HttpServletRequest request){
        return null;
    }

}

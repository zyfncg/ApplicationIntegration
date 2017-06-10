package nju.software.controller;

import nju.software.model.*;
import nju.software.service.CourseService;
import nju.software.service.StudCourService;
import nju.software.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ZhangYF on 2017/6/4.
 */
@Controller
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudCourService studCourService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> login(String studentid,String password, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        Student student = studentService.getStudent(Integer.parseInt(studentid));
        if(password.equals(student.getPassword())){
            HttpSession session = request.getSession(false);
            if(null == session) {
                session = request.getSession(true);
            }
            session.setAttribute("studentid",studentid);
            map.put("msg", "true");
        }else{
            map.put("msg", "false");
        }
        return map;
    }
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> logout(HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();

        HttpSession session = request.getSession(false);
        if(null != session) {
            session.removeAttribute("studentid");
        }

        map.put("msg", "true");
        return map;
    }

    @RequestMapping(value = "/courses")
    @ResponseBody
    public ListBean getAllCourses(){
        List<Course>  list = courseService.getAllCourse();
        List<CourseBeanX> result = new ArrayList<>();
        for(Course course:list){
            result.add(new CourseBeanX(course));
        }
        ListBean listBean = new ListBean();
        listBean.setList(result);
        return listBean;
    }
    @RequestMapping(value = "/course")
    @ResponseBody
    public CourseBeanX getCourse(){
        List<Course>  list = courseService.getAllCourse();

        return new CourseBeanX(list.get(0));
    }

    @RequestMapping(value = "/studyCourses")
    @ResponseBody
    public List<CourseBean> getStudyCourses(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(null == session||session.getAttribute("studentid")==null){
            return null;
        }
        List<Course>  list = courseService.getStudyCourse(Integer.parseInt((String)session.getAttribute("studentid")));
        List<CourseBean> result = new ArrayList<>();
        for(Course course:list){
            result.add(new CourseBeanX(course));
        }
        return result;
    }

    @RequestMapping(value = "/chooseCourse",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> chooseCourses(HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        HttpSession session = request.getSession(false);
        if(null == session||session.getAttribute("studentid")==null){
            map.put("msg","false");
            return map;
        }
        int studentid = Integer.parseInt((String)session.getAttribute("studentid"));
        int courseid = Integer.parseInt(request.getParameter("courseid"));
        System.out.println(studentid+"  "+courseid);
        Student student = studentService.getStudent(studentid);
        Course course = courseService.getCourse(courseid);
        StudCour studCour = new StudCour();
        if(student!=null && course != null){
            studCour.setStudent(student);
            studCour.setCourse(course);
            if(studCourService.addStudCour(studCour)){
                map.put("msg","true");
            }else{
                map.put("msg","false");
            }
        }else{
            System.out.println("not found");
            map.put("msg","false");
        }

        return map;
    }

    @RequestMapping(value = "/dropCourse",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> dropCourses(HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        HttpSession session = request.getSession(false);
        if(null == session||session.getAttribute("studentid")==null){
            map.put("msg","false");
            return map;
        }
        int studentid = Integer.parseInt((String)session.getAttribute("studentid"));
        int courseid = Integer.parseInt(request.getParameter("courseid"));
        System.out.println(studentid+"  "+courseid);


        if(studCourService.deleteStudCour(studentid,courseid)){
            map.put("msg","true");
        }else{
            map.put("msg","false");
        }

        return map;
    }

}

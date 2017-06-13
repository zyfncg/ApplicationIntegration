package nju.software.controller;

import nju.software.model.ResultBean;
import nju.software.model.Selection;
import nju.software.model.statistic.CourseInfo;
import nju.software.model.statistic.ListBean;
import nju.software.service.CourseService;
import nju.software.util.XmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;
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

    @RequestMapping(value = "/courseStatistics")
    @ResponseBody
    public ListBean getCourseStatistics(HttpServletRequest request) {
        String idStr = request.getParameter("institutionIds");
        String[] ids = idStr.split(",");
        List<CourseInfo> infoList = new LinkedList<>();
        for (String id : ids) {
            Map<Integer, Integer> mp = courseService.statisticCourses(Integer.parseInt(id));
            for (Map.Entry<Integer, Integer> entry : mp.entrySet()) {

            }
        }
        return null;
    }

    @RequestMapping(value = "/studentStatistics")
    @ResponseBody
    public ListBean getStudentStatistics(HttpServletRequest request) {
        return null;
    }

    @RequestMapping(value = "/chooseCourse",method = RequestMethod.POST)
    @ResponseBody
    public String chooseCourses(HttpServletRequest request){
        boolean result = courseService.addCourse(
                Integer.parseInt(request.getParameter("institutionId")),
                Integer.parseInt(request.getParameter("studentId")),
                Integer.parseInt(request.getParameter("courseInstitution")),
                Integer.parseInt(request.getParameter("courseId"))
        );

        ResultBean bean;
        if (result) {
            bean = new ResultBean(true, "选课成功");
        }
        else {
            bean = new ResultBean(false, "选课失败");
        }
        return XmlUtil.convertToXml(bean);
    }

    @RequestMapping(value = "/dropCourse",method = RequestMethod.POST)
    @ResponseBody
    public String dropCourses(HttpServletRequest request) {
        boolean result = courseService.deleteCourse(
                Integer.parseInt(request.getParameter("institutionId")),
                Integer.parseInt(request.getParameter("studentId")),
                Integer.parseInt(request.getParameter("courseInstitution")),
                Integer.parseInt(request.getParameter("courseId"))
        );

        ResultBean bean;
        if (result) {
            bean = new ResultBean(true, "选课成功");
        }
        else {
            bean = new ResultBean(false, "选课失败");
        }
        return XmlUtil.convertToXml(bean);
    }

}

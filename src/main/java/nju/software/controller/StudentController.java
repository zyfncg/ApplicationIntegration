package nju.software.controller;

import nju.software.config.ServerConfig;
import nju.software.model.ResultBean;
import nju.software.model.Selection;
import nju.software.model.statistic.CourseInfo;
import nju.software.model.statistic.ListBean;
import nju.software.model.statistic.StudentInfo;
import nju.software.service.CourseService;
import nju.software.service.StatisticService;
import nju.software.util.XmlUtil;
import org.hibernate.type.IntegerType;
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

    @Autowired
    private StatisticService statisticService;

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
    public ListBean getCourseStatistics() {
        List<CourseInfo> remoteStat = statisticService.getCourseStatistics();
        List<CourseInfo> allStat = new LinkedList<>();
        List<Integer> institutionIds = ServerConfig.getAllInstitutionIds();
        for (int institutionId : institutionIds) {
            Map<Integer, Integer> mp = courseService.statisticCourses(institutionId);
            for (CourseInfo info : remoteStat) {
                if (info.getInstitution() == institutionId) {
                    int courseId = info.getCourseid();
                    int studentNum = info.getStudentNum();
                    if (mp.containsKey(courseId)) {
                        studentNum += mp.get(courseId);
                    }
                    allStat.add(new CourseInfo(
                            courseId, info.getName(),
                            institutionId, studentNum
                    ));
                }
            }
        }

        ListBean result = new ListBean();
        result.setList(allStat);
        return result;
    }

    @RequestMapping(value = "/studentStatistics")
    @ResponseBody
    public ListBean getStudentStatistics() {
        List<StudentInfo> remoteStat = statisticService.getStudentStatistics();
        List<StudentInfo> allStat = new LinkedList<>();
        List<Integer> institutionIds = ServerConfig.getAllInstitutionIds();
        for (int institutionId : institutionIds) {
            Map<Integer, Integer> mp = courseService.statisticStudents(institutionId);
            for (StudentInfo info : remoteStat) {
                if (info.getInstitution() == institutionId) {
                    int studentId = info.getStudentid();
                    int courseNum = info.getCourseNum();
                    if (mp.containsKey(studentId)) {
                        courseNum += mp.get(studentId);
                    }
                    allStat.add(new StudentInfo(
                            studentId, info.getName(),
                            institutionId, courseNum
                    ));
                }
            }
        }

        ListBean result = new ListBean();
        result.setList(allStat);
        return result;
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

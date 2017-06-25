package nju.software.webservice;

import nju.software.config.ServerConfig;
import nju.software.model.ResultBean;
import nju.software.model.statistic.CourseInfo;
import nju.software.model.statistic.ListBean;
import nju.software.model.statistic.StudentInfo;
import nju.software.service.CourseService;
import nju.software.service.StatisticService;
import nju.software.util.XmlUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by ZhangYF on 2017/6/26.
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class EDUIntegrationImpl implements EDUIntgerationWebService{

    @Autowired
    private CourseService courseService;

    @Autowired
    private StatisticService statisticService;

    @Override
    public String getAllCourses(int institutionId) {
        return courseService.getAllCourse(institutionId);
    }

    @Override
    public String getStudyCourses(int institutionId, int studentId) {
        return courseService.getStudyCourse(institutionId, studentId);
    }

    @Override
    public String getCourseStatistics() {
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
        return XmlUtil.convertToXml(result);
    }

    @Override
    public String getStudentStatistics() {
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
        return XmlUtil.convertToXml(result);
    }

    @Override
    public String chooseCourses(int institutionId, int studentId, int courseInstitution, int courseId) {
        boolean result = courseService.addCourse(institutionId,studentId,courseInstitution,courseId);

        ResultBean bean;
        if (result) {
            bean = new ResultBean(true, "选课成功");
        }
        else {
            bean = new ResultBean(false, "选课失败");
        }
        return XmlUtil.convertToXml(bean);
    }

    @Override
    public String dropCourses(int institutionId, int studentId, int courseInstitution, int courseId) {
        boolean result = courseService.deleteCourse(institutionId,studentId,courseInstitution,courseId);

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

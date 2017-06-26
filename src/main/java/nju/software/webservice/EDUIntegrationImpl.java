package nju.software.webservice;

import nju.software.model.ResultBean;
import nju.software.model.statistic.ListBean;
import nju.software.service.CourseService;
import nju.software.service.webservice.CourseWebService;
import nju.software.service.webservice.SelectWebService;
import nju.software.service.webservice.StatisticsWebService;
import nju.software.util.XmlUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by ZhangYF on 2017/6/26.
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class EDUIntegrationImpl implements EDUIntgerationWebService{

    @Autowired
    private CourseService courseService;

    @Autowired
    private SelectWebService selectWebService;

    @Override
    public String getAllCourses(int institutionId) {
        return CourseWebService.getAllCourses(institutionId);
    }

    @Override
    public String getStudyCourses(int institutionId, int studentId) {
        return selectWebService.getStudyCourses(institutionId, studentId);
    }

    @Override
    public String getCourseStatistics() {
        ListBean result = StatisticsWebService.getCourseStatistics();
        return XmlUtil.convertToXml(result);
    }

    @Override
    public String getStudentStatistics() {
        ListBean result = StatisticsWebService.getStudentStatistics();
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

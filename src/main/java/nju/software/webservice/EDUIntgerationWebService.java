package nju.software.webservice;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by ZhangYF on 2017/6/26.
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface EDUIntgerationWebService {

    public String getAllCourses(int institutionId);

    public String getStudyCourses(int institutionId, int studentId);

    public String getCourseStatistics();

    public String getStudentStatistics();

    public String chooseCourses(int institutionId, int studentId, int courseInstitution, int courseId);

    public String dropCourses(int institutionId, int studentId, int courseInstitution, int courseId);

}

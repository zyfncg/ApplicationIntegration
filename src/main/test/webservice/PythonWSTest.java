package webservice;

import nju.software.config.ServerConfig;
import nju.software.model.javadb.JavaDBCourseInfo;
import nju.software.model.javadb.JavaDBCourseList;
import nju.software.model.pythondb.PythonDBCourseInfo;
import nju.software.model.statistic.CourseInfo;
import nju.software.model.statistic.StudentInfo;
import nju.software.service.webservice.StatisticsWebService;
import nju.software.util.WebServiceUtil;
import org.apache.axiom.om.OMElement;
import org.apache.axis2.AxisFault;
import org.apache.axis2.databinding.utils.BeanUtil;
import org.apache.axis2.engine.DefaultObjectSupplier;

import java.util.*;

/**
 * Created by Anson Shaw on 2017/6/25.
 *
 * Python服务器Web Service测试
 */
public class PythonWSTest {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws AxisFault {
//        System.out.println(WebServiceUtil.invoke(
//                WebServiceUtil.pythonClient,
//                WebServiceUtil.crateMethodElement(
//                        ServerConfig.PYTHON_TARGET_NAMESPACE,
//                        ServerConfig.PYTHON_WS_COURSE_STAT_URL,
//                        null
//                )
//        ));
//
//        System.out.println(WebServiceUtil.invoke(
//                WebServiceUtil.pythonClient,
//                WebServiceUtil.crateMethodElement(
//                        ServerConfig.PYTHON_TARGET_NAMESPACE,
//                        ServerConfig.PYTHON_WS_STUDENT_STAT_URL,
//                        null
//                )
//        ));
//
//        System.out.println(WebServiceUtil.invoke(
//                WebServiceUtil.pythonClient,
//                WebServiceUtil.crateMethodElement(
//                        ServerConfig.PYTHON_TARGET_NAMESPACE,
//                        ServerConfig.PYTHON_WS_ALL_COURSE_URL,
//                        null
//                )
//        ));
//
//        Map<String, String> param = new HashMap<>();
//        param.put("idStr", "1,2,3");
//        System.out.println(WebServiceUtil.invoke(
//                WebServiceUtil.pythonClient,
//                WebServiceUtil.crateMethodElement(
//                        ServerConfig.PYTHON_TARGET_NAMESPACE,
//                        ServerConfig.PYTHON_WS_COURSE_BY_ID_URL,
//                        param
//                )
//        ));
//
//        List<CourseInfo> courseStat = StatisticsWebService.getCourseStatistics().getList();
//        for (CourseInfo info : courseStat) {
//            System.out.println(info.getName());
//        }
//
//        List<StudentInfo> studentStat = StatisticsWebService.getStudentStatistics().getList();
//        for (StudentInfo info : studentStat) {
//            System.out.println(info.getName());
//        }

        OMElement data = WebServiceUtil.invoke(
                WebServiceUtil.pythonClient,
                WebServiceUtil.crateMethodElement(
                        ServerConfig.PYTHON_TARGET_NAMESPACE,
                        ServerConfig.PYTHON_WS_ALL_COURSE_URL,
                        null
                )
        );

        JavaDBCourseList result = new JavaDBCourseList();
        result.setCourseList(new LinkedList<>());
        Iterator itr = data.getFirstElement().getChildElements();
        while (itr.hasNext()) {
            PythonDBCourseInfo info = (PythonDBCourseInfo) BeanUtil.processObject(
                    (OMElement) itr.next(), PythonDBCourseInfo.class, null,
                    false, new DefaultObjectSupplier(),null
            );
            result.getCourseList().add(info.toStandard().toJavaDBCourseInfo());
        }

        for (JavaDBCourseInfo info : result.getCourseList()) {
            System.out.println(info.getName());
        }
    }
}

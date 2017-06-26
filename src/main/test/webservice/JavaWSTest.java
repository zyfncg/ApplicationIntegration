package webservice;

import nju.software.config.ServerConfig;
import nju.software.model.Selection;
import nju.software.model.javadb.JavaDBCourseInfo;
import nju.software.model.javadb.JavaDBCourseList;
import nju.software.model.statistic.CourseInfo;
import nju.software.model.statistic.ListBean;
import nju.software.service.helper.SelectHelper;
import nju.software.util.WebServiceUtil;
import nju.software.util.XmlUtil;
import org.apache.axiom.om.OMElement;
import org.apache.axis2.AxisFault;
import org.apache.axis2.databinding.utils.BeanUtil;
import org.apache.axis2.engine.DefaultObjectSupplier;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Anson Shaw on 2017/6/26.
 *
 * Java Web Service 测试
 */
public class JavaWSTest {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws AxisFault {
//        OMElement data = WebServiceUtil.invoke(
//                WebServiceUtil.javaClient,
//                WebServiceUtil.crateMethodElement(
//                        ServerConfig.JAVA_TARGET_NAMESPACE,
//                        ServerConfig.JAVA_WS_COURSE_STAT_URL,
//                        null
//                )
//        );
//
//        ListBean list = XmlUtil.converyToJavaBean(
//                data.getFirstElement().getText(),
//                ListBean.class
//        );
//
//        List<CourseInfo> courseStat = list.getList();
//        for (CourseInfo info : courseStat) {
//            System.out.println(info.getName());
//        }

//        OMElement data = WebServiceUtil.invoke(
//                WebServiceUtil.javaClient,
//                WebServiceUtil.crateMethodElement(
//                        ServerConfig.JAVA_TARGET_NAMESPACE,
//                        ServerConfig.JAVA_WS_ALL_COURSE_URL,
//                        null
//                )
//        );
//
//        JavaDBCourseList list = XmlUtil.converyToJavaBean(
//                data.getFirstElement().getText(),
//                JavaDBCourseList.class
//        );
//
//        List<JavaDBCourseInfo> courseList = list.getCourseList();
//        for (JavaDBCourseInfo info : courseList) {
//            System.out.println(info.getName());
//        }

        List<Selection> selections = new LinkedList<>();
        selections.add(new Selection(
                141110001, 2,
                30001, 1
        ));
        Map<String, String> params = SelectHelper.getJavaParam(
                selections, ServerConfig.JAVA_WS_COURSE_REQUEST_KEY
        );
        OMElement data = WebServiceUtil.invoke(
                WebServiceUtil.javaClient,
                WebServiceUtil.crateMethodElement(
                        ServerConfig.JAVA_TARGET_NAMESPACE,
                        ServerConfig.JAVA_WS_COURSE_BY_ID_URL,
                        params
                )
        );

        JavaDBCourseList list = XmlUtil.converyToJavaBean(
                data.getFirstElement().getText(),
                JavaDBCourseList.class
        );

        List<JavaDBCourseInfo> courseList = list.getCourseList();
        for (JavaDBCourseInfo info : courseList) {
            System.out.println(info.getName());
        }
    }
}

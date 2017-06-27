package nju.software.service.webservice;

import nju.software.config.ServerConfig;
import nju.software.model.statistic.CourseInfo;
import nju.software.model.statistic.ListBean;
import nju.software.model.statistic.StudentInfo;
import nju.software.util.WebServiceUtil;
import nju.software.util.XmlUtil;
import org.apache.axiom.om.OMElement;
import org.apache.axis2.databinding.utils.BeanUtil;
import org.apache.axis2.engine.DefaultObjectSupplier;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Anson Shaw on 2017/6/25.
 *
 * 从Web Service获取统计数据
 */
@Service(value = "statisticsWebService")
public class StatisticsWebService {

    @SuppressWarnings("unchecked")
    public ListBean getCourseStatistics() {
        long start = System.currentTimeMillis();

        ListBean result = new ListBean();
        result.setList(new LinkedList<CourseInfo>());

        // 从Python Web Service服务器获得课程统计信息
        try {
            OMElement data = WebServiceUtil.invoke(
                    WebServiceUtil.pythonClient,
                    WebServiceUtil.crateMethodElement(
                            ServerConfig.PYTHON_TARGET_NAMESPACE,
                            ServerConfig.PYTHON_WS_COURSE_STAT_URL,
                            null
                    )
            );

            Iterator itr = data.getFirstElement().getChildElements();
            while (itr.hasNext()) {
                CourseInfo info = (CourseInfo) BeanUtil.processObject(
                        (OMElement) itr.next(), CourseInfo.class, null,
                        false, new DefaultObjectSupplier(),null
                );
                result.getList().add(info);
            }
        } catch (Exception e) {
            System.out.println("从Python Web Service服务器获得课程统计信息失败");
            e.printStackTrace();
        }

        // 从Java Web Service服务器获得课程统计信息
        try {
            OMElement data = WebServiceUtil.invoke(
                    WebServiceUtil.javaClient,
                    WebServiceUtil.crateMethodElement(
                            ServerConfig.JAVA_TARGET_NAMESPACE,
                            ServerConfig.JAVA_WS_COURSE_STAT_URL,
                            null
                    )
            );

            ListBean list = XmlUtil.converyToJavaBean(
                    data.getFirstElement().getText(),
                    ListBean.class
            );
            result.getList().addAll(list.getList());
        } catch (Exception e) {
            System.out.println("Java Web Service服务器获得课程统计信息失败");
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("调用课程统计信息时间：" + (end - start));
        return result;
    }

    @SuppressWarnings("unchecked")
    public ListBean getStudentStatistics() {
        long start = System.currentTimeMillis();

        ListBean result = new ListBean();
        result.setList(new LinkedList<StudentInfo>());

        // 从Python Web Service服务器获得学生统计信息
        try {
            OMElement data = WebServiceUtil.invoke(
                    WebServiceUtil.pythonClient,
                    WebServiceUtil.crateMethodElement(
                            ServerConfig.PYTHON_TARGET_NAMESPACE,
                            ServerConfig.PYTHON_WS_STUDENT_STAT_URL,
                            null
                    )
            );

            Iterator itr = data.getFirstElement().getChildElements();
            while (itr.hasNext()) {
                StudentInfo info = (StudentInfo) BeanUtil.processObject(
                        (OMElement) itr.next(), StudentInfo.class, null,
                        false, new DefaultObjectSupplier(),null
                );
                result.getList().add(info);
            }
        } catch (Exception e) {
            System.out.println("从Python Web Service服务器获得学生统计信息失败");
            e.printStackTrace();
        }

        // 从Java Web Service服务器获得学生统计信息
        try {
            OMElement data = WebServiceUtil.invoke(
                    WebServiceUtil.javaClient,
                    WebServiceUtil.crateMethodElement(
                            ServerConfig.JAVA_TARGET_NAMESPACE,
                            ServerConfig.JAVA_WS_STUDENT_STAT_URL,
                            null
                    )
            );

            ListBean list = XmlUtil.converyToJavaBean(
                    data.getFirstElement().getText(),
                    ListBean.class
            );
            result.getList().addAll(list.getList());
        } catch (Exception e) {
            System.out.println("Java Web Service服务器获得学生统计信息失败");
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("调用学生统计信息时间：" + (end - start));
        return result;
    }

}

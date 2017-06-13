package nju.software.service.helper;

import nju.software.config.ServerConfig;
import nju.software.model.statistic.CourseInfo;
import nju.software.model.statistic.ListBean;
import nju.software.model.statistic.StudentInfo;
import nju.software.util.HttpUtil;
import nju.software.util.XmlUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Anson Shaw on 2017/6/13.
 */
public class StatisticsHelper {

    public static List<CourseInfo> getRemoteCourseStatistics() {
        List<CourseInfo> result = new LinkedList<>();

        // 获得Java服务器的统计数据
        result.addAll(getStatistics(ServerConfig.JAVA_COURSE_STAT_URL));
        // 获得PHP服务器的统计数据
        result.addAll(getStatistics(ServerConfig.PHP_COURSE_STAT_URL));
        // 获得Python服务器的统计数据
        result.addAll(getStatistics(ServerConfig.PYTHON_COURSE_STAT_URL));

        return result;
    }

    public static List<StudentInfo> getRemoteStudentStatistics() {
        List<StudentInfo> result = new LinkedList<>();

        // 获得Java服务器的统计数据
        result.addAll(getStatistics(ServerConfig.JAVA_STUDENT_STAT_URL));
        // 获得PHP服务器的统计数据
        result.addAll(getStatistics(ServerConfig.PHP_STUDENT_STAT_URL));
        // 获得Python服务器的统计数据
        result.addAll(getStatistics(ServerConfig.PYTHON_STUDENT_STAT_URL));

        return result;
    }

    private static<T> List<T> getStatistics(String url) {
        String xml = HttpUtil.post(url, null);
//        System.out.println("------------------------------");
//        System.out.println(xml);
//        System.out.println("------------------------------");
        ListBean bean = XmlUtil.converyToJavaBean(xml, ListBean.class);
        if (bean.getList().get(0) instanceof StudentInfo) {
            System.out.println("Student List");
        }
        if (bean.getList().get(0) instanceof CourseInfo) {
            System.out.println("Course List");
        }
        return bean.getList();
    }
}

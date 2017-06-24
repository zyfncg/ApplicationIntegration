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

        // 获得Java服务器的课程统计数据
        try {
            result.addAll(getStatistics(ServerConfig.JAVA_COURSE_STAT_URL));
        }
        catch (Exception e) {
            System.out.println("从Java服务器获得课程统计信息出错");
            e.printStackTrace();
        }

        // 获得PHP服务器的课程统计数据
        try {
            result.addAll(getStatistics(ServerConfig.PHP_COURSE_STAT_URL));
        }
        catch (Exception e) {
            System.out.println("从PHP服务器获得课程统计信息出错");
            e.printStackTrace();
        }

        // 获得Python服务器的课程统计数据
        try {
            result.addAll(getStatistics(ServerConfig.PYTHON_COURSE_STAT_URL));
        }
        catch (Exception e) {
            System.out.println("从Python服务器获得课程统计信息出错");
            e.printStackTrace();
        }

        return result;
    }

    public static List<StudentInfo> getRemoteStudentStatistics() {
        List<StudentInfo> result = new LinkedList<>();

        // 获得Java服务器的学生统计数据
        try {
            result.addAll(getStatistics(ServerConfig.JAVA_STUDENT_STAT_URL));
        }
        catch (Exception e) {
            System.out.println("从Java服务器获得学生统计信息出错");
            e.printStackTrace();
        }

        // 获得PHP服务器的学生统计数据
        try {
            result.addAll(getStatistics(ServerConfig.PHP_STUDENT_STAT_URL));
        }
        catch (Exception e) {
            System.out.println("从PHP服务器获得学生统计信息出错");
            e.printStackTrace();
        }

        // 获得Python服务器的学生统计数据
        try {
            result.addAll(getStatistics(ServerConfig.PYTHON_STUDENT_STAT_URL));
        }
        catch (Exception e) {
            System.out.println("从Python服务器获得学生统计信息出错");
            e.printStackTrace();
        }

        return result;
    }

    private static<T> List<T> getStatistics(String url) {
        String xml = HttpUtil.post(url, null);
        ListBean bean = XmlUtil.converyToJavaBean(xml, ListBean.class);
        return bean.getList();
    }
}

package controller;

import nju.software.util.HttpUtil;

/**
 * Created by Anson Shaw on 2017/6/13.
 */
public class StatisticsTest {

    public static void main(String[] args) {
        String courseURL = "http://localhost:8088/student/courseStatistics";
        System.out.println("Course statistics:");
        System.out.println(HttpUtil.post(courseURL, null));

        String studentURL = "http://localhost:8088/student/studentStatistics";
        System.out.println("Student Statistics:");
        System.out.println(HttpUtil.post(studentURL, null));
    }
}

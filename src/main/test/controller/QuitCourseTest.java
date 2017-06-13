package controller;

import nju.software.util.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anson Shaw on 2017/6/13.
 *
 * 退课接口测试
 */
public class QuitCourseTest {

    public static void main(String[] args) {
        // URL
        String quitURL = "http://localhost:8080/student/dropCourse";

        // Java选Python
        Map<String, String> javaData = new HashMap<>();
        javaData.put("institutionId", "1");
        javaData.put("studentId", "30001");
        javaData.put("courseInstitution", "2");
        javaData.put("courseId", "1");
        System.out.println("Java Student drop Python course:");
        System.out.println(HttpUtil.post(quitURL, javaData));

        // Python选PHP
        Map<String, String> pyData = new HashMap<>();
        pyData.put("institutionId", "2");
        pyData.put("studentId", "141110001");
        pyData.put("courseInstitution", "3");
        pyData.put("courseId", "1");
        System.out.println("Python Student drop PHP course:");
        System.out.println(HttpUtil.post(quitURL, pyData));

        // PHP选Java
        Map<String, String> phpData = new HashMap<>();
        phpData.put("institutionId", "2");
        phpData.put("studentId", "141110001");
        phpData.put("courseInstitution", "1");
        phpData.put("courseId", "1");
        System.out.println("PHP Student drop Java course:");
        System.out.println(HttpUtil.post(quitURL, phpData));
    }
}

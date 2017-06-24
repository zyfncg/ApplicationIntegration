package controller;

import com.sun.corba.se.spi.activation.Server;
import nju.software.config.ServerConfig;
import nju.software.util.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anson Shaw on 2017/6/13.
 *
 * 选课接口的测试
 */
public class SelectCourseTest {

    public static void main(String[] args) {
        // URL
        String chooseURL = "http://localhost:8080/student/chooseCourse";

        // Java选Python
        Map<String, String> javaData = new HashMap<>();
        javaData.put("institutionId", "1");
        javaData.put("studentId", "30001");
        javaData.put("courseInstitution", "2");
        javaData.put("courseId", "1");
        System.out.println("Java Student select Python course:");
        System.out.println(HttpUtil.post(chooseURL, javaData));

        // Python选PHP
        Map<String, String> pyData = new HashMap<>();
        pyData.put("institutionId", "2");
        pyData.put("studentId", "141110001");
        pyData.put("courseInstitution", "3");
        pyData.put("courseId", "1");
        System.out.println("Python Student select PHP course:");
        System.out.println(HttpUtil.post(chooseURL, pyData));

        // PHP选Java
        Map<String, String> phpData = new HashMap<>();
        phpData.put("institutionId", "3");
        phpData.put("studentId", "1");
        phpData.put("courseInstitution", "1");
        phpData.put("courseId", "30001");
        System.out.println("PHP Student select Java course:");
        System.out.println(HttpUtil.post(chooseURL, phpData));

        String selectURL = "http://localhost:8088/student/studyCourses";

        // Java获取Python选课
        Map<String, String> javaSelect = new HashMap<>();
        javaSelect.put("institutionId", "1");
        javaSelect.put("studentId", "30001");
        System.out.println("Java student select:");
        System.out.println(HttpUtil.post(selectURL, javaSelect));

        // Python获取PHP选课
        Map<String, String> pySelect = new HashMap<>();
        pySelect.put("institutionId", "2");
        pySelect.put("studentId", "141110001");
        System.out.println("Python student select:");
        System.out.println(HttpUtil.post(selectURL, pySelect));

        // PHP获取Java选课
        Map<String, String> phpSelect = new HashMap<>();
        phpSelect.put("institutionId", "3");
        phpSelect.put("studentId", "1");
        System.out.println("PHP student select:");
        System.out.println(HttpUtil.post(selectURL, phpSelect));
    }
}

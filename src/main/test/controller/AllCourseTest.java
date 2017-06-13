package controller;

import nju.software.config.ServerConfig;
import nju.software.util.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anson Shaw on 2017/6/13.
 *
 * 获取所有课程接口测试
 */
public class AllCourseTest {

    public static void main(String[] args) {
        String url = "http://localhost:8088/student/courses";
        Map<String, String> javaData = new HashMap<>();
        javaData.put("institutionId", "1");
        System.out.println("Info except Java:");
        System.out.println(HttpUtil.post(url, javaData));

        Map<String, String> pythonData = new HashMap<>();
        pythonData.put("institutionId", "2");
        System.out.println("Info except Python:");
        System.out.println(HttpUtil.post(url, pythonData));

        Map<String, String> phpData = new HashMap<>();
        phpData.put("institutionId", "3");
        System.out.println("Info except PHP:");
        System.out.println(HttpUtil.post(url, phpData));
    }
}

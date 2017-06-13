package nju.software;

import nju.software.config.ServerConfig;
import nju.software.util.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anson Shaw on 2017/6/12.
 */
public class SelectTest {

    public static void main(String[] args) {
        Map<String, String> pyData = new HashMap<>();
        pyData.put("courseIds", "1,2,3,4,5");
        System.out.println("Python Courses:");
        System.out.println(HttpUtil.post(ServerConfig.PYTHON_COURSE_URL, pyData));

        Map<String, String> javaData = new HashMap<>();
        javaData.put("courseids", "30001,30002,30003");
        System.out.println("Java Courses:");
        System.out.println(HttpUtil.post(ServerConfig.JAVA_COURSE_URL, javaData));

        Map<String, String> phpData = new HashMap<>();
        phpData.put("action", "Statistic/coursesInfo");
        phpData.put("cids", "1,2,3,4,6");
        System.out.println("PHP Courses:");
        System.out.println(HttpUtil.post(ServerConfig.PHP_COURSE_URL, phpData));

//        Map<String, String> selectData = new HashMap<>();
//        selectData.put("institutionId", "2");
//        selectData.put("studentId", "141110001");
//        selectData.put("courseInstitution", "3");
//        selectData.put("courseId", "1");
//        System.out.println("Python Student select PHP course:");
//        System.out.println(HttpUtil.p);
    }
}

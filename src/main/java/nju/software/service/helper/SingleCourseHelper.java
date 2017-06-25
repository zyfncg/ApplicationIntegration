package nju.software.service.helper;

import nju.software.config.ServerConfig;
import nju.software.model.standard.StandardCourseInfo;
import nju.software.util.HttpUtil;
import nju.software.util.XmlUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anson Shaw on 2017/6/13.
 */
public class SingleCourseHelper {

    private static Map<Integer, SingleCourseXmlExtractor> handlerMap = new HashMap<>();

    static {
        // 从Java服务器获得课程信息
        handlerMap.put(ServerConfig.JAVA_DB_INSTITUTION, (courseId) ->
            getCourseInfo(
                    ServerConfig.JAVA_COURSE_URL,
                    ServerConfig.JAVA_COURSE_REQUEST_KEY, courseId
            )
        );

        // 从Python服务器获得课程信息
        handlerMap.put(ServerConfig.PYTHON_DB_INSTITUTION, (courseId) ->
            getCourseInfo(
                    ServerConfig.PYTHON_COURSE_URL,
                    ServerConfig.PYTHON_COURSE_REQUEST_KEY, courseId
            )
        );

        // 从PHP服务器获得课程信息
        handlerMap.put(ServerConfig.PHP_DB_INSTITUTION, (courseId) ->
            getCourseInfo(
                    ServerConfig.PHP_COURSE_URL,
                    ServerConfig.PHP_COURSE_REQUEST_KEY, courseId
            )
        );
    }

    private static StandardCourseInfo getCourseInfo(String url, String key, int courseId) {
        try {
            Map<String, String> data = new HashMap<>();
            data.put(key, String.valueOf(courseId));
            String xml = HttpUtil.post(url, data);
            return XmlUtil.converyToJavaBean(
                    xml, StandardCourseInfo.class
            );
        }
        catch (Exception e) {
            System.out.println("获得课程信息出错");
            e.printStackTrace();
            return new StandardCourseInfo();
        }
    }

    public static Map<Integer, SingleCourseXmlExtractor> getHandlerMap() {
        return handlerMap;
    }
}

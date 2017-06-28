package nju.software.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by Anson Shaw on 2017/6/12.
 *
 * 服务器的一些参数
 */
public class ServerConfig {

    private static Properties properties = new Properties();

    static {
        ClassLoader loader = ServerConfig.class.getClassLoader();
        InputStream inputStream = loader.getResourceAsStream("server.properties");

        try {
            properties.load(inputStream);
        } catch (IOException e) {
            System.out.println("读取服务器配置文件失败");
            e.printStackTrace();
        }
    }

    public static final int JAVA_DB_INSTITUTION = Integer.parseInt(
            (String) properties.get("java_db_institution")
    );

    public static final int PYTHON_DB_INSTITUTION = Integer.parseInt(
            (String) properties.get("python_db_institution")
    );

    public static final int PHP_DB_INSTITUTION = Integer.parseInt(
            (String) properties.get("php_db_institution")
    );

    private static final String JAVA_HOST = String.valueOf(
            properties.get("java_host")
    );

    private static final String PYTHON_HOST = String.valueOf(
            properties.get("python_host")
    );

    private static final String PHP_HOST = String.valueOf(
            properties.get("php_host")
    );

    public static final String JAVA_ALL_COURSE_URL = JAVA_HOST + String.valueOf(
            properties.get("java_all_course_url")
    );

    public static final String PYTHON_ALL_COURSE_URL = PYTHON_HOST + String.valueOf(
            properties.get("python_all_course_url")
    );

    public static final String PHP_ALL_COURSE_URL = PHP_HOST + String.valueOf(
            properties.get("php_all_course_url")
    );

    public static final String JAVA_COURSE_URL = JAVA_HOST + String.valueOf(
            properties.get("java_course_url")
    );

    public static final String PYTHON_COURSE_URL = PYTHON_HOST + String.valueOf(
            properties.get("python_course_url")
    );

    public static final String PHP_COURSE_URL = PHP_HOST + String.valueOf(
            properties.get("php_course_url")
    );

    public static final String JAVA_COURSE_STAT_URL = JAVA_HOST + String.valueOf(
            properties.get("java_course_stat_url")
    );

    public static final String PYTHON_COURSE_STAT_URL = PYTHON_HOST + String.valueOf(
            properties.get("python_course_stat_url")
    );

    public static final String PHP_COURSE_STAT_URL = PHP_HOST + String.valueOf(
            properties.get("php_course_stat_url")
    );

    public static final String JAVA_STUDENT_STAT_URL = JAVA_HOST + String.valueOf(
            properties.get("java_student_stat_url")
    );

    public static final String PYTHON_STUDENT_STAT_URL = PYTHON_HOST + String.valueOf(
            properties.get("python_student_stat_url")
    );

    public static final String PHP_STUDENT_STAT_URL = PHP_HOST + String.valueOf(
            properties.get("php_student_stat_url")
    );


    private static final String PYTHON_WS_HOST = String.valueOf(
            properties.get("python_ws_host")
    );

    public static final String PYTHON_WSDL_URL = PYTHON_WS_HOST + String.valueOf(
            properties.get("python_wsdl_url")
    );

    // 向PHP服务请求所有课程信息的参数
    public static Map<String, String> phpAllCourseParam = new HashMap<>();
    static {
        phpAllCourseParam.put("action", "Statistic/course_stat");
    }

    public static final String JAVA_COURSE_REQUEST_KEY = String.valueOf(
            properties.get("java_course_request_key")
    );

    public static final String PYTHON_COURSE_REQUEST_KEY = String.valueOf(
            properties.get("python_course_request_key")
    );

    public static final String PHP_COURSE_REQUEST_KEY = String.valueOf(
            properties.get("php_course_request_key")
    );

    public static final String PYTHON_TARGET_NAMESPACE = String.valueOf(
            properties.get("python_target_namespace")
    );

    public static final String PYTHON_WS_COURSE_REQUEST_KEY = String.valueOf(
            properties.get("python_ws_course_request_key")
    );

    public static final String PYTHON_WS_COURSE_STAT_URL = String.valueOf(
            properties.get("python_ws_course_stat_url")
    );

    public static final String PYTHON_WS_STUDENT_STAT_URL = String.valueOf(
            properties.get("python_ws_student_stat_url")
    );

    public static final String PYTHON_WS_ALL_COURSE_URL = String.valueOf(
            properties.get("python_ws_all_course_url")
    );

    public static final String PYTHON_WS_COURSE_BY_ID_URL = String.valueOf(
            properties.get("python_ws_course_by_id_url")
    );

    private static final String JAVA_WS_HOST = String.valueOf(
            properties.get("java_ws_host")
    );

    public static final String JAVA_WSDL_URL = JAVA_WS_HOST + String.valueOf(
            properties.get("java_wsdl_url")
    );

    public static final String JAVA_TARGET_NAMESPACE = String.valueOf(
            properties.get("java_target_namespace")
    );

    public static final String JAVA_WS_COURSE_REQUEST_KEY = String.valueOf(
            properties.get("java_ws_course_request_key")
    );

    public static final String JAVA_WS_COURSE_STAT_URL = String.valueOf(
            properties.get("java_ws_course_stat_url")
    );

    public static final String JAVA_WS_STUDENT_STAT_URL = String.valueOf(
            properties.get("java_ws_student_stat_url")
    );

    public static final String JAVA_WS_ALL_COURSE_URL = String.valueOf(
            properties.get("java_ws_all_course_url")
    );

    public static final String JAVA_WS_COURSE_BY_ID_URL = String.valueOf(
            properties.get("java_ws_course_by_id_url")
    );

    private static final String PHP_WS_HOST = String.valueOf(
            properties.get("php_ws_host")
    );

    public static final String PHP_WSDL_URL = PHP_WS_HOST + String.valueOf(
            properties.get("php_wsdl_url")
    );

    public static final String PHP_TARGET_NAMESPACE = String.valueOf(
            properties.get("php_target_namespace")
    );

    public static final String PHP_WS_COURSE_REQUEST_KEY = String.valueOf(
            properties.get("php_ws_course_request_key")
    );

    public static final String PHP_WS_ALL_COURSE_URL = String.valueOf(
            properties.get("php_ws_all_course_url")
    );

    public static final String PHP_WS_STUDENT_STAT_URL = String.valueOf(
            properties.get("php_ws_student_stat_url")
    );

    public static final String PHP_WS_COURSE_STAT_URL = String.valueOf(
            properties.get("php_ws_course_stat_url")
    );

    public static final String PHP_WS_COURSE_BY_ID_URL = String.valueOf(
            properties.get("php_ws_course_by_id_url")
    );

    public static List<Integer> getAllInstitutionIds() {
        List<Integer> ids = new LinkedList<>();
        ids.add(JAVA_DB_INSTITUTION);
        ids.add(PYTHON_DB_INSTITUTION);
        ids.add(PHP_DB_INSTITUTION);
        return ids;
    }
}

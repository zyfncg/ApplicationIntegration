package nju.software.config;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anson Shaw on 2017/6/12.
 *
 * 服务器的一些参数
 */
public class ServerConfig {

    public static final int JAVA_DB_INSTITUTION = 1;

    public static final int PYTHON_DB_INSTITUTION = 2;

    public static final int PHP_DB_INSTITUTION = 3;

    private static final String JAVA_HOST = "http://115.159.161.87:8080";

    private static final String PYTHON_HOST = "http://111.231.22.133";

    private static final String PHP_HOST = "http://118.89.192.189";

    public static final String JAVA_ALL_COURSE_URL = JAVA_HOST + "/edu_admin/integration/courses";

    public static final String PYTHON_ALL_COURSE_URL = PYTHON_HOST + "/getAllCourseInfo";

    public static final String PHP_ALL_COURSE_URL = PHP_HOST + "/Controller.php";

    public static final String JAVA_COURSE_URL = JAVA_HOST + "/edu_admin/integration/coursesInfo";

    public static final String PYTHON_COURSE_URL = PYTHON_HOST + "/getCourseByIds";

    public static final String PHP_COURSE_URL = PHP_HOST + "/Controller.php";

    // 向PHP服务请求所有课程信息的参数
    public static Map<String, String> phpAllCourseParam = new HashMap<>();
    static {
        phpAllCourseParam.put("action", "Statistic/course_stat");
    }
}

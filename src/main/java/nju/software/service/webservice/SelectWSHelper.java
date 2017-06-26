package nju.software.service.webservice;

import nju.software.config.ServerConfig;
import nju.software.model.Selection;
import nju.software.model.javadb.JavaDBCourseList;
import nju.software.model.phpdb.PHPDBCourseList;
import nju.software.model.pythondb.PythonDBCourseList;
import nju.software.service.helper.SelectHelper;
import nju.software.service.helper.SelectXmlGenerator;
import nju.software.util.XmlUtil;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Anson Shaw on 2017/6/26.
 *
 * 从其他Web Service服务器根据课程ID获得课程信息
 */
class SelectWSHelper {

    private static Map<Integer, SelectXmlGenerator> xmlHandler = new HashMap<>();

    static {
        // 处理来自Java服务器的请求
        xmlHandler.put(ServerConfig.JAVA_DB_INSTITUTION, (selections) -> {
            JavaDBCourseList result = new JavaDBCourseList();
            result.setCourseList(new LinkedList<>());

            result.getCourseList().addAll(
                    getPythonWSSelectList(selections).toStandard().toJavaList().getCourseList()
            );

            return XmlUtil.convertToXml(result);
        });

        // 处理来自Python服务器的请求
        xmlHandler.put(ServerConfig.PYTHON_DB_INSTITUTION, (selections) -> {
            PythonDBCourseList result = new PythonDBCourseList();
            result.setCourseList(new LinkedList<>());

            result.getCourseList().addAll(
                    getJavaWSSelectList(selections).toStandard().toPythonList().getCourseList()
            );

            return XmlUtil.convertToXml(result);
        });

        // 处理来自PHP服务器的请求
        xmlHandler.put(ServerConfig.PHP_DB_INSTITUTION, (selections) -> {
            PHPDBCourseList result = new PHPDBCourseList();
            result.setCourseList(new LinkedList<>());

            result.getCourseList().addAll(
                    getPythonWSSelectList(selections).toStandard().toPHPList().getCourseList()
            );

            result.getCourseList().addAll(
                    getJavaWSSelectList(selections).toStandard().toPHPList().getCourseList()
            );

            return XmlUtil.convertToXml(result);
        });
    }

    static Map<Integer, SelectXmlGenerator> getHandlerMap() {
        return xmlHandler;
    }

    private static PythonDBCourseList getPythonWSSelectList(List<Selection> selections) {
        Map<String, String> pyData = SelectHelper.getPythonParam(
                selections, ServerConfig.PYTHON_WS_COURSE_REQUEST_KEY
        );
        if (!pyData.containsKey(ServerConfig.PYTHON_WS_COURSE_REQUEST_KEY)) {
            PythonDBCourseList result = new PythonDBCourseList();
            result.setCourseList(new LinkedList<>());
            return result;
        }
        return CourseWSHelper.getPythonWSCourseList(
                ServerConfig.PYTHON_WS_COURSE_BY_ID_URL, pyData
        );
    }

    private static JavaDBCourseList getJavaWSSelectList(List<Selection> selections) {
        Map<String, String> javaData = SelectHelper.getJavaParam(
                selections, ServerConfig.JAVA_WS_COURSE_REQUEST_KEY
        );
        if (!javaData.containsKey(ServerConfig.JAVA_WS_COURSE_REQUEST_KEY)) {
            JavaDBCourseList result = new JavaDBCourseList();
            result.setCourseList(new LinkedList<>());
            return result;
        }
        return CourseWSHelper.getJavaWSCourseList(
                ServerConfig.JAVA_WS_COURSE_BY_ID_URL, javaData
        );
    }
}

package nju.software.service.helper;

import nju.software.config.ServerConfig;
import nju.software.model.javadb.JavaDBCourseInfo;
import nju.software.model.javadb.JavaDBCourseList;
import nju.software.model.phpdb.PHPDBCourseInfo;
import nju.software.model.phpdb.PHPDBCourseList;
import nju.software.model.pythondb.PythonDBCourseInfo;
import nju.software.model.pythondb.PythonDBCourseList;
import nju.software.model.pythondb.PythonDBCourseResult;
import nju.software.util.HttpUtil;
import nju.software.util.XmlUtil;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Anson Shaw on 2017/6/12.
 *
 * 处理与所有课程信息请求有关的逻辑
 */
public class AllCourseHelper {

    private static Map<Integer, AllCourseXmlGenerator> handlerMap = new HashMap<>();

    static {
        // 处理来自Java服务器的请求,获取其它服务器的全部课程信息
        handlerMap.put(ServerConfig.JAVA_DB_INSTITUTION, () -> {
            JavaDBCourseList result = new JavaDBCourseList();
            result.setCourseList(new LinkedList<>());

            String pythonXml = HttpUtil.post(ServerConfig.PYTHON_ALL_COURSE_URL, null);
            PythonDBCourseResult pyList = XmlUtil.converyToJavaBean(
                    pythonXml, PythonDBCourseResult.class
            );
            if (pyList != null && pyList.getList() != null) {
                for (PythonDBCourseInfo info : pyList.getList().getCourseList()) {
                    result.getCourseList().add(info.toStandard().toJavaDBCourseInfo());
                }
            }

            String phpXml = HttpUtil.post(ServerConfig.PHP_ALL_COURSE_URL, ServerConfig.phpAllCourseParam);
            PHPDBCourseList phpList = XmlUtil.converyToJavaBean(
                    phpXml, PHPDBCourseList.class
            );
            if (pyList != null) {
                for (PHPDBCourseInfo info : phpList.getCourseList()) {
                    result.getCourseList().add(info.toStandard().toJavaDBCourseInfo());
                }
            }

            return XmlUtil.convertToXml(result);
        });

        // 处理来自Python服务器的请求,获取其它服务器的全部课程信息
        handlerMap.put(ServerConfig.PYTHON_DB_INSTITUTION, () -> {
            PythonDBCourseList result = new PythonDBCourseList();
            result.setCourseList(new LinkedList<>());

            String javaXml = HttpUtil.post(ServerConfig.JAVA_ALL_COURSE_URL, null);
            JavaDBCourseList javaList = XmlUtil.converyToJavaBean(
                    javaXml, JavaDBCourseList.class
            );
            if (javaList != null) {
                for (JavaDBCourseInfo info : javaList.getCourseList()) {
                    result.getCourseList().add(info.toStandard().toPythonDBCourseInfo());
                }
            }

            String phpXml = HttpUtil.post(ServerConfig.PHP_ALL_COURSE_URL, ServerConfig.phpAllCourseParam);
            PHPDBCourseList phpList = XmlUtil.converyToJavaBean(
                    phpXml, PHPDBCourseList.class
            );
            if (phpList != null) {
                for (PHPDBCourseInfo info : phpList.getCourseList()) {
                    result.getCourseList().add(info.toStandard().toPythonDBCourseInfo());
                }
            }

            return XmlUtil.convertToXml(result);
        });

        // 处理来自PHP服务器的请求,获取其它服务器的全部课程信息
        handlerMap.put(ServerConfig.PHP_DB_INSTITUTION, () -> {
            PHPDBCourseList result = new PHPDBCourseList();
            result.setCourseList(new LinkedList<>());

            String pythonXml = HttpUtil.post(ServerConfig.PYTHON_ALL_COURSE_URL, null);
            PythonDBCourseResult pyList = XmlUtil.converyToJavaBean(
                    pythonXml, PythonDBCourseResult.class
            );
            if (pyList != null && pyList.getList() != null) {
                for (PythonDBCourseInfo info : pyList.getList().getCourseList()) {
                    result.getCourseList().add(info.toStandard().toPHPDBCourseInfo());
                }
            }

            String javaXml = HttpUtil.post(ServerConfig.JAVA_ALL_COURSE_URL, null);
            JavaDBCourseList javaList = XmlUtil.converyToJavaBean(
                    javaXml, JavaDBCourseList.class
            );
            if (javaList != null) {
                for (JavaDBCourseInfo info : javaList.getCourseList()) {
                    result.getCourseList().add(info.toStandard().toPHPDBCourseInfo());
                }
            }

            return XmlUtil.convertToXml(result);
        });
    }

    public static Map<Integer, AllCourseXmlGenerator> getHandlerMap() {
        return handlerMap;
    }

}

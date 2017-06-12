package nju.software.service;

import nju.software.config.ServerConfig;
import nju.software.model.javadb.JavaDBCourseInfo;
import nju.software.model.javadb.JavaDBCourseList;
import nju.software.model.phpdb.PHPDBCourseInfo;
import nju.software.model.phpdb.PHPDBCourseList;
import nju.software.model.pythondb.*;
import nju.software.util.HttpUtil;
import nju.software.util.XmlUtil;

import java.util.*;

/**
 * Created by Anson Shaw on 2017/6/12.
 *
 * 处理选课信息请求
 */
class SelectHelper {

    private static Map<Integer, SelectXmlExtractor> xmlHandler = new HashMap<>();

    static {
        // 处理来自Java服务器的请求
        xmlHandler.put(ServerConfig.JAVA_DB_INSTITUTION, (ids) -> {
            JavaDBCourseList result = new JavaDBCourseList();
            result.setCourseList(new LinkedList<>());

            Map<String, String> pyData = getPythonParam(ids);
            String pythonXml = HttpUtil.post(ServerConfig.PYTHON_COURSE_URL, pyData);
            PythonDBCourseResult pyList = XmlUtil.converyToJavaBean(
                    pythonXml, PythonDBCourseResult.class
            );
            for (PythonDBCourseInfo info : pyList.getList().getCourseList()) {
                result.getCourseList().add(info.toStandard().toJavaDBCourseInfo());
            }

            Map<String, String> phpData = getPHPParam(ids);
            String phpXml = HttpUtil.post(ServerConfig.PHP_COURSE_URL, phpData);
            PHPDBCourseList phpList = XmlUtil.converyToJavaBean(
                    phpXml, PHPDBCourseList.class
            );
            for (PHPDBCourseInfo info : phpList.getCourseList()) {
                result.getCourseList().add(info.toStandard().toJavaDBCourseInfo());
            }

            return XmlUtil.convertToXml(result);
        });

        // 处理来自Python服务器的请求
        xmlHandler.put(ServerConfig.PYTHON_DB_INSTITUTION, (ids) -> {
            PythonDBCourseList result = new PythonDBCourseList();
            result.setCourseList(new LinkedList<>());

            Map<String, String> javaData = getJavaParam(ids);
            String javaXml = HttpUtil.post(ServerConfig.JAVA_COURSE_URL, javaData);
            JavaDBCourseList javaList = XmlUtil.converyToJavaBean(
                    javaXml, JavaDBCourseList.class
            );
            for (JavaDBCourseInfo info : javaList.getCourseList()) {
                result.getCourseList().add(info.toStandard().toPythonDBCourseInfo());
            }

            Map<String, String> phpData = getPHPParam(ids);
            String phpXml = HttpUtil.post(ServerConfig.PHP_COURSE_URL, phpData);
            PHPDBCourseList phpList = XmlUtil.converyToJavaBean(
                    phpXml, PHPDBCourseList.class
            );
            for (PHPDBCourseInfo info : phpList.getCourseList()) {
                result.getCourseList().add(info.toStandard().toPythonDBCourseInfo());
            }

            return XmlUtil.convertToXml(result);
        });

        // 处理来自PHP服务器的请求
        xmlHandler.put(ServerConfig.PHP_DB_INSTITUTION, (ids) -> {
            PHPDBCourseList result = new PHPDBCourseList();
            result.setCourseList(new LinkedList<>());

            Map<String, String> pyData = getPythonParam(ids);
            String pythonXml = HttpUtil.post(ServerConfig.PYTHON_COURSE_URL, pyData);
            PythonDBCourseResult pyList = XmlUtil.converyToJavaBean(
                    pythonXml, PythonDBCourseResult.class
            );
            for (PythonDBCourseInfo info : pyList.getList().getCourseList()) {
                result.getCourseList().add(info.toStandard().toPHPDBCourseInfo());
            }

            Map<String, String> javaData = getJavaParam(ids);
            String javaXml = HttpUtil.post(ServerConfig.JAVA_COURSE_URL, javaData);
            JavaDBCourseList javaList = XmlUtil.converyToJavaBean(
                    javaXml, JavaDBCourseList.class
            );
            for (JavaDBCourseInfo info : javaList.getCourseList()) {
                result.getCourseList().add(info.toStandard().toPHPDBCourseInfo());
            }

            return XmlUtil.convertToXml(result);
        });
    }

    static Map<Integer, SelectXmlExtractor> getHandlerMap() {
        return xmlHandler;
    }

    private static Map<String, String> getPythonParam(List<String> ids) {
        return paramHelper(ids, "courseIds");
    }

    private static Map<String, String> getPHPParam(List<String> ids) {
        Map<String, String> result = paramHelper(ids, "cids");
        result.put("action", "Statistic/coursesInfo");
        return result;
    }

    private static Map<String, String> getJavaParam(List<String> ids) {
        return paramHelper(ids, "courseids");
    }

    private static Map<String, String> paramHelper(List<String> ids, String key) {
        if (ids == null || ids.size() == 0) {
            return new HashMap<>();
        }

        Map<String, String> result = new HashMap<>();
        StringBuilder idStr = new StringBuilder();
        for (String id : ids) {
            idStr.append(id).append(",");
        }

        result.put(key, idStr.substring(0, idStr.length() - 1));
        return result;
    }
}



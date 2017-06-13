package nju.software.service;

import nju.software.config.ServerConfig;
import nju.software.model.Selection;
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
        xmlHandler.put(ServerConfig.JAVA_DB_INSTITUTION, (selections) -> {
            JavaDBCourseList result = new JavaDBCourseList();
            result.setCourseList(new LinkedList<>());

            Map<String, String> pyData = getPythonParam(selections);
            if (pyData != null) {
                String pythonXml = HttpUtil.post(ServerConfig.PYTHON_COURSE_URL, pyData);
                PythonDBCourseResult pyList = XmlUtil.converyToJavaBean(
                        pythonXml, PythonDBCourseResult.class
                );
                if (pyList != null && pyList.getList() != null
                        && pyList.getList().getCourseList() != null) {
                    for (PythonDBCourseInfo info : pyList.getList().getCourseList()) {
                        result.getCourseList().add(info.toStandard().toJavaDBCourseInfo());
                    }
                }
            }

            Map<String, String> phpData = getPHPParam(selections);
            if (phpData != null) {
                String phpXml = HttpUtil.post(ServerConfig.PHP_COURSE_URL, phpData);
                PHPDBCourseList phpList = XmlUtil.converyToJavaBean(
                        phpXml, PHPDBCourseList.class
                );
                if (phpList != null && phpList.getCourseList() != null) {
                    for (PHPDBCourseInfo info : phpList.getCourseList()) {
                        result.getCourseList().add(info.toStandard().toJavaDBCourseInfo());
                    }
                }
            }

            return XmlUtil.convertToXml(result);
        });

        // 处理来自Python服务器的请求
        xmlHandler.put(ServerConfig.PYTHON_DB_INSTITUTION, (selections) -> {
            PythonDBCourseList result = new PythonDBCourseList();
            result.setCourseList(new LinkedList<>());

            Map<String, String> javaData = getJavaParam(selections);
            if (javaData != null) {
                String javaXml = HttpUtil.post(ServerConfig.JAVA_COURSE_URL, javaData);
                JavaDBCourseList javaList = XmlUtil.converyToJavaBean(
                        javaXml, JavaDBCourseList.class
                );
                if (javaList != null && javaList.getCourseList() != null) {
                    for (JavaDBCourseInfo info : javaList.getCourseList()) {
                        result.getCourseList().add(info.toStandard().toPythonDBCourseInfo());
                    }
                }
            }

            Map<String, String> phpData = getPHPParam(selections);
            if (phpData != null) {
                System.out.println(phpData.get("cids"));
                String phpXml = HttpUtil.post(ServerConfig.PHP_COURSE_URL, phpData);
                PHPDBCourseList phpList = XmlUtil.converyToJavaBean(
                        phpXml, PHPDBCourseList.class
                );
                if (phpList != null && phpList.getCourseList() != null) {
                    for (PHPDBCourseInfo info : phpList.getCourseList()) {
                        result.getCourseList().add(info.toStandard().toPythonDBCourseInfo());
                    }
                }
            }

            return XmlUtil.convertToXml(result);
        });

        // 处理来自PHP服务器的请求
        xmlHandler.put(ServerConfig.PHP_DB_INSTITUTION, (selections) -> {
            PHPDBCourseList result = new PHPDBCourseList();
            result.setCourseList(new LinkedList<>());

            Map<String, String> pyData = getPythonParam(selections);
            if (pyData != null) {
                String pythonXml = HttpUtil.post(ServerConfig.PYTHON_COURSE_URL, pyData);
                PythonDBCourseResult pyList = XmlUtil.converyToJavaBean(
                        pythonXml, PythonDBCourseResult.class
                );
                if (pyList != null && pyList.getList() != null
                        && pyList.getList().getCourseList() != null) {
                    for (PythonDBCourseInfo info : pyList.getList().getCourseList()) {
                        result.getCourseList().add(info.toStandard().toPHPDBCourseInfo());
                    }
                }
            }

            Map<String, String> javaData = getJavaParam(selections);
            if (javaData != null) {
                String javaXml = HttpUtil.post(ServerConfig.JAVA_COURSE_URL, javaData);
                JavaDBCourseList javaList = XmlUtil.converyToJavaBean(
                        javaXml, JavaDBCourseList.class
                );
                if (javaList != null && javaList.getCourseList() != null) {
                    for (JavaDBCourseInfo info : javaList.getCourseList()) {
                        result.getCourseList().add(info.toStandard().toPHPDBCourseInfo());
                    }
                }
            }

            return XmlUtil.convertToXml(result);
        });
    }

    static Map<Integer, SelectXmlExtractor> getHandlerMap() {
        return xmlHandler;
    }

    private static Map<String, String> getPythonParam(List<Selection> selections) {
        List<String> ids = new LinkedList<>();
        for (Selection s : selections) {
            if (s.getCourseInstitution() == ServerConfig.PYTHON_DB_INSTITUTION) {
                ids.add(String.valueOf(s.getCourseid()));
            }
        }
        return paramHelper(ids, "courseIds");
    }

    private static Map<String, String> getPHPParam(List<Selection> selections) {
        List<String> ids = new LinkedList<>();
        for (Selection s : selections) {
            if (s.getCourseInstitution() == ServerConfig.PHP_DB_INSTITUTION) {
                ids.add(String.valueOf(s.getCourseid()));
            }
        }

        Map<String, String> result = paramHelper(ids, "cids");
        if (result != null) {
            result.put("action", "Statistic/coursesInfo");
        }
        return result;
    }

    private static Map<String, String> getJavaParam(List<Selection> selections) {
        List<String> ids = new LinkedList<>();
        for (Selection s : selections) {
            if (s.getCourseInstitution() == ServerConfig.JAVA_DB_INSTITUTION) {
                ids.add(String.valueOf(s.getCourseid()));
            }
        }
        return paramHelper(ids, "courseids");
    }

    private static Map<String, String> paramHelper(List<String> ids, String key) {
        if (ids == null || ids.size() == 0) {
            return null;
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



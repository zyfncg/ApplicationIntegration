package nju.software.service.helper;

import nju.software.config.ServerConfig;
import nju.software.model.Selection;
import nju.software.model.javadb.JavaDBCourseList;
import nju.software.model.phpdb.PHPDBCourseList;
import nju.software.model.pythondb.PythonDBCourseList;
import nju.software.model.pythondb.PythonDBCourseResult;
import nju.software.util.HttpUtil;
import nju.software.util.XmlUtil;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Anson Shaw on 2017/6/12.
 *
 * 处理选课信息请求
 */
public class SelectHelper {

    private static Map<Integer, SelectXmlGenerator> xmlHandler = new HashMap<>();

    static {
        // 处理来自Java服务器的请求
        xmlHandler.put(ServerConfig.JAVA_DB_INSTITUTION, (selections) -> {
            JavaDBCourseList result = new JavaDBCourseList();
            result.setCourseList(new LinkedList<>());

            result.getCourseList().addAll(
                    getPythonSelectList(selections).toStandard().toJavaList().getCourseList()
            );

            result.getCourseList().addAll(
                    getPHPSelectList(selections).toStandard().toJavaList().getCourseList()
            );

            return XmlUtil.convertToXml(result);
        });

        // 处理来自Python服务器的请求
        xmlHandler.put(ServerConfig.PYTHON_DB_INSTITUTION, (selections) -> {
            PythonDBCourseList result = new PythonDBCourseList();
            result.setCourseList(new LinkedList<>());

            result.getCourseList().addAll(
                    getJavaSelectList(selections).toStandard().toPythonList().getCourseList()
            );

            result.getCourseList().addAll(
                    getPHPSelectList(selections).toStandard().toPythonList().getCourseList()
            );

            return XmlUtil.convertToXml(result);
        });

        // 处理来自PHP服务器的请求
        xmlHandler.put(ServerConfig.PHP_DB_INSTITUTION, (selections) -> {
            PHPDBCourseList result = new PHPDBCourseList();
            result.setCourseList(new LinkedList<>());

            result.getCourseList().addAll(
                    getPythonSelectList(selections).toStandard().toPHPList().getCourseList()
            );

            result.getCourseList().addAll(
                    getJavaSelectList(selections).toStandard().toPHPList().getCourseList()
            );

            return XmlUtil.convertToXml(result);
        });
    }

    public static Map<Integer, SelectXmlGenerator> getHandlerMap() {
        return xmlHandler;
    }

    private static PythonDBCourseList getPythonSelectList(List<Selection> selections) {
        PythonDBCourseList result = new PythonDBCourseList();
        result.setCourseList(new LinkedList<>());

        Map<String, String> pyData = getPythonParam(
                selections, ServerConfig.PYTHON_COURSE_REQUEST_KEY
        );
        if (pyData != null) {
            try {
                String pythonXml = HttpUtil.post(ServerConfig.PYTHON_COURSE_URL, pyData);
                PythonDBCourseResult pyList = XmlUtil.converyToJavaBean(
                        pythonXml, PythonDBCourseResult.class
                );
                if (pyList != null && pyList.getList() != null
                        && pyList.getList().getCourseList() != null) {
                    return pyList.getList();
                }
            }
            catch (Exception e) {
                System.out.println("从python服务器获得课程信息出错");
                e.printStackTrace();
            }
        }

        return result;
    }

    private static JavaDBCourseList getJavaSelectList(List<Selection> selections) {
        JavaDBCourseList result = new JavaDBCourseList();
        result.setCourseList(new LinkedList<>());

        Map<String, String> javaData = getJavaParam(
                selections, ServerConfig.JAVA_COURSE_REQUEST_KEY
        );
        if (javaData != null) {
            try {
                String javaXml = HttpUtil.post(ServerConfig.JAVA_COURSE_URL, javaData);
                JavaDBCourseList javaList = XmlUtil.converyToJavaBean(
                        javaXml, JavaDBCourseList.class
                );
                if (javaList != null && javaList.getCourseList() != null) {
                    return javaList;
                }
            }
            catch (Exception e) {
                System.out.println("从Java服务器获得课程信息出错");
                e.printStackTrace();
            }
        }

        return result;
    }

    private static PHPDBCourseList getPHPSelectList(List<Selection> selections) {
        PHPDBCourseList result = new PHPDBCourseList();
        result.setCourseList(new LinkedList<>());

        Map<String, String> phpData = getPHPParam(
                selections, ServerConfig.PHP_COURSE_REQUEST_KEY
        );
        if (phpData != null) {
            try {
                String phpXml = HttpUtil.post(ServerConfig.PHP_COURSE_URL, phpData);
                PHPDBCourseList phpList = XmlUtil.converyToJavaBean(
                        phpXml, PHPDBCourseList.class
                );
                if (phpList != null && phpList.getCourseList() != null) {
                    return phpList;
                }
            }
            catch (Exception e) {
                System.out.println("从PHP服务器获得课程信息出错");
                e.printStackTrace();
            }
        }

        return result;
    }

    public static Map<String, String> getPythonParam(
            List<Selection> selections, String requestKey
    ) {
        List<String> ids = extractIds(selections, ServerConfig.PYTHON_DB_INSTITUTION);
        return paramHelper(ids, requestKey);
    }

    private static Map<String, String> getPHPParam(
            List<Selection> selections, String requestKey
    ) {
        List<String> ids = extractIds(selections, ServerConfig.PHP_DB_INSTITUTION);
        Map<String, String> result = paramHelper(ids, requestKey);
        if (result != null) {
            result.put("action", "Statistic/coursesInfo");
        }
        return result;
    }

    public static Map<String, String> getJavaParam(
            List<Selection> selections, String requestKey
    ) {
        List<String> ids = extractIds(selections, ServerConfig.JAVA_DB_INSTITUTION);
        return paramHelper(ids, requestKey);
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

    private static List<String> extractIds(List<Selection> selections, int institutionId) {
        List<String> ids = new LinkedList<>();
        for (Selection s : selections) {
            if (s.getCourseInstitution() == institutionId) {
                ids.add(String.valueOf(s.getCourseid()));
            }
        }
        return ids;
    }
}



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

            result.getCourseList().addAll(
                    getPythonCourseList().toStandard().toJavaList().getCourseList()
            );

            result.getCourseList().addAll(
                    getPHPCourseList().toStandard().toJavaList().getCourseList()
            );

            return XmlUtil.convertToXml(result);
        });

        // 处理来自Python服务器的请求,获取其它服务器的全部课程信息
        handlerMap.put(ServerConfig.PYTHON_DB_INSTITUTION, () -> {
            PythonDBCourseList result = new PythonDBCourseList();
            result.setCourseList(new LinkedList<>());

            result.getCourseList().addAll(
                    getJavaCourseList().toStandard().toPythonList().getCourseList()
            );

            result.getCourseList().addAll(
                    getPHPCourseList().toStandard().toPythonList().getCourseList()
            );

            return XmlUtil.convertToXml(result);
        });

        // 处理来自PHP服务器的请求,获取其它服务器的全部课程信息
        handlerMap.put(ServerConfig.PHP_DB_INSTITUTION, () -> {
            PHPDBCourseList result = new PHPDBCourseList();
            result.setCourseList(new LinkedList<>());

            result.getCourseList().addAll(
                    getPythonCourseList().toStandard().toPHPList().getCourseList()
            );

            result.getCourseList().addAll(
                    getJavaCourseList().toStandard().toPHPList().getCourseList()
            );

            return XmlUtil.convertToXml(result);
        });
    }

    public static Map<Integer, AllCourseXmlGenerator> getHandlerMap() {
        return handlerMap;
    }

    private static PythonDBCourseList getPythonCourseList() {
        PythonDBCourseList result = new PythonDBCourseList();
        result.setCourseList(new LinkedList<>());

        try {
            String pythonXml = HttpUtil.post(ServerConfig.PYTHON_ALL_COURSE_URL, null);
            PythonDBCourseResult pyList = XmlUtil.converyToJavaBean(
                    pythonXml, PythonDBCourseResult.class
            );
            if (pyList != null && pyList.getList() != null) {
                return pyList.getList();
            }
        }
        catch (Exception e) {
            System.out.println("从Python服务器获得课程信息出错");
            e.printStackTrace();
        }

        return result;
    }

    private static JavaDBCourseList getJavaCourseList() {
        JavaDBCourseList result = new JavaDBCourseList();
        result.setCourseList(new LinkedList<>());

        try {
            String javaXml = HttpUtil.post(ServerConfig.JAVA_ALL_COURSE_URL, null);
            JavaDBCourseList javaList = XmlUtil.converyToJavaBean(
                    javaXml, JavaDBCourseList.class
            );
            if (javaList != null) {
                return javaList;
            }
        }
        catch (Exception e) {
            System.out.println("从Java服务器获得课程信息出错");
            e.printStackTrace();
        }

        return result;
    }

    private static PHPDBCourseList getPHPCourseList() {
        PHPDBCourseList result = new PHPDBCourseList();
        result.setCourseList(new LinkedList<>());

        try {
            String phpXml = HttpUtil.post(
                    ServerConfig.PHP_ALL_COURSE_URL,
                    ServerConfig.phpAllCourseParam
            );
            PHPDBCourseList phpList = XmlUtil.converyToJavaBean(
                    phpXml, PHPDBCourseList.class
            );
            if (phpList != null) {
                return phpList;
            }
        }
        catch (Exception e) {
            System.out.println("从PHP服务器获得课程信息出错");
            e.printStackTrace();
        }

        return result;
    }

}


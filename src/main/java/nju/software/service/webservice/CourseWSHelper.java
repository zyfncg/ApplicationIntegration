package nju.software.service.webservice;

import nju.software.config.ServerConfig;
import nju.software.model.javadb.JavaDBCourseList;
import nju.software.model.phpdb.PHPDBCourseList;
import nju.software.model.pythondb.PythonDBCourseInfo;
import nju.software.model.pythondb.PythonDBCourseList;
import nju.software.service.helper.AllCourseXmlGenerator;
import nju.software.util.WebServiceUtil;
import nju.software.util.XmlUtil;
import org.apache.axiom.om.OMElement;
import org.apache.axis2.databinding.utils.BeanUtil;
import org.apache.axis2.engine.DefaultObjectSupplier;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Anson Shaw on 2017/6/26.
 *
 * 从其它服务器通过Web Service获得课程信息
 */
class CourseWSHelper {

    private static Map<Integer, AllCourseXmlGenerator> handlerMap = new HashMap<>();

    static {
        // 处理来自Java服务器的请求,获取其它服务器的全部课程信息
        handlerMap.put(ServerConfig.JAVA_DB_INSTITUTION, () -> {
            JavaDBCourseList result = new JavaDBCourseList();
            result.setCourseList(new LinkedList<>());

            result.getCourseList().addAll(
                    getPythonWSCourseList(
                            ServerConfig.PYTHON_WS_ALL_COURSE_URL, null
                    ).toStandard().toJavaList().getCourseList()
            );

            result.getCourseList().addAll(
                    getPHPWSCourseList(
                            ServerConfig.PHP_WS_ALL_COURSE_URL, null
                    ).toStandard().toJavaList().getCourseList()
            );

            return XmlUtil.convertToXml(result);
        });

        // 处理来自Python服务器的请求,获取其它服务器的全部课程信息
        handlerMap.put(ServerConfig.PYTHON_DB_INSTITUTION, () -> {
            PythonDBCourseList result = new PythonDBCourseList();
            result.setCourseList(new LinkedList<>());

            result.getCourseList().addAll(
                    getJavaWSCourseList(
                            ServerConfig.JAVA_WS_ALL_COURSE_URL, null
                    ).toStandard().toPythonList().getCourseList()
            );

            result.getCourseList().addAll(
                    getPHPWSCourseList(
                            ServerConfig.PHP_WS_ALL_COURSE_URL, null
                    ).toStandard().toPythonList().getCourseList()
            );

            return XmlUtil.convertToXml(result);
        });

        // 处理来自PHP服务器的请求,获取其它服务器的全部课程信息
        handlerMap.put(ServerConfig.PHP_DB_INSTITUTION, () -> {
            PHPDBCourseList result = new PHPDBCourseList();
            result.setCourseList(new LinkedList<>());

            result.getCourseList().addAll(
                    getPythonWSCourseList(
                            ServerConfig.PYTHON_WS_ALL_COURSE_URL, null
                    ).toStandard().toPHPList().getCourseList()
            );

            result.getCourseList().addAll(
                    getJavaWSCourseList(
                            ServerConfig.JAVA_WS_ALL_COURSE_URL, null
                    ).toStandard().toPHPList().getCourseList()
            );

            return XmlUtil.convertToXml(result);
        });
    }

    static Map<Integer, AllCourseXmlGenerator> getHandlerMap() {
        return handlerMap;
    }

    static PythonDBCourseList getPythonWSCourseList(
            String methodUrl, Map<String, String> params
    ) {
        PythonDBCourseList result = new PythonDBCourseList();
        result.setCourseList(new LinkedList<>());

        try {
            OMElement data = WebServiceUtil.invoke(
                    WebServiceUtil.pythonClient,
                    WebServiceUtil.crateMethodElement(
                            ServerConfig.PYTHON_TARGET_NAMESPACE,
                            methodUrl, params
                    )
            );
            Iterator itr = data.getFirstElement().getChildElements();
            while (itr.hasNext()) {
                PythonDBCourseInfo info = (PythonDBCourseInfo) BeanUtil.processObject(
                        (OMElement) itr.next(), PythonDBCourseInfo.class, null,
                        false, new DefaultObjectSupplier(),null
                );
                result.getCourseList().add(info);
            }
        }
        catch (Exception e) {
            System.out.println("从Python Web Service服务器获得课程信息出错");
            e.printStackTrace();
        }
        return result;
    }

    static JavaDBCourseList getJavaWSCourseList(
            String methodUrl, Map<String, String> params
    ) {
        JavaDBCourseList result = new JavaDBCourseList();
        result.setCourseList(new LinkedList<>());

        try {
            OMElement data = WebServiceUtil.invoke(
                    WebServiceUtil.javaClient,
                    WebServiceUtil.crateMethodElement(
                            ServerConfig.JAVA_TARGET_NAMESPACE,
                            methodUrl, params
                    )
            );

            JavaDBCourseList list = XmlUtil.converyToJavaBean(
                    data.getFirstElement().getText(),
                    JavaDBCourseList.class
            );

            if (list != null) {
                return list;
            }
        }
        catch (Exception e) {
            System.out.println("从Java Web Service服务器获得课程信息失败");
            e.printStackTrace();
        }

        return result;
    }

    static PHPDBCourseList getPHPWSCourseList(
            String methodUrl, Map<String, String> params
    ) {
        PHPDBCourseList result = new PHPDBCourseList();
        result.setCourseList(new LinkedList<>());

        try {
            OMElement data = WebServiceUtil.invoke(
                    WebServiceUtil.phpClient,
                    WebServiceUtil.crateMethodElement(
                            ServerConfig.PHP_TARGET_NAMESPACE,
                            methodUrl, params
                    )
            );

            PHPDBCourseList list = XmlUtil.converyToJavaBean(
                    data.getFirstElement().getText(),
                    PHPDBCourseList.class
            );

            if (list != null) {
                return list;
            }
        }
        catch (Exception e) {
            System.out.println("从PHP Web Service服务器获得课程信息失败");
            e.printStackTrace();
        }

        return result;
    }
}

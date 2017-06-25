package webservice;

import nju.software.config.ServerConfig;
import nju.software.util.WebServiceUtil;
import org.apache.axis2.AxisFault;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anson Shaw on 2017/6/25.
 *
 * Python服务器Web Service测试
 */
public class PythonWSTest {

    public static void main(String[] args) throws AxisFault {
        System.out.println(WebServiceUtil.invokePython(
                ServerConfig.PYTHON_TARGET_NAMESPACE,
                ServerConfig.PYTHON_WS_COURSE_STAT_URL,
                null
        ));

        System.out.println(WebServiceUtil.invokePython(
                ServerConfig.PYTHON_TARGET_NAMESPACE,
                ServerConfig.PYTHON_WS_STUDENT_URL,
                null
        ));

        System.out.println(WebServiceUtil.invokePython(
                ServerConfig.PYTHON_TARGET_NAMESPACE,
                ServerConfig.PYTHON_WS_ALL_COURSE_URL,
                null
        ));

        Map<String, String> param = new HashMap<>();
        param.put("idStr", "1,2,3");
        System.out.println(WebServiceUtil.invokePython(
                ServerConfig.PYTHON_TARGET_NAMESPACE,
                ServerConfig.PYTHON_WS_COURSE_BY_ID_URL,
                param
        ));
    }
}

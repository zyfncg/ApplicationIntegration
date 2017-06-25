package nju.software.util;

import nju.software.config.ServerConfig;
import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;

import java.util.Map;

/**
 * Created by Anson Shaw on 2017/6/25.
 *
 * Web Service 连接工具
 */
public class WebServiceUtil {

    private static ServiceClient pythonClient = initClient(ServerConfig.PYTHON_WSDL_URL);

    /**
     *
     * @param tns 目标命名空间字符串
     * @param methodName 远程方法名称
     * @return 数据XML封装对象
     * @throws AxisFault 远程调用失败异常
     */
    public static OMElement invokePython(
            String tns, String methodName, Map<String, String> params) throws AxisFault {
        OMElement method = crateMethodElement(tns, methodName, params);
        method.build();
        return pythonClient.sendReceive(method);
    }

    private static ServiceClient initClient(String wsdl) {
        ServiceClient client = null;
        try {
            client = new ServiceClient();
            EndpointReference reference = new EndpointReference(wsdl);
            Options options = client.getOptions();
            options.setTo(reference);
        } catch (AxisFault axisFault) {
            System.out.println("从" + wsdl + "获取WSDL失败");
            axisFault.printStackTrace();
        }
        return client;
    }

    private static OMElement crateMethodElement(
            String tns, String methodName, Map<String, String> params) {
        OMFactory factory = OMAbstractFactory.getOMFactory();
        OMNamespace namespace = factory.createOMNamespace(tns, "");
        OMElement method =  factory.createOMElement(methodName, namespace);

        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                OMElement param = factory.createOMElement(entry.getKey(), namespace);
                param.addChild(factory.createOMText(param, entry.getValue()));
                method.addChild(param);
            }
        }

        return method;
    }
}

package webservice;

import org.apache.axiom.om.*;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;

/**
 * Created by Anson Shaw on 2017/6/25.
 *
 * Web Service 客户端测试
 */
public class WebServiceTest {

    public static void main(String[] args) throws Exception {
        String wsdl = "http://111.231.22.133:8088/?wsdl";
        ServiceClient client = new ServiceClient();
        EndpointReference reference = new EndpointReference(wsdl);
        Options options = client.getOptions();
        options.setTo(reference);

        OMFactory factory = OMAbstractFactory.getOMFactory();
        OMNamespace namespace = factory.createOMNamespace("edu.nju.software.education.soap", "");
        OMElement method = factory.createOMElement("getCourseStatistics", namespace);
        method.build();
        System.out.println(client.sendReceive(method));

        OMElement method1 = factory.createOMElement("getCourseByIds", namespace);
        OMElement param1 = factory.createOMElement("idStr", namespace);
        param1.addChild(factory.createOMText(param1, "1,2,3"));
        method1.addChild(param1);
        method1.build();
        System.out.println(client.sendReceive(method1));
    }
}

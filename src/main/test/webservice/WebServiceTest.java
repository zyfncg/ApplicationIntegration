package webservice;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
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

        OMElement result = client.sendReceive(method);
        System.out.println(result);
        System.out.println("done");
    }
}

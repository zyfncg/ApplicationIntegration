package webservice;

import nju.software.config.ServerConfig;
import nju.software.model.Selection;
import nju.software.service.helper.SelectHelper;
import nju.software.util.WebServiceUtil;
import org.apache.axiom.om.OMElement;
import org.apache.axis2.AxisFault;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Anson Shaw on 2017/6/29.
 */
public class PHPWSTest {

    public static void main(String[] args) throws AxisFault {
        List<Selection> selections = new LinkedList<>();
        selections.add(new Selection(141110001, 2, 1, 3));
        Map<String, String> params = SelectHelper.getPHPParam(
                selections, ServerConfig.PHP_WS_COURSE_REQUEST_KEY
        );
        OMElement data = WebServiceUtil.invoke(
                WebServiceUtil.phpClient,
                WebServiceUtil.crateMethodElement(
                        ServerConfig.PHP_TARGET_NAMESPACE,
                        ServerConfig.PHP_WS_COURSE_BY_ID_URL,
                        params
                )
        );
        System.out.println(data);
    }
}

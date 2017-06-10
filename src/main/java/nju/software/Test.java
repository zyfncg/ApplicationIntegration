package nju.software;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by ZhangYF on 2017/6/10.
 */
public class Test {
//    @Test
    public void testXmlRequestResponse() throws IOException, URISyntaxException {
        String url = "http://localhost:8080/student/courses";
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("Accept", "application/xml");
        requestHeaders.set("Content-Type", "application/xml");

        String xmlStr = "<employeeX><name>Jack</name><salary>16000</salary></employeeX>";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> httpEntity = new HttpEntity<String>(xmlStr, requestHeaders);
        String xmlData = restTemplate.postForObject(url, httpEntity, String.class);

        System.out.println(xmlData);
    }
}

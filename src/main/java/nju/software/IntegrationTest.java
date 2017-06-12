package nju.software;

import nju.software.util.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anson Shaw on 2017/6/12.
 */
public class IntegrationTest {

    private static final String JAVA_HOST = "http://115.159.161.87:8080/edu_admin/integration/courses";

    private static final String PYTHON_HOST = "http://111.231.22.133/getAllCourseInfo";

    private static final String PHP_HOST = "http://118.89.192.189/Controller.php";

    public static void main(String[] args) {
        String url = "http://localhost:8088/student/courses";
        Map<String, String> javaData = new HashMap<>();
        javaData.put("institutionId", "1");
        System.out.println("Info except Java:");
        System.out.println(HttpUtil.post(url, javaData));

        Map<String, String> pythonData = new HashMap<>();
        pythonData.put("institutionId", "2");
        System.out.println("Info except Python:");
        System.out.println(HttpUtil.post(url, pythonData));

        Map<String, String> phpData = new HashMap<>();
        phpData.put("institutionId", "3");
        System.out.println("Info except PHP:");
        System.out.println(HttpUtil.post(url, phpData));


//        String javaXml = HttpUtil.post(JAVA_HOST, null);
//        JavaDBCourseList javaList = XmlUtil.converyToJavaBean(
//                javaXml, JavaDBCourseList.class
//        );
//        PythonDBResult pyInfos = new PythonDBResult();
//        PythonDBCourseList pyCourseList = new PythonDBCourseList();
//        pyCourseList.setCourseList(new LinkedList<PythonDBCourseInfo>());
//        pyInfos.setList(pyCourseList);
//        for (JavaDBCourseInfo info : javaList.getCourseList()) {
//            pyCourseList.getCourseList().add(info.toStandard().toPythonDBCourseInfo());
//        }
//        System.out.println("Python List: ");
//        System.out.println(XmlUtil.convertToXml(pyInfos));
//
//
//        String pythonXml = HttpUtil.post(PYTHON_HOST, null);
//        PythonDBResult pyList = XmlUtil.converyToJavaBean(
//                pythonXml, PythonDBResult.class
//        );
//        PHPDBCourseList phpInfos = new PHPDBCourseList();
//        phpInfos.setCourseList(new LinkedList<PHPDBCourseInfo>());
//        for (PythonDBCourseInfo info : pyList.getList().getCourseList()) {
//            phpInfos.getCourseList().add(info.toStandard().toPHPDBCourseInfo());
//        }
//        System.out.println("PHP List: ");
//        System.out.println(XmlUtil.convertToXml(phpInfos));
//
//        Map<String, String> data = new HashMap<>();
//        data.put("action", "Statistic/course_stat");
//        String phpXml = HttpUtil.post(PHP_HOST, data);
//        PHPDBCourseList phpList = XmlUtil.converyToJavaBean(
//                phpXml, PHPDBCourseList.class
//        );
//        JavaDBCourseList list = new JavaDBCourseList();
//        list.setCourseList(new LinkedList<JavaDBCourseInfo>());
//        for (PHPDBCourseInfo info : phpList.getCourseList()) {
//            StandardCourseInfo std = info.toStandard();
//            list.getCourseList().add(std.toJavaDBCourseInfo());
//        }
//        System.out.println("Java List: ");
//        System.out.println(XmlUtil.convertToXml(list));

    }
}

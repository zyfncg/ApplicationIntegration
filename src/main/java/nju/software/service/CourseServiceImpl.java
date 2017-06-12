package nju.software.service;

import nju.software.dao.SelectionDao;
import nju.software.model.Selection;
import nju.software.model.javadb.JavaDBCourseInfo;
import nju.software.model.javadb.JavaDBCourseList;
import nju.software.model.phpdb.PHPDBCourseInfo;
import nju.software.model.phpdb.PHPDBCourseList;
import nju.software.model.pythondb.PythonDBCourseInfo;
import nju.software.model.pythondb.PythonDBCourseList;
import nju.software.model.pythondb.PythonDBResult;
import nju.software.util.HttpUtil;
import nju.software.util.XmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by ZhangYF on 2017/6/4.
 */
@Service(value = "courseService")
public class CourseServiceImpl implements CourseService{

    @Autowired
    private SelectionDao selectionDao;

    @Override
    public String getAllCourse(int institutionId) {
        if (xmlHandler.containsKey(institutionId)) {
            return xmlHandler.get(institutionId).get();
        }
        return "没有这个院系ID";
    }

    @Override
    public List<Selection> getStudyCourse(int institutionId, int studentId) {
        List<Selection> selectionList = selectionDao.findAllByStudentidAndStudentInstitution(studentId,institutionId);
        return selectionList;
    }

    @Override
    public boolean addCourse(int institutionId, int studentId, int courseInstitution, int courseId) {
        Selection selection = new Selection(studentId,institutionId,courseId,courseInstitution);
        selectionDao.save(selection);
        return true;
    }

    @Override
    public boolean deleteCourse(int institutionId, int studentId, int courseInstitution, int courseId) {
        Selection selection = selectionDao.findByStudentidAndStudentInstitutionAndCourseidAndCourseInstitution
                (studentId,institutionId,courseId,courseInstitution);
        selectionDao.delete(selection);
        return true;
    }

    @Override
    public int studyCourseNum(int studentid, int institution) {
        return selectionDao.findAllByStudentidAndStudentInstitution(studentid,institution).size();
    }

    @Override
    public int getstudentNum(int courseid, int institution) {
        return selectionDao.findAllByCourseidAndCourseInstitution(courseid, institution).size();
    }

    private static final int JAVA_DB_INSTITUTION = 1;

    private static final int PYTHON_DB_INSTITUTION = 2;

    private static final int PHP_DB_INSTITUTION = 3;

    private static final String JAVA_HOST = "http://115.159.161.87:8080/edu_admin/integration/courses";

    private static final String PYTHON_HOST = "http://111.231.22.133/getAllCourseInfo";

    private static final String PHP_HOST = "http://118.89.192.189/Controller.php";

    // 向PHP服务起发送请求的参数
    private static Map<String, String> phpRequestData = new HashMap<>();
    static {
        phpRequestData.put("action", "Statistic/course_stat");
    }

    @FunctionalInterface
    private interface XmlDataGetter {
        String get();
    }

    private static Map<Integer, XmlDataGetter> xmlHandler = new HashMap<>();
    static {
        // 处理来自Java服务器的请求
        xmlHandler.put(JAVA_DB_INSTITUTION, () -> {
            JavaDBCourseList result = new JavaDBCourseList();
            result.setCourseList(new LinkedList<>());

            String pythonXml = HttpUtil.post(PYTHON_HOST, null);
            PythonDBResult pyList = XmlUtil.converyToJavaBean(
                    pythonXml, PythonDBResult.class
            );
            for (PythonDBCourseInfo info : pyList.getList().getCourseList()) {
                result.getCourseList().add(info.toStandard().toJavaDBCourseInfo());
            }

            String phpXml = HttpUtil.post(PHP_HOST, phpRequestData);
            PHPDBCourseList phpList = XmlUtil.converyToJavaBean(
                    phpXml, PHPDBCourseList.class
            );
            for (PHPDBCourseInfo info : phpList.getCourseList()) {
                result.getCourseList().add(info.toStandard().toJavaDBCourseInfo());
            }

            return XmlUtil.convertToXml(result);
        });

        // 处理来自Python服务器的请求
        xmlHandler.put(PYTHON_DB_INSTITUTION, () -> {
            PythonDBCourseList result = new PythonDBCourseList();
            result.setCourseList(new LinkedList<>());

            String javaXml = HttpUtil.post(JAVA_HOST, null);
            JavaDBCourseList javaList = XmlUtil.converyToJavaBean(
                    javaXml, JavaDBCourseList.class
            );
            for (JavaDBCourseInfo info : javaList.getCourseList()) {
                result.getCourseList().add(info.toStandard().toPythonDBCourseInfo());
            }

            String phpXml = HttpUtil.post(PHP_HOST, phpRequestData);
            PHPDBCourseList phpList = XmlUtil.converyToJavaBean(
                    phpXml, PHPDBCourseList.class
            );
            for (PHPDBCourseInfo info : phpList.getCourseList()) {
                result.getCourseList().add(info.toStandard().toPythonDBCourseInfo());
            }

            return XmlUtil.convertToXml(result);
        });

        // 处理来自PHP服务器的请求
        xmlHandler.put(PHP_DB_INSTITUTION, () -> {
            PHPDBCourseList result = new PHPDBCourseList();
            result.setCourseList(new LinkedList<>());

            String pythonXml = HttpUtil.post(PYTHON_HOST, null);
            PythonDBResult pyList = XmlUtil.converyToJavaBean(
                    pythonXml, PythonDBResult.class
            );
            for (PythonDBCourseInfo info : pyList.getList().getCourseList()) {
                result.getCourseList().add(info.toStandard().toPHPDBCourseInfo());
            }

            String javaXml = HttpUtil.post(JAVA_HOST, null);
            JavaDBCourseList javaList = XmlUtil.converyToJavaBean(
                    javaXml, JavaDBCourseList.class
            );
            for (JavaDBCourseInfo info : javaList.getCourseList()) {
                result.getCourseList().add(info.toStandard().toPHPDBCourseInfo());
            }

            return XmlUtil.convertToXml(result);
        });
    }
}

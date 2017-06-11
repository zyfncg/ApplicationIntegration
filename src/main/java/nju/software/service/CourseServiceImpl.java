package nju.software.service;

import nju.software.model.standard.StandardCourseList;
import nju.software.util.HttpUtil;
import nju.software.util.XmlUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ZhangYF on 2017/6/4.
 */
@Service(value = "courseService")
public class CourseServiceImpl implements CourseService{

//    @Autowired
//    private CourseDao courseDao;

    private static final int JAVA_DB_INSTITUTION = 1;

    private static final int PYTHON_DB_INSTITUTION = 2;

    private static final int PHP_DB_INSTITUTION = 3;

    private static final String JAVA_HOST = "";

    private static final String PYTHON_HOST = "";

    private static final String PHP_HOST = "";

    @Override
    public String getAllCourse(int institutionId) {
        StandardCourseList list = new StandardCourseList();
        if (institutionId == JAVA_DB_INSTITUTION) {
            String xmlFromPythonDB = HttpUtil.post(PYTHON_HOST, null);
        }
        else if (institutionId == PYTHON_DB_INSTITUTION) {

        }
        else if (institutionId == PHP_DB_INSTITUTION) {

        }

        return XmlUtil.convertToXml(list);
    }

    @Override
    public String getStudyCourse(int institutionId, int studentId) {
        return null;
    }

    @Override
    public boolean addCourse(int institutionId, int studentId, int courseInstitution, int courseId) {
        return false;
    }

    @Override
    public boolean deleteCourse(int institutionId, int studentId, int courseInstitution, int courseId) {
        return false;
    }
}

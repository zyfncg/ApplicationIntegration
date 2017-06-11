package nju.software.service;

import nju.software.dao.SelectionDao;
import nju.software.model.Selection;
import nju.software.model.standard.StandardCourseList;
import nju.software.util.HttpUtil;
import nju.software.util.XmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ZhangYF on 2017/6/4.
 */
@Service(value = "courseService")
public class CourseServiceImpl implements CourseService{

    @Autowired
    private SelectionDao selectionDao;

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

}

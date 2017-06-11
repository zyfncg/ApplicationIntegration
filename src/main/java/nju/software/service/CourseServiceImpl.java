package nju.software.service;

import nju.software.dao.SelectionDao;
import nju.software.model.Selection;
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

    @Override
    public String getAllCourse(int institutionId) {
        return null;
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
}

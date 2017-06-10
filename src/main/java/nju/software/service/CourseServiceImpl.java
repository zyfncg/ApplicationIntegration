package nju.software.service;

import nju.software.dao.CourseDao;
import nju.software.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ZhangYF on 2017/6/4.
 */
@Service(value = "courseService")
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseDao courseDao;

    @Override
    public Course getCourse(int courseid) {
        return courseDao.findOne(courseid);
    }

    @Override
    public List<Course> getAllCourse() {
        return courseDao.findAll();
    }

    @Override
    public List<Course> getStudyCourse(int studentid) {
        return courseDao.findStudyCourses(studentid);
    }

    @Override
    public boolean addCourse(Course course) {
        courseDao.save(course);
        return true;
    }

    @Override
    public boolean updateCourse(Course course) {
        courseDao.save(course);
        return true;
    }
}

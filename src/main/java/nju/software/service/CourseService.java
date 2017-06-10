package nju.software.service;

import nju.software.model.Course;

import java.util.List;

/**
 * Created by ZhangYF on 2017/6/4.
 */
public interface CourseService {
    public Course getCourse(int courseid);

    public List<Course> getAllCourse();

    public List<Course> getStudyCourse(int studentid);

    public boolean addCourse(Course course);

    public boolean updateCourse(Course course);
}

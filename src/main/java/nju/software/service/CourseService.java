package nju.software.service;

/**
 * Created by ZhangYF on 2017/6/4.
 */
public interface CourseService {

    String getAllCourse(int institutionId);

    String getStudyCourse(int institutionId, int studentId);

    boolean addCourse(int institutionId, int studentId, int courseInstitution, int courseId);

    boolean deleteCourse(int institutionId, int studentId, int courseInstitution, int courseId);
}

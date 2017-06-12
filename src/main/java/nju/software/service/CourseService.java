package nju.software.service;

import nju.software.model.Selection;

import java.util.List;
import java.util.Map;

/**
 * Created by ZhangYF on 2017/6/4.
 */
public interface CourseService {

    String getAllCourse(int institutionId);

    /**
     * 获得跨院系的已选课程
     * @param institutionId
     * @param studentId
     * @return
     */
    List<Selection> getStudyCourse(int institutionId, int studentId);

    /**
     * 选课
     * @param institutionId
     * @param studentId
     * @param courseInstitution
     * @param courseId
     * @return
     */
    boolean addCourse(int institutionId, int studentId, int courseInstitution, int courseId);

    /**
     * 退课
     * @param institutionId
     * @param studentId
     * @param courseInstitution
     * @param courseId
     * @return
     */
    boolean deleteCourse(int institutionId, int studentId, int courseInstitution, int courseId);

    /**
     * 获得跨院系已选课数量
     * @param studentid
     * @param institution
     * @return
     */
    public int studyCourseNum(int studentid, int institution);

    /**
     * 获得一门课程的跨院系选课学生人数
     * @param courseid
     * @param institution
     * @return
     */
    public int getStudentNum(int courseid, int institution);

    /**
     * 统计院系所有课程的跨院系选课人数
     * @param intitution
     * @return 课程id:课程学生数
     */
    public Map<Integer,Integer> statisticCourses(int intitution);

    /**
     * 统计院系所有学生的跨院系选课数
     * @param intitution
     * @return 学生id：学生选课数
     */
    public Map<Integer,Integer> statisticStudents(int intitution);
}

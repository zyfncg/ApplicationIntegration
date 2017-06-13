package nju.software.service;

import nju.software.model.statistic.CourseInfo;
import nju.software.model.statistic.StudentInfo;

import java.util.List;

/**
 * Created by Anson Shaw on 2017/6/13.
 */
public interface StatisticService {

    List<StudentInfo> getStudentStatistics();

    List<CourseInfo> getCourseStatistics();
}

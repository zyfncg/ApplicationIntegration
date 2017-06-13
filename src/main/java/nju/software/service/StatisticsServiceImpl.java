package nju.software.service;

import nju.software.model.statistic.CourseInfo;
import nju.software.model.statistic.StudentInfo;
import nju.software.service.helper.StatisticsHelper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Anson Shaw on 2017/6/13.
 */
@Service(value = "StatisticsService")
public class StatisticsServiceImpl implements StatisticService {

    @Override
    public List<StudentInfo> getStudentStatistics() {
        return StatisticsHelper.getRemoteStudentStatistics();
    }

    @Override
    public List<CourseInfo> getCourseStatistics() {
        return StatisticsHelper.getRemoteCourseStatistics();
    }
}

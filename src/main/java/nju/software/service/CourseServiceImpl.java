package nju.software.service;

import org.springframework.stereotype.Service;

/**
 * Created by ZhangYF on 2017/6/4.
 */
@Service(value = "courseService")
public class CourseServiceImpl implements CourseService{

//    @Autowired
//    private CourseDao courseDao;

    @Override
    public String getAllCourse(int institutionId) {
        return null;
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

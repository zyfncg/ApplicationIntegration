package nju.software.dao;

import nju.software.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ZhangYF on 2017/6/4.
 */
@Repository
public interface CourseDao extends JpaRepository<Course, Integer> {

    @Modifying
    @Query("delete from Course where courseid in (select course.courseid from StudCour where student.studentid=?1)")
    List<Course> findStudyCourses(int studentid);

}

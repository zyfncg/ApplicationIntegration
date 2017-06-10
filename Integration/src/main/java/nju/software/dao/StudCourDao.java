package nju.software.dao;

import nju.software.model.Course;
import nju.software.model.StudCour;
import nju.software.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by ZhangYF on 2017/6/4.
 */
@Repository
public interface StudCourDao extends JpaRepository<StudCour, Integer> {

    @Modifying
    @Query("delete from StudCour where student.studentid=?1 and course.courseid=?2")
    void deleteStudCour(int studentid, int courseid);

    void deleteStudCourByStudentAndCourse(Student student, Course course);
}

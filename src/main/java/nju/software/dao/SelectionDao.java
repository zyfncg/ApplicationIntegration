package nju.software.dao;

import nju.software.model.Selection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ZhangYF on 2017/6/11.
 */
@Repository
public interface SelectionDao extends JpaRepository<Selection,Integer>{

    List<Selection> findAllByStudentidAndStudentInstitution(int studentid, int studentInstitution);

    Selection findByStudentidAndStudentInstitutionAndCourseidAndCourseInstitution(int studentid, int studentInsitution, int courseid ,int courseInstitution);
}

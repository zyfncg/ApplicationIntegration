package nju.software.dao;

import nju.software.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ZhangYF on 2017/5/26.
 */
@Repository
public interface StudentDao extends JpaRepository<Student, Integer> {

}

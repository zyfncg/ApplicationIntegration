package nju.software.service;

import nju.software.model.Student;

import java.util.List;

/**
 * Created by ZhangYF on 2017/5/26.
 */

public interface StudentService {
    public Student getStudent(int studentid);

    public List<Student> getAllStudent();

    public boolean addStudent(Student student);

    public boolean updateStduent(Student student);
}

package nju.software.model;

import java.io.Serializable;

/**
 * Created by ZhangYF on 2017/5/26.
 */
public class Student implements Serializable{

    private Integer institution;

    private Integer studentId;

    private String name;

    public Student() {
    }

    public Student(Integer institution, Integer studentId, String name) {
        this.institution = institution;
        this.studentId = studentId;
        this.name = name;
    }

    public Integer getInstitution() {
        return institution;
    }

    public void setInstitution(Integer institution) {
        this.institution = institution;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

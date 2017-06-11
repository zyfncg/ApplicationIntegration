package nju.software.model;

import java.io.Serializable;

/**
 * Created by Anson Shaw on 2017/6/11.
 */
public class Selection implements Serializable {

    private Integer studentId;

    private Integer studentInstitution;

    private Integer courseId;

    private Integer courseInstitution;

    public Selection() {
    }

    public Selection(Integer studentId, Integer studentInstitution, Integer courseId, Integer courseInstitution) {
        this.studentId = studentId;
        this.studentInstitution = studentInstitution;
        this.courseId = courseId;
        this.courseInstitution = courseInstitution;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getStudentInstitution() {
        return studentInstitution;
    }

    public void setStudentInstitution(Integer studentInstitution) {
        this.studentInstitution = studentInstitution;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getCourseInstitution() {
        return courseInstitution;
    }

    public void setCourseInstitution(Integer courseInstitution) {
        this.courseInstitution = courseInstitution;
    }
}

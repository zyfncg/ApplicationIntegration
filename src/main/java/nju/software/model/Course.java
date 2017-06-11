package nju.software.model;

import java.io.Serializable;

/**
 * Created by ZhangYF on 2017/6/4.
 */
public class Course implements Serializable {

    private Integer institution;

    private Integer courseId;

    private String name;

    public Course(Integer institution, Integer courseId, String name) {
        this.institution = institution;
        this.courseId = courseId;
        this.name = name;
    }

    public Course() {
    }

    public Integer getInstitution() {
        return institution;
    }

    public void setInstitution(Integer institution) {
        this.institution = institution;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

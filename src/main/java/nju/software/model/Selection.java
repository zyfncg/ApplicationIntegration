package nju.software.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by ZhangYF on 2017/6/11.
 */
@Entity
@XmlRootElement
public class Selection {
    private Integer id;
    private Integer studentid;
    private Integer studentInstitution;
    private Integer courseid;
    private Integer courseInstitution;

    public Selection() {
    }

    public Selection(Integer studentid, Integer studentInstitution, Integer courseid, Integer courseInstitution) {
        this.studentid = studentid;
        this.studentInstitution = studentInstitution;
        this.courseid = courseid;
        this.courseInstitution = courseInstitution;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "studentid", nullable = false)
    public Integer getStudentid() {
        return studentid;
    }

    public void setStudentid(Integer studentid) {
        this.studentid = studentid;
    }

    @Basic
    @Column(name = "studentInstitution", nullable = false)
    public Integer getStudentInstitution() {
        return studentInstitution;
    }

    public void setStudentInstitution(Integer studentInstitution) {
        this.studentInstitution = studentInstitution;
    }

    @Basic
    @Column(name = "courseid", nullable = false)
    public Integer getCourseid() {
        return courseid;
    }

    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
    }

    @Basic
    @Column(name = "courseInstitution", nullable = false)
    public Integer getCourseInstitution() {
        return courseInstitution;
    }

    public void setCourseInstitution(Integer courseInstitution) {
        this.courseInstitution = courseInstitution;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Selection selection = (Selection) o;

        if (id != null ? !id.equals(selection.id) : selection.id != null) return false;
        if (studentid != null ? !studentid.equals(selection.studentid) : selection.studentid != null) return false;
        if (studentInstitution != null ? !studentInstitution.equals(selection.studentInstitution) : selection.studentInstitution != null)
            return false;
        if (courseid != null ? !courseid.equals(selection.courseid) : selection.courseid != null) return false;
        if (courseInstitution != null ? !courseInstitution.equals(selection.courseInstitution) : selection.courseInstitution != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (studentid != null ? studentid.hashCode() : 0);
        result = 31 * result + (studentInstitution != null ? studentInstitution.hashCode() : 0);
        result = 31 * result + (courseid != null ? courseid.hashCode() : 0);
        result = 31 * result + (courseInstitution != null ? courseInstitution.hashCode() : 0);
        return result;
    }
}

package nju.software.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

/**
 * Created by ZhangYF on 2017/6/4.
 */
@Entity
@Table(name = "course")
public class Course {
    private Integer courseid;
    private String name;
    private String semester;
    private String teacher;
    private Integer credit;
    private List<StudCour> studCours;

    @Id
    @Column(name = "courseid", nullable = false)
    public Integer getCourseid() {
        return courseid;
    }

    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "semester", nullable = true, length = 15)
    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    @Basic
    @Column(name = "teacher", nullable = true, length = 20)
    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    @Basic
    @Column(name = "credit", nullable = true)
    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    @OneToMany(mappedBy = "course")
    @LazyCollection(LazyCollectionOption.EXTRA)
    public List<StudCour> getStudCours() {
        return studCours;
    }

    public void setStudCours(List<StudCour> studCours) {
        this.studCours = studCours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (courseid != null ? !courseid.equals(course.courseid) : course.courseid != null) return false;
        if (name != null ? !name.equals(course.name) : course.name != null) return false;
        if (semester != null ? !semester.equals(course.semester) : course.semester != null) return false;
        if (teacher != null ? !teacher.equals(course.teacher) : course.teacher != null) return false;
        if (credit != null ? !credit.equals(course.credit) : course.credit != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = courseid != null ? courseid.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (semester != null ? semester.hashCode() : 0);
        result = 31 * result + (teacher != null ? teacher.hashCode() : 0);
        result = 31 * result + (credit != null ? credit.hashCode() : 0);
        return result;
    }
}

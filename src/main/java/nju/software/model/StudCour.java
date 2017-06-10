package nju.software.model;

import javax.persistence.*;

/**
 * Created by ZhangYF on 2017/6/4.
 */
@Entity
@Table(name = "stud_cour")
public class StudCour {
    private Integer id;
    private Double grade;
    private Course course;
    private Student student;

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
    @Column(name = "grade", nullable = true, precision = 0)
    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    @ManyToOne
    @JoinColumn(name="courseid")
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @ManyToOne
    @JoinColumn(name = "studentid")
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudCour studCour = (StudCour) o;

        if (id != null ? !id.equals(studCour.id) : studCour.id != null) return false;
        if (grade != null ? !grade.equals(studCour.grade) : studCour.grade != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (grade != null ? grade.hashCode() : 0);
        return result;
    }
}

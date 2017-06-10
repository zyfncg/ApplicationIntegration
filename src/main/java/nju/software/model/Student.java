package nju.software.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by ZhangYF on 2017/5/26.
 */
@Entity
@Table(name = "student")
public class Student implements Serializable{
    private int studentid;
    private String name;
    private String password;
    private String gender;
    private List<StudCour> studCours;

    @Id
    @Column(name = "studentid", nullable = false)
    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
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
    @Column(name = "password", nullable = false, length = 20)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "gender", nullable = true)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @OneToMany
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

        Student student = (Student) o;

        if (studentid != student.studentid) return false;
        if (name != null ? !name.equals(student.name) : student.name != null) return false;
        if (password != null ? !password.equals(student.password) : student.password != null) return false;
        if (gender != null ? !gender.equals(student.gender) : student.gender != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = studentid;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        return result;
    }
}

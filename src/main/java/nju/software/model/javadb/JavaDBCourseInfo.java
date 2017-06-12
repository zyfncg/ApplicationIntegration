package nju.software.model.javadb;

import nju.software.model.standard.StandardCourseInfo;
import nju.software.model.standard.Standardized;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Anson Shaw on 2017/6/11.
 */
@XmlRootElement(name = "course")
public class JavaDBCourseInfo implements Standardized {

    private Integer courseid;
    private String name;
    private String semester;
    private String teacher;
    private Integer credit;
    private int institution;

    public JavaDBCourseInfo() {
    }

    public JavaDBCourseInfo(int institution, Integer courseid, Integer credit, String name, String semester, String teacher) {
        this.courseid = courseid;
        this.name = name;
        this.semester = semester;
        this.teacher = teacher;
        this.credit = credit;
        this.institution = institution;
    }

    public Integer getCourseid() {
        return courseid;
    }

    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public int getInstitution() {
        return institution;
    }

    public void setInstitution(int institution) {
        this.institution = institution;
    }

    @Override
    public StandardCourseInfo toStandard() {
        return new StandardCourseInfo(
                institution, courseid, credit, name,
                "未知", semester, "未知", "未知", teacher
        );
    }
}

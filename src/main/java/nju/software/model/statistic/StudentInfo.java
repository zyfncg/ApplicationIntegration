package nju.software.model.statistic;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by ZhangYF on 2017/6/11.
 */
@XmlRootElement
public class StudentInfo {
    private int studentid;
    private String name;
    private int institution;
    private int courseNum;

    public StudentInfo() {
    }

    public StudentInfo(int studentid, String name, int institution, int courseNum) {
        this.studentid = studentid;
        this.name = name;
        this.institution = institution;
        this.courseNum = courseNum;
    }

    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInstitution() {
        return institution;
    }

    public void setInstitution(int institution) {
        this.institution = institution;
    }

    public int getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(int courseNum) {
        this.courseNum = courseNum;
    }
}

package nju.software.model.statistic;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by ZhangYF on 2017/6/11.
 */
@XmlRootElement (name = "courseInfo")
public class CourseInfo{

    private int courseid;
    private String name;
    private int institution;
    private Integer studentNum;

    public CourseInfo() {
        super();
    }

    public CourseInfo(int courseid, String name, int institution, Integer studentNum) {
        this.courseid = courseid;
        this.name = name;
        this.institution = institution;
        this.studentNum = studentNum;
    }

    public int getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
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

    public Integer getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(Integer studentNum) {
        this.studentNum = studentNum;
    }
}

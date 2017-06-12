package nju.software.model.pythondb;

import nju.software.model.standard.StandardCourseInfo;
import nju.software.model.standard.CourseStandardized;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Anson Shaw on 2017/6/11.
 */
@XmlRootElement(name = "course")
public class PythonDBCourseInfo implements CourseStandardized {

    private int institutionId;

    private int courseId;

    private String name;

    private String classroom;

    private String classtime;

    private String type;

    private String department;

    public PythonDBCourseInfo() {
    }

    public PythonDBCourseInfo(
            int institutionId, int courseId, String name,
            String classroom, String classtime,
            String type, String department
    ) {
        this.institutionId = institutionId;
        this.courseId = courseId;
        this.name = name;
        this.classroom = classroom;
        this.classtime = classtime;
        this.type = type;
        this.department = department;
    }

    public int getInstitutionId() {
        return institutionId;
    }

    @XmlElement(name = "department_id")
    public void setInstitutionId(int institutionId) {
        this.institutionId = institutionId;
    }

    public int getCourseId() {
        return courseId;
    }

    @XmlElement(name = "course_id")
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    @XmlElement(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    public String getClassroom() {
        return classroom;
    }

    @XmlElement(name = "classroom")
    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getClasstime() {
        return classtime;
    }

    @XmlElement(name = "classtime")
    public void setClasstime(String classtime) {
        this.classtime = classtime;
    }

    public String getType() {
        return type;
    }

    @XmlElement(name = "type")
    public void setType(String type) {
        this.type = type;
    }

    public String getDepartment() {
        return department;
    }

    @XmlElement(name = "department")
    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public StandardCourseInfo toStandard() {
        return new StandardCourseInfo(
                institutionId, courseId, 0, name, classroom,
                classtime, type, department, "未知"
        );
    }
}

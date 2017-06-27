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

    private int department_id;

    private int course_id;

    private String name;

    private String classroom;

    private String classtime;

    private String classtype;

    private String department;

    public PythonDBCourseInfo() {
    }

    public PythonDBCourseInfo(
            int institutionId, int courseId, String name,
            String classroom, String classtime,
            String classtype, String department
    ) {
        this.department_id = institutionId;
        this.course_id = courseId;
        this.name = name;
        this.classroom = classroom;
        this.classtime = classtime;
        this.classtype = classtype;
        this.department = department;
    }

    public int getDepartment_id() {
        return department_id;
    }

    @XmlElement(name = "department_id")
    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    @XmlElement(name = "course_id")
    public void setCourse_id(int course_id) {
        this.course_id = course_id;
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

    public String getClasstype() {
        return classtype;
    }

    @XmlElement(name = "classtype")
    public void setClasstype(String classtype) {
        this.classtype = classtype;
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
                department_id, course_id, 0, name, classroom,
                classtime, classtype, department, "未知"
        );
    }
}

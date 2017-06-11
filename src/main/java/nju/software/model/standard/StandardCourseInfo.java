package nju.software.model.standard;

import nju.software.model.javadb.JavaDBCourseInfo;
import nju.software.model.phpdb.PHPDBCourseInfo;
import nju.software.model.pythondb.PythonDBCourseInfo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Anson Shaw on 2017/6/11.
 */
@XmlRootElement(name = "course")
public class StandardCourseInfo {

    private int institutionId;

    private int courseId;

    private int credit;

    private String name;

    private String classroom;

    private String classtime;

    private String type;

    private String department;

    private String teacher;

    public StandardCourseInfo() {
    }

    public StandardCourseInfo(
            int institutionId, int courseId, int credit,
            String name, String classroom, String classtime,
            String type, String department, String teacher
    ) {
        this.institutionId = institutionId;
        this.courseId = courseId;
        this.credit = credit;
        this.name = name;
        this.classroom = classroom;
        this.classtime = classtime;
        this.type = type;
        this.department = department;
        this.teacher = teacher;
    }

    public int getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(int institutionId) {
        this.institutionId = institutionId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getClasstime() {
        return classtime;
    }

    public void setClasstime(String classtime) {
        this.classtime = classtime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public PythonDBCourseInfo toPythonDBCourseInfo() {
        return new PythonDBCourseInfo(
                institutionId, courseId, name, classroom,
                classtime, type, department
        );
    }

    public PHPDBCourseInfo toPHPDBCourseInfo() {
        return new PHPDBCourseInfo(
                institutionId, courseId, name
        );
    }

    public JavaDBCourseInfo toJavaDBCourseInfo() {
        return new JavaDBCourseInfo(
                institutionId, courseId, credit,
                name, classtime, teacher
        );
    }
}

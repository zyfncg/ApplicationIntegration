package nju.software.model.javadb;

import nju.software.model.standard.StandardCourseInfo;
import nju.software.model.standard.Standardable;

/**
 * Created by Anson Shaw on 2017/6/11.
 */
public class JavaDBCourseInfo implements Standardable {

    private int institutionId;

    private int courseId;

    private int credit;

    private String name;

    private String semester;

    private String teacher;

    public JavaDBCourseInfo() {
    }

    public JavaDBCourseInfo(
            int institutionId, int courseId, int credit,
            String name, String semester, String teacher
    ) {
        this.institutionId = institutionId;
        this.courseId = courseId;
        this.credit = credit;
        this.name = name;
        this.semester = semester;
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

    @Override
    public StandardCourseInfo toStandard() {
        return new StandardCourseInfo(
                institutionId, courseId, credit, name,
                "未知", semester, "未知", "未知", teacher
        );
    }
}

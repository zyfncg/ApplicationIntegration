package nju.software.model.phpdb;

import nju.software.model.standard.StandardCourseInfo;
import nju.software.model.standard.Standardable;

/**
 * Created by Anson Shaw on 2017/6/11.
 */
public class PHPDBCourseInfo implements Standardable {
    private int institutionId;

    private int courseId;

    private String name;

    public PHPDBCourseInfo() {
    }

    public PHPDBCourseInfo(int institutionId, int courseId, String name) {
        this.institutionId = institutionId;
        this.courseId = courseId;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public StandardCourseInfo toStandard() {
        return new StandardCourseInfo(
                institutionId, courseId, 0, name, "未知",
                "未知", "未知", "未知", "未知"
        );
    }
}

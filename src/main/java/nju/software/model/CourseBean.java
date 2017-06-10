package nju.software.model;

/**
 * Created by ZhangYF on 2017/6/5.
 */
public class CourseBean {
    private Integer courseid;
    private String name;
    private String semester;
    private String teacher;
    private Integer credit;

    public CourseBean() {
    }

    public CourseBean(Course course) {
        this.courseid = course.getCourseid();
        this.name = course.getName();
        this.semester = course.getSemester();
        this.teacher = course.getTeacher();
        this.credit = course.getCredit();
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
}

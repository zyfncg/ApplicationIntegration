package nju.software.model.javadb;

import nju.software.model.standard.CourseListStandardized;
import nju.software.model.standard.CourseStandardized;
import nju.software.model.standard.StandardCourseList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Anson Shaw on 2017/6/12.
 */
@XmlRootElement(name = "listBean")
public class JavaDBCourseList implements CourseListStandardized {

    private List<JavaDBCourseInfo> courseList;

    @XmlElements({
            @XmlElement(name = "course", type = JavaDBCourseInfo.class)
    })
    public List<JavaDBCourseInfo> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<JavaDBCourseInfo> courseList) {
        this.courseList = courseList;
    }

    @Override
    public StandardCourseList toStandard() {
        StandardCourseList list = new StandardCourseList();
        list.setCourseList(new LinkedList<>());
        for (JavaDBCourseInfo info : courseList) {
            list.getCourseList().add(info.toStandard());
        }
        return list;
    }
}

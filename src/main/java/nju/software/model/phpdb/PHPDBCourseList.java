package nju.software.model.phpdb;

import nju.software.model.javadb.JavaDBCourseInfo;
import nju.software.model.standard.CourseListStandardized;
import nju.software.model.standard.StandardCourseList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Anson Shaw on 2017/6/12.
 *
 * PHP服务器端的课程信息列表格式
 */
@XmlRootElement(name = "courses")
public class PHPDBCourseList implements CourseListStandardized {

    private List<PHPDBCourseInfo> courseList;

    @XmlElements({
            @XmlElement(name = "course", type = PHPDBCourseInfo.class)
    })
    public List<PHPDBCourseInfo> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<PHPDBCourseInfo> courseList) {
        this.courseList = courseList;
    }

    @Override
    public StandardCourseList toStandard() {
        StandardCourseList list = new StandardCourseList();
        list.setCourseList(new LinkedList<>());
        for (PHPDBCourseInfo info : courseList) {
            list.getCourseList().add(info.toStandard());
        }
        return list;
    }
}

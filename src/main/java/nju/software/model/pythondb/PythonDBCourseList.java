package nju.software.model.pythondb;

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
 */
@XmlRootElement(name = "courses")
public class PythonDBCourseList implements CourseListStandardized {

    private List<PythonDBCourseInfo> courseList;

    @XmlElements({
            @XmlElement(name = "course", type = PythonDBCourseInfo.class)
    })
    public List<PythonDBCourseInfo> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<PythonDBCourseInfo> courseList) {
        this.courseList = courseList;
    }

    @Override
    public StandardCourseList toStandard() {
        StandardCourseList list = new StandardCourseList();
        list.setCourseList(new LinkedList<>());
        for (PythonDBCourseInfo info : courseList) {
            list.getCourseList().add(info.toStandard());
        }
        return list;
    }
}

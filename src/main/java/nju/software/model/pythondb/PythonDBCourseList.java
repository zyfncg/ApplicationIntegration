package nju.software.model.pythondb;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Anson Shaw on 2017/6/12.
 */
@XmlRootElement(name = "courses")
public class PythonDBCourseList {

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
}

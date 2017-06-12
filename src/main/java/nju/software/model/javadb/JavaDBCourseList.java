package nju.software.model.javadb;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Anson Shaw on 2017/6/12.
 */
@XmlRootElement(name = "listBean")
public class JavaDBCourseList {

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
}

package nju.software.model.phpdb;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Anson Shaw on 2017/6/12.
 */
@XmlRootElement(name = "courses")
public class PHPDBCourseList {

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

}

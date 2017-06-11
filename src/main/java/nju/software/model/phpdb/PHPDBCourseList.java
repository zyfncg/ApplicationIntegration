package nju.software.model.phpdb;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Anson Shaw on 2017/6/11.
 */
@XmlRootElement(name = "courses")
public class PHPDBCourseList {

    private List<PHPDBCourseInfo> courses;

    public List<PHPDBCourseInfo> getCourses() {
        return courses;
    }

    @XmlElement(name = "courses")
    public void setCourses(List<PHPDBCourseInfo> courses) {
        this.courses = courses;
    }
}

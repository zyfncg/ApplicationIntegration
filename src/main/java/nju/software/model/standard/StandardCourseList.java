package nju.software.model.standard;

import nju.software.model.javadb.JavaDBCourseInfo;
import nju.software.model.phpdb.PHPDBCourseInfo;
import nju.software.model.pythondb.PythonDBCourseInfo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Anson Shaw on 2017/6/11.
 */
@XmlRootElement(name = "courses")
public class StandardCourseList {

    private List courses;

    @XmlElements({
            @XmlElement(name = "course", type = JavaDBCourseInfo.class),
            @XmlElement(name = "course", type = PythonDBCourseInfo.class),
            @XmlElement(name = "course", type = PHPDBCourseInfo.class)
    })
    public List getCourses() {
        return courses;
    }

    public void setCourses(List courses) {
        this.courses = courses;
    }

    public Object transform(int institutionId) {
        return null;
    }
}

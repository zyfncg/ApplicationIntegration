package nju.software.model.standard;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Anson Shaw on 2017/6/11.
 */
@XmlRootElement(name = "courses")
public class StandardCourseList {

    private List<StandardCourseInfo> courses;

    public List<StandardCourseInfo> getCourses() {
        return courses;
    }

    @XmlElement(name = "course")
    public void setCourses(List<StandardCourseInfo> courses) {
        this.courses = courses;
    }

    public Object transform(int institutionId) {
        return null;
    }
}

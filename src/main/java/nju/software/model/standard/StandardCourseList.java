package nju.software.model.standard;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Anson Shaw on 2017/6/11.
 */
@XmlRootElement(name = "courseList")
public class StandardCourseList {

    private List<StandardCourseInfo> courseList;

    @XmlElements({
            @XmlElement(name = "course", type = StandardCourseInfo.class)
    })
    public List<StandardCourseInfo> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<StandardCourseInfo> courseList) {
        this.courseList = courseList;
    }
}

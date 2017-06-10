package nju.software.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by ZhangYF on 2017/6/10.
 */
@XmlRootElement(name = "course")
public class CourseBeanX extends CourseBean{
    public CourseBeanX() {
        super();
    }

    public CourseBeanX(Course course) {
        super(course);
    }
}

package nju.software.model.standard;

import nju.software.model.javadb.JavaDBCourseList;
import nju.software.model.phpdb.PHPDBCourseList;
import nju.software.model.pythondb.PythonDBCourseList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Anson Shaw on 2017/6/11.
 *
 * 标准格式的课程信息列表
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

    public JavaDBCourseList toJavaList() {
        JavaDBCourseList result = new JavaDBCourseList();
        result.setCourseList(new LinkedList<>());
        for (StandardCourseInfo info : courseList) {
            result.getCourseList().add(info.toJavaDBCourseInfo());
        }
        return result;
    }

    public PythonDBCourseList toPythonList() {
        PythonDBCourseList result = new PythonDBCourseList();
        result.setCourseList(new LinkedList<>());
        for (StandardCourseInfo info : courseList) {
            result.getCourseList().add(info.toPythonDBCourseInfo());
        }
        return result;
    }

    public PHPDBCourseList toPHPList() {
        PHPDBCourseList result = new PHPDBCourseList();
        result.setCourseList(new LinkedList<>());
        for (StandardCourseInfo info : courseList) {
            result.getCourseList().add(info.toPHPDBCourseInfo());
        }
        return result;
    }
}

package nju.software.model.pythondb;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Anson Shaw on 2017/6/11.
 */
@XmlRootElement(name = "result")
public class PythonDBCourseList {

    private String success;

    private String message;

    private List<PythonDBCourseInfo> courses;

    public String getSuccess() {
        return success;
    }

    @XmlElement(name = "success")
    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    @XmlElement(name = "message")
    public void setMessage(String message) {
        this.message = message;
    }

    public List<PythonDBCourseInfo> getCourses() {
        return courses;
    }

    @XmlElement(name = "courses")
    public void setCourses(List<PythonDBCourseInfo> courses) {
        this.courses = courses;
    }
}

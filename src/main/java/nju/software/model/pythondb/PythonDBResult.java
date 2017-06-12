package nju.software.model.pythondb;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Anson Shaw on 2017/6/11.
 */
@XmlRootElement(name = "result")
public class PythonDBResult {

    private String success;

    private String message;

    private PythonDBCourseList list;

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

    public PythonDBCourseList getList() {
        return list;
    }

    @XmlElement(name = "courses")
    public void setList(PythonDBCourseList list) {
        this.list = list;
    }
}

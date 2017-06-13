package nju.software.model.statistic;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by ZhangYF on 2017/6/10.
 */
@XmlRootElement (name = "listBean")
public class ListBean {
    private List list;

    @XmlElements({
            @XmlElement(name = "studentInfo", type = StudentInfo.class),
            @XmlElement(name = "courseInfo", type = CourseInfo.class)
    })
    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}


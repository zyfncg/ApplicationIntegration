package nju.software.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by ZhangYF on 2017/6/10.
 */
@XmlRootElement
public class ListBean {
    private List list;

    @XmlElements({
            @XmlElement(name = "course", type = CourseBeanX.class)
    })
    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}


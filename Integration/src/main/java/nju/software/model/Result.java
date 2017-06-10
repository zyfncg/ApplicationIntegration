package nju.software.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by ZhangYF on 2017/6/10.
 */
@XmlRootElement
public class Result {
    private String result;
    private String msg;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

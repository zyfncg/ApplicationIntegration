package nju.software.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Anson Shaw on 2017/6/13.
 */
@XmlRootElement(name = "result")
public class ResultBean {

    private boolean success;

    private String message;

    public ResultBean() {
    }

    public ResultBean(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

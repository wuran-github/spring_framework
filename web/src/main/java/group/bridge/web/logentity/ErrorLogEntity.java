package group.bridge.web.logentity;

import java.util.Date;

/**
 * @author wuran
 * @Created on 2019/3/20
 */
public class ErrorLogEntity {
    String method;
    String className;
    String message;
    Date date;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

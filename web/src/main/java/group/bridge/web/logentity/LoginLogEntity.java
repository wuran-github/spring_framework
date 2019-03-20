package group.bridge.web.logentity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wuran
 * @Created on 2019/3/19
 */
public class LoginLogEntity {
    String username;
    String ip;
    Date loginDate;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }
    @Override
    public String toString(){
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        String str="username:"+getUsername()+" ";
        str+="ip:"+getIp()+" ";
        str+="Date:"+format.format(getLoginDate())+" ";

        return str;
    }
}

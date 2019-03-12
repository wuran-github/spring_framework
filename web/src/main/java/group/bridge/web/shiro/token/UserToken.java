package group.bridge.web.shiro.token;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 加上了用户类型的token
 * @author wuran
 * @Created on 2019/3/5
 */
public class UserToken extends UsernamePasswordToken {
    public UserToken(String user,String password){
        super(user,password);
        setAdmin(false);
    }
    public UserToken(String user,String password,boolean isAdmin){
        super(user,password);
        setAdmin(isAdmin);
    }
    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    private boolean isAdmin = false;

}

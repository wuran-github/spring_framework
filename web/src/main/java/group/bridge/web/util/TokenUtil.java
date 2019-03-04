package group.bridge.web.util;

import io.jsonwebtoken.Claims;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;

public class TokenUtil {
    public static boolean validateToken(HttpServletRequest request, HttpServletResponse response){
        boolean result=false;
        String token= CookieUtil.getCookie(request,"token");
        if(token!=null){
            Claims claims= JwtBuilder.getClaims(token);
            String name=claims.get("name",String.class);

            //判断过期时间
            Date expiredDate=claims.getExpiration();
            if(expiredDate==null){
                result=false;
            }
            else{
                Calendar delayTime=Calendar.getInstance();
                delayTime.setTime(expiredDate);
                delayTime.add(Calendar.DATE,StaticData.TokenDelayTime);
                if(Calendar.getInstance().getTime().before(expiredDate)){
                    result=true;
                }
                else if(delayTime.getTime().before(expiredDate)){
                    setToken(name,response);
                    result=true;
                }
                else{
                    clearToken(response);
                    SessionUtil.removeSession("nav",request);
                    result=false;
                }
            }
        }
        return result;
    }
    public static void setToken(String username, HttpServletResponse response){
        String token=JwtBuilder.buildToken(username);
        CookieUtil.writeCookie(response,"token",token);
    }
    public static void clearToken(HttpServletResponse response){
        CookieUtil.writeCookie(response,"token","",0);
    }
}

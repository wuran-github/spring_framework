package group.bridge.web.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

    public static String getCookie(HttpServletRequest request, String cookieName) {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
    //写cookie，默认时间1天
    public static void writeCookie(HttpServletResponse response, String cookieName, String value){
        writeCookie(response,cookieName,value,StaticData.CookieAge);

    }
    public static void writeCookie(HttpServletResponse response, String cookieName, String value,int time) {
        Cookie cookie = new Cookie(cookieName, value);
        cookie.setPath("/");
        cookie.setMaxAge(time*24*60 * 60);
        response.addCookie(cookie);
    }

}

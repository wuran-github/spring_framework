package group.bridge.web.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {
    public static void removeSession(String attrName, HttpServletRequest request){
        HttpSession session=request.getSession();
        session.removeAttribute(attrName);
    }
    public static void setNavigation(HttpServletRequest request){
        HttpSession session=request.getSession();
        Object nav=session.getAttribute("nav");
        if(nav==null) {
            session.setAttribute("nav", XmlOperator.getUrl());
        }
    }
}

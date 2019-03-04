package group.bridge.web.interceptor;

import group.bridge.web.util.*;
import io.jsonwebtoken.Claims;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PageInterceptor implements HandlerInterceptor {
    //在这里些拦截内容
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        boolean result= TokenUtil.validateToken(request,response);
        if(!result) {
            response.sendRedirect("/login");
        }else{
           SessionUtil.setNavigation(request);
        }
        return result;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }


}

package group.bridge.web.controller;

import group.bridge.web.service.PersonService;
import group.bridge.web.util.CookieUtil;
import group.bridge.web.util.JwtBuilder;
import group.bridge.web.util.SessionUtil;
import group.bridge.web.util.TokenUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login() {
        return "login/login";
    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(String userName,String password,HttpServletResponse response){
        //test
        String testName="admin";
        String testPassword="admin";
        String url="redirect:/login";
        if(testName.equals(userName) && password.equals(testPassword)){
            TokenUtil.setToken(userName,response);
            url="redirect:/";
        }
        return url;
    }
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request,HttpServletResponse response) {
        clean(request,response);
        return "redirect:/login";
    }
    private void clean(HttpServletRequest request,HttpServletResponse response){
        //remove cookie
        TokenUtil.clearToken(response);

        //remove session
        SessionUtil.removeSession("nav",request);
    }


}

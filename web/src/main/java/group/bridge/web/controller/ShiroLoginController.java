package group.bridge.web.controller;

import group.bridge.web.annotation.LoginLog;
import group.bridge.web.shiro.token.UserToken;
import group.bridge.web.util.SessionUtil;
import group.bridge.web.util.ShiroUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wuran
 * @Created on 2019/3/4
 */
@Controller
public class ShiroLoginController {
    @RequestMapping("/login")
    public String login(){
        return "login/login";
    }
    @LoginLog
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(String name, String password, boolean admin, HttpServletRequest request){
       /**
         * 使用Shiro编写认证操作
         */

        //1.获取subject
        Subject subject = SecurityUtils.getSubject();

        //2.封装用户数据
        //UsernamePasswordToken token = new UsernamePasswordToken(name,password);
        //使用自定义的token
        UserToken token = new UserToken(name,password,admin);
        //
        SessionUtil.setNavigation(request);
        //3.执行登录方法
        //没有异常代表登录成功
        try {
            subject.login(token);
            Session session = subject.getSession();
            //session有直接gethost方法，但是暂时还没看到源码里是怎么获取host的
            session.setAttribute("ip", ShiroUtil.getIpAddress(request));
        }
        //代表用户名不存在
        catch (UnknownAccountException ex){
            System.out.println("用户名不存在");
            //ex.printStackTrace();
        }
        //代表密码错误
        catch (IncorrectCredentialsException ex){
            System.out.println("密码错误");
            //ex.printStackTrace();
        }

        //
        return "redirect:/index";
    }
}

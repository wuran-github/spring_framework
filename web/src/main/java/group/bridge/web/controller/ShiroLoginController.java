package group.bridge.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wuran
 * @Created on 2019/3/4
 */
@Controller
public class ShiroLoginController {
    @RequestMapping("/login")
    public void login(String name,String password){
        /**
         * 使用Shiro编写认证操作
         */

        //1.获取subject
        Subject subject = SecurityUtils.getSubject();

        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(name,password);

        //3.执行登录方法
        //没有异常代表登录成功
        try {
            subject.login(token);

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

    }
}

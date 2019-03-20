package group.bridge.web.aspect;

import group.bridge.web.annotation.LoginLog;
import group.bridge.web.logentity.LoginLogEntity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author wuran
 * @Created on 2019/3/19
 */
@Aspect
@Component
public class LoginLogAspect {
    @Pointcut("@annotation(group.bridge.web.annotation.LoginLog)")
    public void logPointCut(){}
    //代表返回结果后通知
    @AfterReturning(value = "logPointCut()",returning = "result")
    public void afterRunning(JoinPoint point,Object result)throws Throwable{
        Boolean successful = (Boolean)result;
        if(successful) {
            saveLog(point);
        }
    }


    private void saveLog(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        //获取到method
        Method method = signature.getMethod();


        //创建log实体
        LoginLogEntity log = new LoginLogEntity();
        //获取到注解
        LoginLog loginLog = method.getAnnotation(LoginLog.class);

        //获取注解的参数


        //通过shiro的方法获取username
        Subject subject = SecurityUtils.getSubject();
        String name = (String)subject.getPrincipal();
        //获取ip
        Session session = subject.getSession();
        String ip = session.getHost();

        String ip2 = (String)session.getAttribute("ip");

        //请求的 类名、方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();

        log.setIp(ip);
        log.setLoginDate(new Date());
        log.setUsername(name);
        System.out.println(log.toString());



    }
}

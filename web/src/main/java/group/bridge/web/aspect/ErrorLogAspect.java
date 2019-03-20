package group.bridge.web.aspect;

import group.bridge.web.annotation.GuidLog;
import group.bridge.web.logentity.ErrorLogEntity;
import group.bridge.web.logentity.SysLog;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author wuran
 * @Created on 2019/3/20
 */
@Aspect
@Component
public class ErrorLogAspect {
    @Pointcut("execution(* group.bridge.web.controller..*(..))")
    public void errorPointCut(){}
    @AfterThrowing(value = "errorPointCut()",throwing = "ex")
    public void afterThrowing(JoinPoint point, Exception ex){
        saveErrorLog(point,ex);
    }
    private void saveErrorLog(JoinPoint joinPoint, Exception ex){
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        //获取到method
        Method method = signature.getMethod();

        //创建log实体
        ErrorLogEntity log = new ErrorLogEntity();
        //获取到注解
//        GuidLog guidLog = method.getAnnotation(GuidLog.class);



        //请求的 类名、方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();

        log.setDate(new Date());
        log.setClassName(className);
        log.setMethod(methodName);
        log.setMessage(ex.getMessage());

        System.out.println(log.toString());



    }
}

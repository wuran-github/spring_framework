package group.bridge.web.aspect;

import group.bridge.web.annotation.GuidLog;
import group.bridge.web.logentity.SysLog;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
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
public class GuidLogAspect {
    @Pointcut("@annotation(group.bridge.web.annotation.GuidLog)")
    public void logPointCut(){}

    //代表环绕通知
    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point)throws Throwable{
        //执行方法，around可以控制方法的执行
        Object result = point.proceed();
        saveLog(point);
        return result;
    }


    private void saveLog(ProceedingJoinPoint joinPoint){
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        //获取到method
        Method method = signature.getMethod();


        //创建log实体
        SysLog sysLog = new SysLog();
        //获取到注解
        GuidLog guidLog = method.getAnnotation(GuidLog.class);

        //获取注解的参数
        String operationType = guidLog.operationType();
        String table = guidLog.table();

        //通过shiro的方法获取username
        Subject subject = SecurityUtils.getSubject();
        String name = (String)subject.getPrincipal();


        //请求的 类名、方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();

        sysLog.setDate(new Date());
        sysLog.setOperationType(operationType);
        sysLog.setTable(table);
        sysLog.setOperator(name);

        System.out.println(sysLog.toString());



    }
}

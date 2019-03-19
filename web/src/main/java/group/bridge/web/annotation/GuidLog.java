package group.bridge.web.annotation;

import java.lang.annotation.*;

/**
 * @author wuran
 * @Created on 2019/3/19
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GuidLog {
    String table() default "";
    String operationType() default "";
}

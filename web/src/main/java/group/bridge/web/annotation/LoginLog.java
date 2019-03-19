package group.bridge.web.annotation;

import java.lang.annotation.*;

/**
 * @author wuran
 * @Created on 2019/3/19
 */
@Target(ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface LoginLog {
}

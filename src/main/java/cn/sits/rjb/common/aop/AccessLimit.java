package cn.sits.rjb.common.aop;

import java.lang.annotation.*;

/**
 * @author Neal.Sun
 * @version V1.0
 * @Package cn.sits.rjb.aop
 * @date 2020/04/15 10:50
 */
@Inherited
@Documented
@Target({ElementType.FIELD,ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessLimit {
    int limit() default 5;
    int sec() default 5;
}
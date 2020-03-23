package cn.sits.rjb.common.aop;

/**
 * @author Neal.Sun
 * @version V1.0
 * @Package cn.sits.rjb.common.aop
 * @date 2020/03/23 14:13
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 限制登录次数
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Limit {

    /**
     * 名称，自定义使用位置，可用来区分
     */
    String name() default "";

    /**
     * 资源的key
     */
    String key() default "";

    /**
     * 前缀
     */
    String prefix() default "login_limit_";

    /**
     * 默认限制次数
     * 例：用在登录上即限制三次登录失败，就锁定账号一段时间
     */
    int count() default 3;

    /**
     * 默认限制时间，半个小时之内不能解除封印
     */
    int period() default 1800;

    /**
     * 限制类型
     */
//    SysEnum.LimitType limitType() default SysEnum.LimitType.IP;
}
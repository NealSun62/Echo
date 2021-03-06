package cn.sits.rjb.config.authentication;

import java.lang.annotation.*;

/**
 * 忽略安全性检查
 *
 * @since 1.0.0
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreAuthentication {
}

package com.leekitman.kylin.framework.annotation;

import java.lang.annotation.*;

/**
 * 切面注解
 *
 * @author LeeKITMAN
 * @see 2018/5/2 15:41
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {

    /**
     * 注解
     */
    Class<? extends Annotation> value();
}

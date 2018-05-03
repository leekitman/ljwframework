package com.leekitman.kylin.framework;

import com.leekitman.kylin.framework.helper.*;
import com.leekitman.kylin.framework.util.ClassUtil;

/**
 * 加载相应的 Helper 类
 *
 * @author LeeKITMAN
 * @see 2018/4/30 23:47
 */
public final class HelperLoader {

    public static void init() {
        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
                AopHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };
        for (Class<?> cls :
                classList) {
            ClassUtil.loadClass(cls.getName(), true);
        }
    }
}

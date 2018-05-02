package com.leekitman.practise.chapter2.aspect;

import com.leekitman.kylin.framework.annotation.Aspect;
import com.leekitman.kylin.framework.annotation.Controller;
import com.leekitman.kylin.framework.proxy.AspectProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @author LeeKITMAN
 * @see 2018/5/2 16:51
 */
@Aspect(Controller.class)
public class ControllerAspect extends AspectProxy {

    private static final Logger LOG = LoggerFactory.getLogger(ControllerAspect.class);

    private long begin;

    @Override
    public void before(Class<?> cls, Method method, Object[] params) throws Throwable {
        LOG.debug("-------------------begin-----------------------");
        LOG.debug(String.format("class: %s", cls.getName()));
        LOG.debug(String.format("method: %s", method.getName()));
        begin = System.currentTimeMillis();
    }

    @Override
    public void after(Class<?> cls, Method method, Object[] params, Object result) throws Throwable {
        LOG.debug(String.format("time: %dms", System.currentTimeMillis() - begin));
        LOG.debug("-------------------end-------------------------");
    }
}

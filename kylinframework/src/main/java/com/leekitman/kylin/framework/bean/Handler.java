package com.leekitman.kylin.framework.bean;

import java.lang.reflect.Method;

/**
 * 封装 Action 信息
 *
 * @author LeeKITMAN
 * @see 2018/4/30 21:58
 */
public class Handler {

    /**
     * Controller 类
     */
    private Class<?> controllerClass;

    /**
     * Action 方法
     */
    private Method actionMethod;

    public Handler(Class<?> controllerClass, Method actionMethod) {
        this.controllerClass = controllerClass;
        this.actionMethod = actionMethod;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }
}

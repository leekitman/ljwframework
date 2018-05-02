package com.leekitman.kylin.framework.helper;

import com.leekitman.kylin.framework.annotation.Aspect;
import com.leekitman.kylin.framework.proxy.AspectProxy;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 切面助手类
 *
 * @author LeeKITMAN
 * @see 2018/5/2 19:23
 */
public final class AopHelper {

    private static Set<Class<?>> createTargetClassSet(Aspect aspect) throws  Exception{
        Set<Class<?>> targetClassSet = new HashSet<Class<?>>();
        if(aspect != null){
            Class<? extends Annotation> annotation = aspect.value();
            if(!annotation.equals(Aspect.class)){
                targetClassSet.addAll(ClassHelper.getClassSetByAnnotation(annotation));
            }
        }
        return targetClassSet;
    }

    private static Map<Class<?>, Set<Class<?>>> createProxyMap() throws Exception {
        Map<Class<?>, Set<Class<?>>> proxyMap = new HashMap<Class<?>, Set<Class<?>>>();
        Set<Class<?>> proxyClassSet = ClassHelper.getClassSetBySuper(AspectProxy.class);
        for (Class<?> proxyClass :
                proxyClassSet) {
            if (proxyClass.isAnnotationPresent(Aspect.class)){
                Aspect aspect = proxyClass.getAnnotation(Aspect.class);
                Set<Class<?>> targetClassSet = createTargetClassSet(aspect);
                proxyMap.put(proxyClass, targetClassSet);
            }
        }
        return proxyMap;
    }
}

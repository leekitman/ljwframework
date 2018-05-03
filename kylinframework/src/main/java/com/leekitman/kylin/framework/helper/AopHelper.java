package com.leekitman.kylin.framework.helper;

import com.leekitman.kylin.framework.annotation.Aspect;
import com.leekitman.kylin.framework.annotation.Service;
import com.leekitman.kylin.framework.proxy.AspectProxy;
import com.leekitman.kylin.framework.proxy.Proxy;
import com.leekitman.kylin.framework.proxy.ProxyManager;
import com.leekitman.kylin.framework.proxy.TransactionProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.*;

/**
 * 切面助手类
 *
 * @author LeeKITMAN
 * @see 2018/5/2 19:23
 */
public final class AopHelper {

    private static final Logger LOG = LoggerFactory.getLogger(AopHelper.class);

    static {
        try {
            Map<Class<?>, Set<Class<?>>> proxyMap = createProxyMap();
            Map<Class<?>, List<Proxy>> targetMap = createTargetMap(proxyMap);
            for (Map.Entry<Class<?>, List<Proxy>> targetEntry :
                    targetMap.entrySet()) {
                // 连接点
                Class<?> targetClass = targetEntry.getKey();
                // 切面列表
                List<Proxy> proxyList = targetEntry.getValue();
                // 创造代理类，存入bean，当依赖注入的时候，注入的其实是代理类
                Object proxy = ProxyManager.createProxy(targetClass, proxyList);
                BeanHelper.setBean(targetClass, proxy);
            }
        } catch (Exception e) {
            LOG.error("aop failure", e);
        }
    }

    /**
     * @param aspect 带有值的Aspect注解，他的值是另一个注解
     * @return 返回标注了‘另一个注解’的所有类。
     */
    private static Set<Class<?>> createTargetClassSet(Aspect aspect) throws Exception {
        Set<Class<?>> targetClassSet = new HashSet<Class<?>>();
        if (aspect != null) {
            Class<? extends Annotation> annotation = aspect.value();
            if (!annotation.equals(Aspect.class)) {
                targetClassSet.addAll(ClassHelper.getClassSetByAnnotation(annotation));
            }
        }
        return targetClassSet;
    }

    /**
     * 在所有继承了AspectProxy抽象类的子类中，抽取出标注了@Aspect注解的子类，并遍历这些子类，
     * 将每个子类与标注了该子类上@Aspect注解的值——另一个注解的所有类关联起来，存为一个Map。
     *
     * @return 返回切面与连接点的映射Map
     */
    private static Map<Class<?>, Set<Class<?>>> createProxyMap() throws Exception {
        Map<Class<?>, Set<Class<?>>> proxyMap = new HashMap<Class<?>, Set<Class<?>>>();
        // 增加@Aspect注解式代理
        addAspectProxy(proxyMap);
        // 增加事务代理
        addTransactionProxy(proxyMap);
        return proxyMap;
    }

    /**
     * 将传入的“切面与连接点的映射Map”改造成“连接点与切面列表的映射Map”
     *
     * @param proxyMap 所有切面和连接点的映射Map
     */
    private static Map<Class<?>, List<Proxy>> createTargetMap(Map<Class<?>, Set<Class<?>>> proxyMap) throws Exception {
        Map<Class<?>, List<Proxy>> targetMap = new HashMap<Class<?>, List<Proxy>>();
        for (Map.Entry<Class<?>, Set<Class<?>>> proxyEntry :
                proxyMap.entrySet()) {
            Class<?> proxyClass = proxyEntry.getKey();
            Set<Class<?>> targetClassSet = proxyEntry.getValue();
            for (Class<?> targetClass :
                    targetClassSet) {
                Proxy proxy = (Proxy) proxyClass.newInstance();
                if (targetMap.containsKey(targetClass)) {
                    targetMap.get(targetClass).add(proxy);
                } else {
                    List<Proxy> proxyList = new ArrayList<Proxy>();
                    proxyList.add(proxy);
                    targetMap.put(targetClass, proxyList);
                }
            }
        }
        return targetMap;
    }

    /**
     * 增加@Aspect注解的代理
     */
    private static void addAspectProxy(Map<Class<?>, Set<Class<?>>> proxyMap) throws Exception {
        Set<Class<?>> proxyClassSet = ClassHelper.getClassSetBySuper(AspectProxy.class);
        for (Class<?> proxyClass :
                proxyClassSet) {
            if (proxyClass.isAnnotationPresent(Aspect.class)) {
                Aspect aspect = proxyClass.getAnnotation(Aspect.class);
                Set<Class<?>> targetClassSet = createTargetClassSet(aspect);
                proxyMap.put(proxyClass, targetClassSet);
            }
        }
    }

    /**
     * 增加事务代理
     */
    private static void addTransactionProxy(Map<Class<?>, Set<Class<?>>> proxyMap){
        Set<Class<?>> serviceClassSet = ClassHelper.getClassSetByAnnotation(Service.class);
        proxyMap.put(TransactionProxy.class,serviceClassSet);
    }
}

package com.leekitman.kylin.framework.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 代理管理器
 *
 * @author LeeKITMAN
 * @see 2018/5/2 16:15
 */
public class ProxyManager {

    @SuppressWarnings("unchecked")
    public static <T> T createProxy(final Class<?> targetClass, final List<Proxy> proxyList){
        return (T) Enhancer.create(targetClass, new MethodInterceptor() {
            public Object intercept(Object targetObject, Method targetMethod, Object[] methodParams, MethodProxy methodProxy) throws Throwable {
                // 此处应有before(); methodProxy.invokeSuper(targetObject,methodParams); after();
                return new ProxyChain(targetClass, targetObject,targetMethod,methodProxy, methodParams, proxyList).doProxyChain();
            }
        });
    }
}

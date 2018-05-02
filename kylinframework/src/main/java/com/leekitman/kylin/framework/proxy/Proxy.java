package com.leekitman.kylin.framework.proxy;

/**
 * 代理接口
 *
 * @author LeeKITMAN
 * @see 2018/5/2 15:50
 */
public interface Proxy {

    Object doProxy(ProxyChain proxyChain) throws Throwable;
}

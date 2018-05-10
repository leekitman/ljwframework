package com.leekitman.kylin.plugin.security;

import org.apache.shiro.web.env.EnvironmentLoaderListener;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Set;

/**
 * @author LeeKITMAN
 * @see 2018/5/10 15:17
 */
public class KylinSecurityPlugin implements ServletContainerInitializer {
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        // 设置初始化参数
        servletContext.setInitParameter("shiroConfigLocations", "classpath:kylin-security.ini");
        // 注册 Listener
        servletContext.addListener(EnvironmentLoaderListener.class);
        // 注册 Filter
        FilterRegistration.Dynamic kylinSecurityFilter = servletContext.addFilter("KylinSecurityFilter", KylinSecurityFilter.class);
        kylinSecurityFilter.addMappingForUrlPatterns(null, false, "/*");
    }
}

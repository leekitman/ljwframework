package com.leekitman.kylin.plugin.security;

/**
 * 常量接口
 *
 * @author LeeKITMAN
 * @see 2018/5/10 16:14
 */
public interface SecurityConstant {
    String REALMS = "kylin.plugin.security.realms";
    String REALMS_JDBC = "jdbc";
    String REALMS_CUSTOM = "custom";

    String KYLIN_SECURITY = "kylin.plugin.security.custom.class";

    String JDBC_AUTHC_QUERY = "kylin.plugin.security.jdbc.authc_query";
    String JDBC_ROLES_QUERY = "kylin.plugin.security.jdbc.roles_query";
    String JDBC_PERMISSIONS_QUERY = "kylin.plugin.security.jdbc.permissions_query";

    String CACHEABLE = "smart.plugin.security.cacheable";
}

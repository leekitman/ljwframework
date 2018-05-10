package com.leekitman.kylin.plugin.security;

import com.leekitman.kylin.framework.ConfigHelper;
import com.leekitman.kylin.framework.util.ReflectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 从配置文件中获取相关属性
 *
 * @author LeeKITMAN
 * @see 2018/5/10 16:03
 */
public final class SecurityConfig {

    private static final Logger LOG = LoggerFactory.getLogger(SecurityConfig.class);

    public static String getRealms() {
        return ConfigHelper.getString(SecurityConstant.REALMS);
    }

    public static KylinSecurity getKylinSecurity() {
        String className = ConfigHelper.getString(SecurityConstant.KYLIN_SECURITY);
        Class<?> cls = null;
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            LOG.error("无法从 " + SecurityConstant.KYLIN_SECURITY + " 配置中找到对应的类", e);
        }
        KylinSecurity kylinSecurity = null;
        if (cls != null) {
            try {
                kylinSecurity = (KylinSecurity) cls.newInstance();
            } catch (Exception e) {
                LOG.error(KylinSecurity.class.getSimpleName() + " 实例化异常", e);
            }
        }
        return kylinSecurity;
    }

    public static String getJdbcAuthcQuery() {
        return ConfigHelper.getString(SecurityConstant.JDBC_AUTHC_QUERY);
    }

    public static String getJdbcRolesQuery() {
        return ConfigHelper.getString(SecurityConstant.JDBC_ROLES_QUERY);
    }

    public static String getJdbcPermissionsQuery(){
        return ConfigHelper.getString(SecurityConstant.JDBC_PERMISSIONS_QUERY);
    }

    public static boolean isCacheable(){
        return ConfigHelper.getBoolean(SecurityConstant.CACHEABLE);
    }
}

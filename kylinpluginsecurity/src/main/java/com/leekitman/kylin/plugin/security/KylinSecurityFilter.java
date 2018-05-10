package com.leekitman.kylin.plugin.security;

import com.leekitman.kylin.plugin.security.realm.KylinCustomRealm;
import com.leekitman.kylin.plugin.security.realm.KylinJdbcRealm;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.CachingSecurityManager;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.ShiroFilter;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author LeeKITMAN
 * @see 2018/5/10 15:25
 */
public class KylinSecurityFilter extends ShiroFilter {

    @Override
    public void init() throws Exception {
        super.init();
        WebSecurityManager webSecurityManager = super.getSecurityManager();
        // 设置 Realm ，可同时支持多个 Realm，并按照先后顺序用逗号分割
        setRealms(webSecurityManager);
        // 设置 Cache，用于减少数据库查询次数，降低 I/O 访问
        setCache(webSecurityManager);
    }

    private void setRealms(WebSecurityManager webSecurityManager) {
        // 读取 kylin.plugin.security.realms 配置项
        String securityRealms = SecurityConfig.getRealms();
        if (securityRealms != null) {
            // 根据逗号进行拆分
            String[] securityRealmArray = securityRealms.split(",");
            if (securityRealmArray.length > 0) {
                // 使 Realm 具备唯一性与顺序性
                Set<Realm> realms = new LinkedHashSet<Realm>();
                for (String securityRealm :
                        securityRealmArray) {
                    if (securityRealm.equalsIgnoreCase(SecurityConstant.REALMS_JDBC)) {
                        // 添加基于 JDBC 的 Realm，需配置相关 SQL 查询语句
                        addJdbcRealm(realms);
                    } else if (securityRealm.equalsIgnoreCase(SecurityConstant.REALMS_CUSTOM)) {
                        // 添加基于定制化的 Realm，需实现 KylinSecurity 接口
                        addCustomRealm(realms);
                    }
                }
                RealmSecurityManager realmSecurityManager = (RealmSecurityManager) webSecurityManager;
                realmSecurityManager.setRealms(realms); // 设置 Realm
            }
        }
    }

    private void addCustomRealm(Set<Realm> realms) {
        // 读取 kylin.plugin.security.custom.class 配置项
        KylinSecurity kylinSecurity = SecurityConfig.getKylinSecurity();
        // 添加自己实现的 Realm
        KylinCustomRealm kylinCustomRealm = new KylinCustomRealm(kylinSecurity);
        realms.add(kylinCustomRealm);
    }

    private void addJdbcRealm(Set<Realm> realms) {
        // 添加自己实现的基于 JDBC 的 Realm
        KylinJdbcRealm kylinJdbcRealm = new KylinJdbcRealm();
        realms.add(kylinJdbcRealm);
    }

    private void setCache(WebSecurityManager webSecurityManager) {
        // 读取 kylin.plugin.security.cacheable 配置项
        if (SecurityConfig.isCacheable()) {
            CachingSecurityManager cachingSecurityManager = (CachingSecurityManager) webSecurityManager;
            // 使用基于内存的 CacheManager
            MemoryConstrainedCacheManager cacheManager = new MemoryConstrainedCacheManager();
            cachingSecurityManager.setCacheManager(cacheManager);
        }
    }
}

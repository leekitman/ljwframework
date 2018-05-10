package com.leekitman.kylin.plugin.security.realm;

import com.leekitman.kylin.framework.helper.DatabaseHelper;
import com.leekitman.kylin.plugin.security.SecurityConfig;
import com.leekitman.kylin.plugin.security.password.Md5CredentialsMatcher;
import org.apache.shiro.realm.jdbc.JdbcRealm;

/**
 * 基于 Kylin 的 JDBC Realm （需要提供相关 kylin.plugin.security.jdbc.* 配置项）
 *
 * @author LeeKITMAN
 * @see 2018/5/10 17:42
 */
public class KylinJdbcRealm extends JdbcRealm {

    public KylinJdbcRealm() {
        super.setDataSource(DatabaseHelper.getDataSource());
        super.setAuthenticationQuery(SecurityConfig.getJdbcAuthcQuery());
        super.setUserRolesQuery(SecurityConfig.getJdbcRolesQuery());
        super.setPermissionsQuery(SecurityConfig.getJdbcPermissionsQuery());
        super.setPermissionsLookupEnabled(true);
        super.setCredentialsMatcher(new Md5CredentialsMatcher());   // 使用 MD5 加密算法
    }
}

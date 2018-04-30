package com.leekitman.kylin.framework;

/**
 * 提供相关配置项常量
 *
 * @author  LeeKITMAN
 * @see 2018/4/30 11:20
 */
public interface ConfigConstant {

    String CONFIG_FILE = "kylin.properties";

    String JDBC_DRIVER = "kylin.framework.jdbc.driver";
    String JDBC_URL = "kylin.framework.jdbc.url";
    String JDBC_USERNAME = "kylin.framework.jdbc.username";
    String JDBC_PASSWORD = "kylin.framework.jdbc.password";

    String APP_BASE_PACKAGE = "kylin.framework.app.base_package";
    String APP_JSP_PATH = "kylin.framework.app.jsp_path";
    String APP_ASSET_PATH = "kylin.framework.app.asset_path";
}

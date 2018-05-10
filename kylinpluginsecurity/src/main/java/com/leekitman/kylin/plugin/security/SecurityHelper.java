package com.leekitman.kylin.plugin.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Security 助手类
 *
 * @author LeeKITMAN
 * @see 2018/5/10 20:01
 */
public final class SecurityHelper {

    private static final Logger LOG = LoggerFactory.getLogger(SecurityHelper.class);

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     */
    public static void login(String username, String password) {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser != null) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            try {
                currentUser.login(token);
            } catch (AuthenticationException e) {
                LOG.error("login failure", e);
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 注销
     */
    public static void logout() {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser != null) {
            currentUser.logout();
        }
    }
}

package com.leekitman.kylin.plugin.soap;

import com.leekitman.kylin.framework.ConfigHelper;

/**
 * 从配置文件中获取相关属性
 *
 * @author LeeKITMAN
 * @version 1.0.0
 */
public class SoapConfig {

    public static boolean isLog() {
        return ConfigHelper.getBoolean(SoapConstant.LOG);
    }
}

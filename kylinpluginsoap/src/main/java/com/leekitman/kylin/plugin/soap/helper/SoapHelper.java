package com.leekitman.kylin.plugin.soap.helper;

import com.leekitman.kylin.plugin.soap.SoapConfig;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.message.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * SOAP 助手类
 *
 * @author LeeKITMAN
 * @version 1.0.0
 */
public class SoapHelper {

    private static final List<Interceptor<? extends Message>> inInterceptorList = new ArrayList<Interceptor<? extends Message>>();
    private static final List<Interceptor<? extends Message>> outInterceptorList = new ArrayList<Interceptor<? extends Message>>();

    static {
        // 添加 Logging Interceptor
        if(SoapConfig.isLog()){
            new LoggingInInterceptor();
        }
    }
}

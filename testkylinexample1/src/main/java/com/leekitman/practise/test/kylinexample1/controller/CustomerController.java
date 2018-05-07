package com.leekitman.practise.test.kylinexample1.controller;

import com.leekitman.kylin.framework.annotation.Action;
import com.leekitman.kylin.framework.annotation.Controller;
import com.leekitman.kylin.framework.bean.Param;

import javax.xml.crypto.Data;
import java.util.Map;

/**
 * @author LeeKITMAN
 * @see 2018/5/5 17:35
 */
@Controller
public class CustomerController {

    @Action("post:/customer_create")
    public Data createSubmit(Param param){
        Map<String, Object> fieldMap = param.getMap();
        return null;
    }
}

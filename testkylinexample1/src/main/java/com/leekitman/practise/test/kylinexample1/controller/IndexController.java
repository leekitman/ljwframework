package com.leekitman.practise.test.kylinexample1.controller;

import com.leekitman.kylin.framework.annotation.Action;
import com.leekitman.kylin.framework.annotation.Controller;
import com.leekitman.kylin.framework.annotation.Inject;
import com.leekitman.kylin.framework.bean.Data;
import com.leekitman.kylin.framework.bean.Param;
import com.leekitman.kylin.framework.bean.View;
import com.leekitman.practise.test.kylinexample1.service.IndexService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author LeeKITMAN
 * @see 2018/5/4 15:16
 */
@Controller
public class IndexController {

    @Inject
    private IndexService indexService;

    @Action("get:/index")
    public Data index(){
        return new Data("test kylin exmple 1 is running...");
    }

    @Action("get:/indexjsp")
    public View indexjsp(Param param){
        String currentTime = indexService.getCurrentTime();
        View view = new View("hello.jsp");
        view.addModel("currentTime",currentTime);
        return view;
    }
}

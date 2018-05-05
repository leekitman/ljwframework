package com.leekitman.practise.test.kylinexample1.service;

import com.leekitman.kylin.framework.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author LeeKITMAN
 * @see 2018/5/4 16:04
 */
@Service
public class IndexService {

    private static final Logger LOG = LoggerFactory.getLogger(IndexService.class);

    public String getCurrentTime() {
        LOG.info("进入了IndexService");
        DateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dataFormat.format(new Date());
    }
}

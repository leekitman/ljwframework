package com.leekitman.kylin.framework.bean;

import com.leekitman.kylin.framework.util.CastUtil;
import com.leekitman.kylin.framework.util.CollectionUtil;

import java.util.Map;

/**
 * 请求参数对象
 *
 * @author LeeKITMAN
 * @see 2018/4/30 23:53
 */
public class Param {

    private Map<String, Object> paramMap;

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    /**
     * 根据参数名获取 long 型参数值
     */
    public long getLong(String name) {
        return CastUtil.castLong(paramMap.get(name));
    }

    /**
     * 获取所有字段信息
     */
    public Map<String, Object> getMap() {
        return paramMap;
    }

    /**
     * 判断是否为空
     */
    public boolean isEmpty() {
        return CollectionUtil.isEmpty(paramMap);
    }
}
package com.leekitman.kylin.framework.bean;

/**
 * 返回数据对象
 *
 * @author LeeKITMAN
 * @see 2018/5/1 0:03
 */
public class Data {

    /**
     * 模型数据
     */
    private Object model;

    public Data(Object model) {
        this.model = model;
    }

    public Object getModel() {
        return model;
    }
}

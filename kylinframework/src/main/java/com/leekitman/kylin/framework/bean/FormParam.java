package com.leekitman.kylin.framework.bean;

/**
 * @author LeeKITMAN
 * @see 2018/5/7 15:35
 */
public class FormParam {

    private String fieldName;
    private Object fieldValue;

    public FormParam(String fieldName, Object fielValue) {
        this.fieldName = fieldName;
        this.fieldValue = fielValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}

package com.leekitman.practise.ljwframework.service;

import com.leekitman.practise.ljwframework.helper.DatabaseHelper;
import com.leekitman.practise.ljwframework.model.Customer;

import java.util.List;
import java.util.Map;

/**
 * 提供客户数据服务
 */
public class CustomerService {

    /**
     * 获取客户列表
     * @return 客户列表
     */
    public List<Customer> getCustomerList(){
        String sql = "SELECT * FROM customer";
        return DatabaseHelper.queryEntityList(Customer.class,sql);
    }

    /**
     * 获取客户
     * @param id 客户记录id
     * @return 客户实体
     */
    public Customer getCustomer(Long id){
        String sql = "SELECT * FROM customer WHERE id=?";
        return DatabaseHelper.queryEntity(Customer.class,sql,id);
    }

    /**
     * 创建客户
     * @param fieldMap 字段map
     * @return 创建成功则返回true，否则返回false
     */
    public Boolean createCustomer(Map<String,Object> fieldMap){
        return DatabaseHelper.insertEntity(Customer.class,fieldMap);
    }

    /**
     * 更新客户
     * @param id 更新的目标id
     * @param fieldMap 更新的字段map
     * @return 更新成功则返回true，否则返回false
     */
    public Boolean updateCustomer(Long id, Map<String,Object> fieldMap){
        return DatabaseHelper.updateEntity(Customer.class,id,fieldMap);
    }

    /**
     * 删除客户
     * @param id 删除目标的id
     * @return 删除成功则返回true，否则返回false
     */
    public Boolean deleteCustomer(Long id){
        return DatabaseHelper.deleteEntity(Customer.class,id);
    }
}

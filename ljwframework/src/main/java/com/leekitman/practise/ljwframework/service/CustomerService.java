package com.leekitman.practise.ljwframework.service;

import com.leekitman.practise.ljwframework.model.Customer;

import java.util.List;
import java.util.Map;

/**
 * 提供客户数据服务
 */
public class CustomerService {
    /**
     * 获取客户列表
     * @param keyword
     * @return
     */
    public List<Customer> getCustomerList(String keyword){
        // TODO
        return null;
    }

    /**
     * 获取客户
     * @param id
     * @return
     */
    public Customer getCustomer(Long id){
        // TODO
        return null;
    }

    /**
     * 创建客户
     * @param fielMap
     * @return
     */
    public Boolean createCustomer(Map<String,Object> fielMap){
        // TODO
        return false;
    }

    /**
     * 更新客户
     * @param id
     * @param fielMap
     * @return
     */
    public Boolean updateCustomer(Long id, Map<String,Object> fielMap){
        // TODO
        return false;
    }

    public Boolean deleteCustomer(Long id){
        // TODO
        return false;
    }
}

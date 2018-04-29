package com.leekitman.practise.ljwframework.service;

import com.alibaba.fastjson.JSON;
import com.leekitman.practise.ljwframework.helper.DatabaseHelper;
import com.leekitman.practise.ljwframework.model.Customer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CustomerService 单元测试
 */
public class CustomerServiceTest {
    private final CustomerService customerService;

    public CustomerServiceTest(){
        customerService = new CustomerService();
    }

    @Before
    public void init(){
        DatabaseHelper.executeSqlFile("sql/customer_init.sql");
    }

    @Test
    public void getCustomerListTest(){
        List<Customer> customerList = customerService.getCustomerList();
        System.out.println("=============================================================");
        System.out.println(JSON.toJSONString(customerList));
        System.out.println("=============================================================");
        Assert.assertEquals(2,customerList.size());
    }

    @Test
    public void getCustomerTest(){
        Long id = 1L;
        Customer customer = customerService.getCustomer(id);
        System.out.println("=============================================================");
        System.out.println(JSON.toJSONString(customer));
        System.out.println("=============================================================");
        Assert.assertNotNull(customer);
    }

    @Test
    public void createCustomerTest(){
        Map<String,Object> fieldMap = new HashMap<>();
        fieldMap.put("name","customer100");
        fieldMap.put("contact","John");
        fieldMap.put("telephone","13658455865");
        Boolean customer = customerService.createCustomer(fieldMap);
        Assert.assertTrue(customer);
    }

    @Test
    public void updateCustomerTest(){
        Long id = 1L;
        Map<String,Object> fieldMap = new HashMap<>();
        fieldMap.put("contact","Eric");
        Boolean aBoolean = customerService.updateCustomer(id, fieldMap);
        Assert.assertTrue(aBoolean);
    }

    @Test
    public void deleteCustomerTest(){
        Long id = 1L;
        Boolean aBoolean = customerService.deleteCustomer(id);
        Assert.assertTrue(aBoolean);
    }
}

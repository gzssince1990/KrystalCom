package com.krystal.dao;

import com.krystal.model.Customer;

import java.util.List;

/**
 * Created by zhisong on 3/8/2016.
 */
public interface CustomerDAO {
    public void saveOrUpdate(Customer customer);
    public void delete(int customer_id);
    public Customer get(int customer_id);
    public List<Customer> list();
}

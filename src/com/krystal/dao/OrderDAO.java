package com.krystal.dao;



import com.krystal.model.Order;

import java.util.List;

/**
 * Created by zhisong on 3/9/2016.
 */
public interface OrderDAO {
    public void saveOrUpdate(Order order);
    public void delete(int order_id);
    public Order get(int order_id);
    public List<Order> list();
}

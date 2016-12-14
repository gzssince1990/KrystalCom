package com.krystal.dao;

import com.krystal.model.Product;

import java.util.List;

/**
 * Created by zhisong on 3/9/2016.
 */
public interface ProductDAO {
    public int saveOrUpdate(Product product);
    public void delete(int product_id);
    public Product get(int product_id);
    public List<Product> list();
}

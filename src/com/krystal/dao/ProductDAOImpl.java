package com.krystal.dao;

import com.krystal.model.Product;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by zhisong on 3/9/2016.
 */
public class ProductDAOImpl implements ProductDAO {

    private JdbcTemplate jdbcTemplate;

    public ProductDAOImpl(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void saveOrUpdate(Product product) {
        if (product.getProduct_id() > 0){
            //update
            String sql = "UPDATE product set brand=?, name=?, " +
                    "color=?, size=?, description=?, cost_usd=?," +
                    "cost_chy=?, price_chy=? WHERE product_id=?";
            jdbcTemplate.update(sql, product.getBrand(), product.getName(),
                    product.getColor(), product.getSize(), product.getDescription(),
                    product.getCost_usd(), product.getCost_chy(),
                    product.getPrice_chy(), product.getProduct_id());
        } else {
            //insert
            String sql = "INSERT INTO product (brand, name, color, size, description, " +
                    "cost_usd, cost_chy, price_chy) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql, product.getBrand(), product.getName(),
                    product.getColor(), product.getSize(), product.getDescription(),
                    product.getCost_usd(), product.getCost_chy(),
                    product.getPrice_chy());
        }
    }

    public void delete(int product_id) {
        String sql = "DELETE FROM product WHERE product_id=?";
        jdbcTemplate.update(sql, product_id);
    }

    public Product get(int product_id) {
        String sql = "SELECT * FROM product WHERE product_id=" + product_id;
        return jdbcTemplate.query(sql, new ResultSetExtractor<Product>() {
            public Product extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if (resultSet.next()){
                    Product product = new Product(resultSet.getString("brand"), resultSet.getString("name"),
                            resultSet.getString("color"), resultSet.getString("size"),
                            resultSet.getString("description"), resultSet.getFloat("cost_usd"),
                            resultSet.getFloat("cost_chy"), resultSet.getFloat("price_chy"));
                    product.setProduct_id(resultSet.getInt("product_id"));
                    return product;
                }

                return null;
            }
        });
    }

    public List<Product> list() {
        String sql = "SELECT * FROM product";
        List<Product> productList = jdbcTemplate.query(sql, new RowMapper<Product>() {
            public Product mapRow(ResultSet resultSet, int i) throws SQLException {
                Product product = new Product(resultSet.getString("brand"), resultSet.getString("name"),
                        resultSet.getString("color"), resultSet.getString("size"),
                        resultSet.getString("description"), resultSet.getFloat("cost_usd"),
                        resultSet.getFloat("cost_chy"), resultSet.getFloat("price_chy"));
                product.setProduct_id(resultSet.getInt("product_id"));
                return product;
            }
        });

        return productList;
    }
}

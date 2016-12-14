package com.krystal.dao;

import com.krystal.model.Product;
import com.mysql.jdbc.Statement;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

    public int saveOrUpdate(final Product product) {
        if (product.getProduct_id() > 0){
            //update
            String sql = "UPDATE product set brand=?, name=?, " +
                    "color=?, size=?, description=?, cost_usd=?," +
                    "cost_chy=?, price_chy=? WHERE product_id=?";
            jdbcTemplate.update(sql, product.getBrand(), product.getName(),
                    product.getColor(), product.getSize(), product.getDescription(),
                    product.getCost_usd(), product.getCost_chy(),
                    product.getPrice_chy(), product.getProduct_id());
            return product.getProduct_id();
        } else {
            //insert
            final String sql = "INSERT INTO product (brand, name, color, size, description, " +
                    "cost_usd, cost_chy, price_chy) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
//            jdbcTemplate.update(sql, product.getBrand(), product.getName(),
//                    product.getColor(), product.getSize(), product.getDescription(),
//                    product.getCost_usd(), product.getCost_chy(),
//                    product.getPrice_chy());

            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(new PreparedStatementCreator() {
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    preparedStatement.setString(1, product.getBrand());
                    preparedStatement.setString(2, product.getName());
                    preparedStatement.setString(3, product.getColor());
                    preparedStatement.setString(4, product.getSize());
                    preparedStatement.setString(5, product.getDescription());
                    preparedStatement.setFloat(6, product.getCost_usd());
                    preparedStatement.setFloat(7, product.getCost_chy());
                    preparedStatement.setInt(8, product.getProduct_id());
                    return preparedStatement;
                }
            }, keyHolder);

            return keyHolder.getKey().intValue();
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

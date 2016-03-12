package com.krystal.dao;

import com.krystal.model.Order;
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
public class OrderDAOImpl implements OrderDAO {

    JdbcTemplate jdbcTemplate;

    public OrderDAOImpl(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void saveOrUpdate(Order order) {
        if (order.getOrder_id() > 0){
            //update
            String sql = "UPDATE orderitem SET customer_id=?, product_id=?, " +
                    "ship_id=?, order_status=?, payment_status=?, ship_status=?, " +
                    "actual_price=? WHERE order_id=?";
            jdbcTemplate.update(sql, order.getCustomer_id(), order.getProduct_id(),
                    order.getShip_id(), order.getOrder_status(), order.getPayment_status(),
                    order.getShip_status(), order.getActual_price(), order.getOrder_id());
        } else {
            //insert
            String sql = "INSERT INTO orderitem (customer_id, product_id, ship_id, " +
                    "order_status, payment_status, ship_status, actual_price) VALUES (" +
                    "?,?,?,?,?,?,?)";
            jdbcTemplate.update(sql, order.getCustomer_id(), order.getProduct_id(),
                    order.getShip_id(), order.getOrder_status(), order.getPayment_status(),
                    order.getShip_status(), order.getActual_price());
        }
    }

    public void delete(int order_id) {
        String sql = "DELETE FROM orderitem WHERE order_id=?";
        jdbcTemplate.update(sql, order_id);
    }

    public Order get(int order_id) {
        String sql = "SELECT * FROM orderitem WHERE order_id=" + order_id;
        return jdbcTemplate.query(sql, new ResultSetExtractor<Order>() {
            public Order extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if (resultSet.next()) {
                    Order order = new Order(resultSet.getInt("customer_id"), resultSet.getInt("product_id"),
                            resultSet.getInt("ship_id"), resultSet.getInt("order_status"),
                            resultSet.getInt("payment_status"), resultSet.getInt("ship_status"),
                            resultSet.getFloat("actual_price"));
                    order.setOrder_id(resultSet.getInt("order_id"));
                    return order;
                }
                return null;
            }
        });
    }

    public List<Order> list() {
        String sql = "SELECT * FROM orderitem";
        return jdbcTemplate.query(sql, new RowMapper<Order>() {
            public Order mapRow(ResultSet resultSet, int i) throws SQLException {
                Order order = new Order(resultSet.getInt("customer_id"), resultSet.getInt("product_id"),
                        resultSet.getInt("ship_id"), resultSet.getInt("order_status"),
                        resultSet.getInt("payment_status"), resultSet.getInt("ship_status"),
                        resultSet.getFloat("actual_price"));
                order.setOrder_id(resultSet.getInt("order_id"));
                return order;
            }
        });
    }
}

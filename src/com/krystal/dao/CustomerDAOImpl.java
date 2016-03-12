package com.krystal.dao;

import com.krystal.model.Customer;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by zhisong on 3/8/2016.
 */
public class CustomerDAOImpl implements CustomerDAO {

    private JdbcTemplate jdbcTemplate;

    public CustomerDAOImpl(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void saveOrUpdate(Customer customer) {
        if (customer.getCustomer_id() > 0){
            //update
            String sql = "UPDATE customer SET firstname=?, lastname=?, telephone=?, " +
                    "email=?, address=? WHERE customer_id=?";
            jdbcTemplate.update(sql, customer.getFirstname(), customer.getLastname(),
                    customer.getTelephone(), customer.getEmail(), customer.getAddress(),
                    customer.getCustomer_id());
        } else {
            //insert
            String sql = "INSERT INTO customer (firstname, lastname, telephone, email, address)" +
                    " VALUES (?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql, customer.getFirstname(), customer.getLastname(),
                    customer.getTelephone(), customer.getEmail(), customer.getAddress());
        }
    }

    public void delete(int customer_id) {
        String sql = "DELETE FROM customer WHERE customer_id=?";
        jdbcTemplate.update(sql, customer_id);
    }

    public Customer get(int customer_id) {
        String sql = "SELECT * FROM customer WHERE customer_id=" + customer_id;
        return jdbcTemplate.query(sql, new ResultSetExtractor<Customer>() {
            public Customer extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if (resultSet.next()){
                    Customer customer = new Customer(resultSet.getString("firstname"),
                            resultSet.getString("lastname"), resultSet.getString("telephone"),
                            resultSet.getString("email"), resultSet.getString("address"));
                    customer.setCustomer_id(resultSet.getInt("customer_id"));
                    return customer;
                }

                return null;
            }
        });

    }

    public List<Customer> list() {
        String sql = "SELECT * FROM customer";
        List<Customer> customerList = jdbcTemplate.query(sql, new RowMapper<Customer>() {
            public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
                Customer customer = new Customer(resultSet.getString("firstname"),
                        resultSet.getString("lastname"), resultSet.getString("telephone"),
                        resultSet.getString("email"), resultSet.getString("address"));
                customer.setCustomer_id(resultSet.getInt("customer_id"));
                return customer;
            }
        });

        return customerList;
    }
}

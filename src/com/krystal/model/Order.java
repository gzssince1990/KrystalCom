package com.krystal.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by zhisong on 3/9/2016.
 */
public class Order implements Serializable {
    private int order_id;
    private int customer_id;
    private int product_id;
    private int ship_id;
    private int order_status;
    private int payment_status;
    private int ship_status;

    @NotNull(message = "Price can not be empty")
    @Min(value = 0, message = "Price can not be lower than 0")
    private Float actual_price;

    public Order(){}

    public Order(int customer_id, int product_id, int ship_id, int order_status,
                 int payment_status, int ship_status, Float actual_price){
        this.customer_id = customer_id;
        this.product_id = product_id;
        this.ship_id = ship_id;
        this.order_status = order_status;
        this.payment_status = payment_status;
        this.ship_status = ship_status;
        this.actual_price = actual_price;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setShip_id(int ship_id) {
        this.ship_id = ship_id;
    }

    public int getShip_id() {
        return ship_id;
    }

    public void setOrder_status(int order_status) {
        this.order_status = order_status;
    }

    public int getOrder_status() {
        return order_status;
    }

    public void setPayment_status(int payment_status) {
        this.payment_status = payment_status;
    }

    public int getPayment_status() {
        return payment_status;
    }

    public void setShip_status(int ship_status) {
        this.ship_status = ship_status;
    }

    public int getShip_status() {
        return ship_status;
    }

    public void setActual_price(Float actual_price) {
        this.actual_price = actual_price;
    }

    public Float getActual_price() {
        return actual_price;
    }
}

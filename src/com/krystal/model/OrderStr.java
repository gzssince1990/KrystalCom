package com.krystal.model;

/**
 * Created by zhisong on 3/10/2016.
 */
public class OrderStr {
    private int order_id;
    private String customer_name;
    private String product_name;
    private String order_status;
    private String payment_status;
    private String ship_status;
    private float actual_price;

    public OrderStr(){}

    public OrderStr(int order_id, String customer_name, String product_name,
                    String order_status, String payment_status,
                    String ship_status, float actual_price){
        this.order_id = order_id;
        this.customer_name = customer_name;
        this.product_name = product_name;
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

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setShip_status(String ship_status) {
        this.ship_status = ship_status;
    }

    public String getShip_status() {
        return ship_status;
    }

    public void setActual_price(float actual_price) {
        this.actual_price = actual_price;
    }

    public float getActual_price() {
        return actual_price;
    }
}

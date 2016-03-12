package com.krystal.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by zhisong on 3/9/2016.
 */
public class Product implements Serializable{

    private int product_id;

    @Size(min = 2, max = 15, message = "The length of brand should be between 2 and 15")
    private String brand;

    @Size(min = 2, max = 15, message = "The length of product should be between 2 and 15")
    private String name;

    @Size(min = 1, max = 15, message = "The length of color should be between 1 and 15")
    private String color;

    @Size(min = 1, max = 15, message = "The length of size should be between 1 and 15")
    private String size;

    @Size(min = 5, max = 100, message = "The length of description should be between 5 and 100")
    private String description;

    @NotNull(message = "Cost in USD should not be empty")
    @Min(value = 0, message = "Cost can not be lower than 0")
    private Float cost_usd;

    @NotNull(message = "Cost in CHY should not be empty")
    @Min(value = 0, message = "Cost can not be lower than 0")
    private Float cost_chy;

    @NotNull(message = "Price in CHY should not be empty")
    @Min(value = 0, message = "Price can not be lower than 0")
    private Float price_chy;

    private String imageStr;

    public Product(){}

    public Product(String brand, String name, String color,
                   String size, String description, float cost_usd,
                   float cost_chy, float price_chy){
        this.brand = brand;
        this.name = name;
        this.color = color;
        this.size = size;
        this.description = description;
        this.cost_usd = cost_usd;
        this.cost_chy = cost_chy;
        this.price_chy = price_chy;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setCost_usd(Float cost_usd) {
        this.cost_usd = cost_usd;
    }

    public Float getCost_usd() {
        return cost_usd;
    }

    public void setCost_chy(Float cost_chy) {
        this.cost_chy = cost_chy;
    }

    public Float getCost_chy() {
        return cost_chy;
    }

    public void setPrice_chy(Float price_chy) {
        this.price_chy = price_chy;
    }

    public Float getPrice_chy() {
        return price_chy;
    }

    public void setImageStr(String imageStr) {
        this.imageStr = imageStr;
    }

    public String getImageStr() {
        return imageStr;
    }
}

package com.krystal.model;

import javax.persistence.*;

/**
 * Created by zhisong on 3/10/2016.
 */
@Entity
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue
    @Column(name = "image_id")
    private int image_id;

    @Column(name = "product_id")
    private int product_id;

    @Column(name = "image_name")
    private String image_name;

    @Column(name = "image_content")
    private byte[] image_content;

    public Image(){}

    public Image(int product_id ,String image_name, byte[] image_path){
        this.product_id = product_id;
        this.image_name = image_name;
        this.image_content = image_path;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_content(byte[] image_content) {
        this.image_content = image_content;
    }

    public byte[] getImage_content() {
        return image_content;
    }
}

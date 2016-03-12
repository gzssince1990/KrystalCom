package com.krystal.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by zhisong on 3/8/2016.
 */
public class Customer implements Serializable {

    private int customer_id;

    @Size(min = 3, max = 30, message = "The length of first name should be between 3 and 30")
    private String firstname;

    @Size(min = 3, max = 30, message = "The length of last name should be between 3 and 30")
    private String lastname;

    @Pattern(regexp="\\d{10}",
            message="Invalid phone number")
    private String telephone;

    @Email(message = "Invalid email format") @NotEmpty(message = "Email is required")
    private String email;

    @Size(min = 3, max = 50 , message = "The length of email should be between 3 and 50")
    private String address;

    public Customer(){}

    public Customer(String firstname, String lastname, String telephone, String email, String address){
        this.firstname = firstname;
        this.lastname = lastname;
        this.telephone = telephone;
        this.email = email;
        this.address = address;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}

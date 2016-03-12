package com.krystal.controller;

import com.krystal.dao.CustomerDAO;
import com.krystal.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * Created by zhisong on 3/8/2016.
 */
@Controller
public class CustomerController {

    @Autowired
    CustomerDAO customerDAO;

    @RequestMapping(value = "/")
    public ModelAndView listCustomer(ModelAndView model) throws IOException{
        List<Customer> customerList = customerDAO.list();
        model.addObject("customerList", customerList);
        model.setViewName("customer");

        return model;
    }

    @RequestMapping(value = "/newCustomer", method = RequestMethod.GET)
    public ModelAndView newCustomer(ModelAndView model){
        Customer customer = new Customer();
        model.addObject("customer", customer);
        model.setViewName("customer_form");

        return model;
    }

    @RequestMapping(value = "/saveCustomer", method = RequestMethod.POST)
    public String saveCustomer(@Valid Customer customer, BindingResult result){

        if (result.hasErrors()){
            /*
            ModelAndView model = new ModelAndView("customer_form");
            model.addObject("customer", customer);
            return model;
            */
            return "customer_form";
        }

        customerDAO.saveOrUpdate(customer);
        //return new ModelAndView("redirect:/");
        return "redirect:/";
    }

    @RequestMapping(value = "deleteCustomer", method = RequestMethod.GET)
    public ModelAndView deleteCustomer(HttpServletRequest request){
        int customer_id = Integer.parseInt(request.getParameter("customer_id"));
        customerDAO.delete(customer_id);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/editCustomer", method = RequestMethod.GET)
    public ModelAndView editCustomer(HttpServletRequest request){
        int customer_id = Integer.parseInt(request.getParameter("customer_id"));
        Customer customer = customerDAO.get(customer_id);
        ModelAndView model = new ModelAndView("customer_form");
        model.addObject("customer", customer);

        return model;
    }
}

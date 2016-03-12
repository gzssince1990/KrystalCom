package com.krystal.controller;

import com.krystal.dao.CustomerDAO;
import com.krystal.dao.OrderDAO;
import com.krystal.dao.ProductDAO;
import com.krystal.model.Customer;
import com.krystal.model.Order;
import com.krystal.model.OrderStr;
import com.krystal.model.Product;
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
import java.util.*;

/**
 * Created by zhisong on 3/9/2016.
 */
@Controller
public class OrderController {

    //Constant
    private final int ORDER_NO_CODE = 0;
    private final String ORDER_NO = "Not order";
    private final int ORDER_YES_CODE = 1;
    private final String ORDER_YES = "Ordered";
    private final int PAY_NO_CODE = 0;
    private final String PAY_NO = "Not pay";
    private final int PAY_YES_CODE = 1;
    private final String PAY_YES = "Payed";
    private final int SHIP_NO_CODE = 0;
    private final String SHIP_NO = "Not ship";
    private final int SHIP_SHIPPED_CODE = 1;
    private final String SHIP_SHIPPED = "Shipped";
    private final int SHIP_SHIPPING_CODE = 2;
    private final String SHIP_SHIPPING = "On the way";
    private final int SHIP_RECEIVED_CODE = 3;
    private final String SHIP_RECEIVED = "Received";

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private CustomerDAO customerDAO;

    @RequestMapping(value = "/order")
    public ModelAndView listOrder(ModelAndView model)throws IOException{
        List<Order> orderList = orderDAO.list();
        List<OrderStr> orderStrList = new LinkedList<OrderStr>();
        for (Order order: orderList){
            Customer customer = customerDAO.get(order.getCustomer_id());
            Product product = productDAO.get(order.getProduct_id());
            int order_id = order.getOrder_id();
            String customer_name = customer.getFirstname()+" "+customer.getLastname();
            String product_name = product.getBrand()+" "+product.getName();
            String order_status = order.getOrder_status()==ORDER_NO_CODE? ORDER_NO:ORDER_YES;
            String payment_status = order.getPayment_status()==PAY_NO_CODE? PAY_NO:PAY_YES;
            String ship_status = order.getShip_status()==SHIP_NO_CODE? SHIP_NO:
                    order.getShip_status()==SHIP_SHIPPED_CODE?  SHIP_SHIPPED:
                            order.getShip_status()==SHIP_SHIPPING_CODE? SHIP_SHIPPING:SHIP_RECEIVED;
            float actual_price = order.getActual_price();

            OrderStr orderStr = new OrderStr(order_id,customer_name,product_name,order_status,
                    payment_status,ship_status,actual_price);
            orderStrList.add(orderStr);
        }


        model.addObject("orderStrList", orderStrList);
        model.setViewName("order");

        return model;
    }

    @RequestMapping(value = "/newOrder", method = RequestMethod.GET)
    public ModelAndView newOrder(ModelAndView model){
        Order order = new Order();
        model.addObject("order", order);
        model.setViewName("order_form");

        return model;
    }

    @RequestMapping(value = "/saveOrder", method = RequestMethod.POST)
    public ModelAndView saveOrder(@Valid Order order, BindingResult result){
        if (result.hasErrors()){
            ModelAndView model = new ModelAndView("order_product");
            model.addObject("order", order);

            //Get the customers
            List<Customer> customerList = customerDAO.list();
            Map<Integer, String> customer_id = new LinkedHashMap<Integer, String>();
            for (Customer customer: customerList){
                customer_id.put(customer.getCustomer_id(),
                        customer.getFirstname()+" "+customer.getLastname());
            }
            model.addObject("customerList", customer_id);

            //Get the product name
            Product product = productDAO.get(order.getProduct_id());
            String productName = product.getBrand() + " " + product.getName();
            model.addObject("productName", productName);

            //Status
            Map<Integer, String> orderStatus = new LinkedHashMap<Integer, String>();
            orderStatus.put(ORDER_NO_CODE, ORDER_NO);
            orderStatus.put(ORDER_YES_CODE, ORDER_YES);
            Map<Integer, String> paymentStatus = new LinkedHashMap<Integer, String>();
            paymentStatus.put(PAY_NO_CODE, PAY_NO);
            paymentStatus.put(PAY_YES_CODE, PAY_YES);
            Map<Integer, String> shipStatus = new LinkedHashMap<Integer, String>();
            shipStatus.put(SHIP_NO_CODE, SHIP_NO);
            shipStatus.put(SHIP_SHIPPED_CODE, SHIP_SHIPPED);
            shipStatus.put(SHIP_SHIPPING_CODE, SHIP_SHIPPING);
            shipStatus.put(SHIP_RECEIVED_CODE, SHIP_RECEIVED);
            model.addObject("orderStatus", orderStatus);
            model.addObject("paymentStatus", paymentStatus);
            model.addObject("shipStatus", shipStatus);

            return model;
        }

        orderDAO.saveOrUpdate(order);
        return new ModelAndView("redirect:/order");
    }

    @RequestMapping(value = "/deleteOrder", method = RequestMethod.GET)
    public ModelAndView deleteOrder(HttpServletRequest request){
        int order_id = Integer.parseInt(request.getParameter("order_id"));
        orderDAO.delete(order_id);
        return new ModelAndView("redirect:/order");
    }

    @RequestMapping(value = "/editOrder", method = RequestMethod.GET)
    public ModelAndView editOrder(HttpServletRequest request){
        int order_id = Integer.parseInt(request.getParameter("order_id"));
        Order order = orderDAO.get(order_id);
        ModelAndView model = new ModelAndView("order_product");
        model.addObject("order", order);

        //Get the customers
        List<Customer> customerList = customerDAO.list();
        Map<Integer, String> customer_id = new LinkedHashMap<Integer, String>();
        for (Customer customer: customerList){
            customer_id.put(customer.getCustomer_id(),
                    customer.getFirstname()+" "+customer.getLastname());
        }
        model.addObject("customerList", customer_id);

        //Get the product name
        Product product = productDAO.get(order.getProduct_id());
        String productName = product.getBrand() + " " + product.getName();
        model.addObject("productName", productName);

        //Status
        Map<Integer, String> orderStatus = new LinkedHashMap<Integer, String>();
        orderStatus.put(ORDER_NO_CODE, ORDER_NO);
        orderStatus.put(ORDER_YES_CODE, ORDER_YES);
        Map<Integer, String> paymentStatus = new LinkedHashMap<Integer, String>();
        paymentStatus.put(PAY_NO_CODE, PAY_NO);
        paymentStatus.put(PAY_YES_CODE, PAY_YES);
        Map<Integer, String> shipStatus = new LinkedHashMap<Integer, String>();
        shipStatus.put(SHIP_NO_CODE, SHIP_NO);
        shipStatus.put(SHIP_SHIPPED_CODE, SHIP_SHIPPED);
        shipStatus.put(SHIP_SHIPPING_CODE, SHIP_SHIPPING);
        shipStatus.put(SHIP_RECEIVED_CODE, SHIP_RECEIVED);
        model.addObject("orderStatus", orderStatus);
        model.addObject("paymentStatus", paymentStatus);
        model.addObject("shipStatus", shipStatus);

        return model;
    }

    @RequestMapping(value = "/orderProduct", method = RequestMethod.GET)
    public ModelAndView orderProduct(HttpServletRequest request){
        int product_id = Integer.parseInt(request.getParameter("product_id"));
        Order order = new Order();
        order.setProduct_id(product_id);
        ModelAndView model = new ModelAndView("order_product");
        model.addObject("order", order);

        //Get the customers
        List<Customer> customerList = customerDAO.list();
        Map<Integer, String> customer_id = new LinkedHashMap<Integer, String>();
        for (Customer customer: customerList){
            customer_id.put(customer.getCustomer_id(),
                    customer.getFirstname()+" "+customer.getLastname());
        }
        model.addObject("customerList", customer_id);

        //Get the product name
        Product product = productDAO.get(product_id);
        String productName = product.getBrand() + " " + product.getName();
        model.addObject("productName", productName);

        //Status
        Map<Integer, String> orderStatus = new LinkedHashMap<Integer, String>();
        orderStatus.put(ORDER_NO_CODE, ORDER_NO);
        orderStatus.put(ORDER_YES_CODE, ORDER_YES);
        Map<Integer, String> paymentStatus = new LinkedHashMap<Integer, String>();
        paymentStatus.put(PAY_NO_CODE, PAY_NO);
        paymentStatus.put(PAY_YES_CODE, PAY_YES);
        Map<Integer, String> shipStatus = new LinkedHashMap<Integer, String>();
        shipStatus.put(SHIP_NO_CODE, SHIP_NO);
        shipStatus.put(SHIP_SHIPPED_CODE, SHIP_SHIPPED);
        shipStatus.put(SHIP_SHIPPING_CODE, SHIP_SHIPPING);
        shipStatus.put(SHIP_RECEIVED_CODE, SHIP_RECEIVED);
        model.addObject("orderStatus", orderStatus);
        model.addObject("paymentStatus", paymentStatus);
        model.addObject("shipStatus", shipStatus);

        return model;
    }
}

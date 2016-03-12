package com.krystal.controller;

import com.krystal.dao.ImageDAO;
import com.krystal.dao.ProductDAO;
import com.krystal.model.Image;
import com.krystal.model.Product;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * Created by zhisong on 3/9/2016.
 */
@Controller
public class ProductController {

    @Autowired
    ProductDAO productDAO;

    @Autowired
    ImageDAO imageDAO;

    @RequestMapping(value = "/product")
    public ModelAndView listProduct(ModelAndView model) throws IOException{
        List<Product> productList = productDAO.list();

        for (Product product: productList){
            List<Image> imageList = imageDAO.getImages(product.getProduct_id());
            if (!imageList.isEmpty()){

                product.setImageStr(getEncodedString(imageList.get(0).getImage_id()));
            }else {
                product.setImageStr(null);
            }
        }

        model.addObject("productList", productList);
        model.setViewName("product");


        return model;
    }

    private String getEncodedString(int image_id){
        byte[] imageByte = imageDAO.getImage(image_id).getImage_content();
        byte[] encodeBase64 = Base64.encodeBase64(imageByte);
        String encodedString = new String(encodeBase64);

        return encodedString;
    }

    @RequestMapping(value = "/images/{id}", method = RequestMethod.GET)
    public ModelAndView getImage(@PathVariable("id") int image_id){
        byte[] imageByte = imageDAO.getImage(image_id).getImage_content();
        byte[] encodeBase64 = Base64.encodeBase64(imageByte);
        String encodedString = new String(encodeBase64);
        ModelAndView model = new ModelAndView("image");
        model.addObject("image", encodedString);

        return model;
    }

    @RequestMapping(value = "/newProduct", method = RequestMethod.GET)
    public ModelAndView newProduct(ModelAndView model){
        Product product = new Product();
        model.addObject("product", product);
        model.setViewName("product_form");

        return model;
    }

    @RequestMapping(value = "/saveProduct", method = RequestMethod.POST)
    public ModelAndView saveProduct(@Valid Product product, BindingResult result,
                                    @RequestParam(value = "image", required = false) MultipartFile image)
    throws IOException{

        if (result.hasErrors()){
            ModelAndView model = new ModelAndView("product_form");
            model.addObject("product", product);
            return model;
        }

        Image img = new Image();

        if (!image.isEmpty()){
            img.setProduct_id(product.getProduct_id());
            img.setImage_name(image.getOriginalFilename());
            img.setImage_content(image.getBytes());
            imageDAO.save(img);
        }

        productDAO.saveOrUpdate(product);
        return new ModelAndView("redirect:/product");
    }

    @RequestMapping(value = "/deleteProduct", method = RequestMethod.GET)
    public ModelAndView deleteProduct(HttpServletRequest request){
        int product_id = Integer.parseInt(request.getParameter("product_id"));
        productDAO.delete(product_id);
        return new ModelAndView("redirect:/product");
    }

    @RequestMapping(value = "/editProduct", method = RequestMethod.GET)
    public ModelAndView editProduct(HttpServletRequest request){
        int product_id = Integer.parseInt(request.getParameter("product_id"));
        Product product = productDAO.get(product_id);
        ModelAndView model = new ModelAndView("product_form");
        model.addObject("product", product);

        return model;
    }

}

package com.bestcommerce.controllers;

import com.bestcommerce.entities.Product;
import com.bestcommerce.exceptions.ProductNotFoundException;
import com.bestcommerce.services.CategoryService;
import com.bestcommerce.services.ProductService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @RequestMapping(value = "",method = RequestMethod.GET)
    public List<Product> listProducts(){
        List<Product> products =  productService.getAllProducts();
        return products;
    }

    @RequestMapping(method = RequestMethod.GET,path = "/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id) {

       try {
           Product product = productService.getProductById(id);
           return new ResponseEntity<Product>(product,HttpStatus.OK);
       }
       catch (Exception ex){
           throw new ProductNotFoundException("Product with id = " + id + " does not exist");
       }
    }




}

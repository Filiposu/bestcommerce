package com.bestcommerce.controllers;

import com.bestcommerce.entities.Category;
import com.bestcommerce.entities.Product;
import com.bestcommerce.exceptions.ProductNotFoundException;
import com.bestcommerce.requests.DiscountRequest;
import com.bestcommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @RequestMapping(value = "",method = RequestMethod.GET)
    public List<Product> listProducts(@RequestParam int page,@RequestParam int size,@RequestParam int priceDir,@RequestParam int inventoryDir){

        List<Sort.Order> orders = new ArrayList<Sort.Order>();

        orders.add(new Sort.Order(Sort.Direction.valueOf(String.valueOf(priceDir == 1? Sort.Direction.DESC:Sort.Direction.ASC)),"price"));
        orders.add(new Sort.Order(Sort.Direction.valueOf(String.valueOf(inventoryDir == 1? Sort.Direction.DESC:Sort.Direction.ASC)),"inventory"));

        //Pageable pageRequest = PageRequest.of(page,size, Sort.by("price").descending().and(Sort.by("inventory").descending()));
        Pageable pageRequest = PageRequest.of(page,size, Sort.by(orders));

        List<Product> products =  productService.getAllProducts(pageRequest);
        products = products.stream().filter(p->p.getInventory()>0).collect(Collectors.toList());
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

    @RequestMapping(method = RequestMethod.POST,value = "/create")
    public Long product (@RequestBody Product product){

        Product newProduct = new Product();
        Category category = new Category();
        category.setId((long) 2);
        newProduct.setCategory(category);
        newProduct.setDiscount(10);
        newProduct.setPrice((double) 600);
        newProduct.setDiscount_end(LocalDate.now().plusDays(10));
        newProduct.setDiscount_start(LocalDate.now());

        Long productId;
        System.out.println("Access achieved");
        try {
           productId = productService.save(newProduct);
        }
       catch (Exception ex){
           throw ex;
       }

        return productId;
    }

    @RequestMapping(value = "/{id}/discount",method = RequestMethod.POST)
    public Product setDiscount(@PathVariable long id, @RequestBody DiscountRequest discount) {

        Product product = productService.setDiscount(id,discount.getDiscount(),discount.getStart(),discount.getEnd());
        return product;
    }




}

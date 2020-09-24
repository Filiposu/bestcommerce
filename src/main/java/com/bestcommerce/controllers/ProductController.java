package com.bestcommerce.controllers;

import com.bestcommerce.entities.Category;
import com.bestcommerce.entities.Country;
import com.bestcommerce.entities.Merchant;
import com.bestcommerce.entities.Product;
import com.bestcommerce.exceptions.ProductNotFoundException;
import com.bestcommerce.repository.CountryRepository;
import com.bestcommerce.requests.DiscountRequest;
import com.bestcommerce.services.MerchantService;
import com.bestcommerce.services.ProductService;
import com.bestcommerce.services.impl.UserDetailsImpl;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private MerchantService merchantService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public List<Product> listProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "1") int size, @RequestParam(defaultValue = "1") int priceDir, @RequestParam(defaultValue = "1") int inventoryDir){

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

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(method = RequestMethod.POST,value = "/create")
    public Product product (Authentication authentication,@RequestBody Product product) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Merchant merchant = merchantService.getMerchandByUserId(userDetails.getId());
        product.setMerchant(merchant);
        return productService.save(product);
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/{id}/discount",method = RequestMethod.POST)
    public Product setDiscount(@PathVariable long id, @RequestBody DiscountRequest discount) {
        Product product = productService.setDiscount(id,discount.getDiscount(),discount.getStart(),discount.getEnd());
        return product;
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/{product_id}/rollout/{country_id}",method = RequestMethod.POST)
    public Product postForRollout(@PathVariable int product_id,@PathVariable int country_id,Authentication authentication) throws NotFoundException {

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Merchant merchant = merchantService.getMerchandByUserId(userDetails.getId());

        Product productFound = productService.getProductById((long) product_id);
        if(productFound.getMerchant().getId() != merchant.getId()){
            throw new AccessDeniedException("You can only post rollout for your own product");
        }
        else {
            Country country = countryRepository.findById(country_id).orElseThrow(() -> new ProductNotFoundException("Country could not be found"));
            Set<Country> countrySet = productFound.getRollout_countries();
            countrySet.add(country);
            productFound.setRollout_countries(countrySet);
            productFound = productService.save(productFound);
        }

        return productFound;
    }




}

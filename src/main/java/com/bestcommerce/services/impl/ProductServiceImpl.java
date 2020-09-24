package com.bestcommerce.services.impl;

import com.bestcommerce.entities.Category;
import com.bestcommerce.entities.Merchant;
import com.bestcommerce.entities.Product;
import com.bestcommerce.exceptions.MerchantNotFoundException;
import com.bestcommerce.exceptions.ProductNotFoundException;
import com.bestcommerce.repository.CategoryRepository;
import com.bestcommerce.repository.ProductRepository;
import com.bestcommerce.services.MerchantService;
import com.bestcommerce.services.ProductService;
import com.bestcommerce.utils.EmailServiceImpl;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Product> getAllProducts(Pageable pageRequest) {

        return productRepository.findAll(pageRequest).toList();
    }


    @Override
    public Product getProductById(Long id){
        Optional<Product> product = productRepository.findById(id);
        return product.get();
    }

    @Override
    public Product save(Product product) {
        Long category_id = product.getCategory().getId();
        Category category = categoryRepository.findById(category_id).orElseThrow(()-> new ProductNotFoundException("Could not find the category to set for the product"));
        product.setCategory(category);
        Product savedProduct = null;
        try {
            savedProduct = productRepository.save(product);
        }
        catch (Exception ex){
            throw ex;
        }
        return savedProduct;
    }

    @Override
    public Product setDiscount(Long id, Double discount, LocalDate start,LocalDate end,Merchant merchant) {
        Optional<Product> productToDiscount = productRepository.findById(id);
        if(productToDiscount.isPresent()){
            if(productToDiscount.get().getMerchant().getId()==merchant.getId()){
                Product product = productToDiscount.get();
                String email =  merchant.getUser().getUsername();
                product.setDiscount_start(start);
                product.setDiscount_end(end);
                product.setDiscount((double)discount);
                Double originalPrice = product.getPrice();
                Double percentage = discount/100;
                Double discountedPrice = originalPrice-(originalPrice*(discount/100));
                StringBuilder builder = new StringBuilder();
                builder.append( "Congratulations discount for the product " + product.getName() + " has been set ");
                builder.append(System.getProperty("line.separator"));
                builder.append( "Discounted price for your product is " + discountedPrice);
                builder.append(System.getProperty("line.separator"));
                builder.append( "Difference between original and discounted price will be " + String.valueOf(product.getPrice()-discountedPrice));
                builder.append(System.getProperty("line.separator"));
                builder.append( "Discount will go online on " + product.getDiscount_start());
                builder.append(System.getProperty("line.separator"));
                builder.append(System.getProperty("line.separator"));
                emailService.sendSimpleMessage(email,"New Discount",builder.toString());
                return productRepository.save(product);
            }
            else {
                throw new MerchantNotFoundException("Merchant coud not be found. You can put discount only on your own product");
            }
        }
        else {
            throw new ProductNotFoundException("Cant set discount because product was not found");
        }
    }


}

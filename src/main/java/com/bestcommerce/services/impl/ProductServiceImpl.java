package com.bestcommerce.services.impl;

import com.bestcommerce.entities.Product;
import com.bestcommerce.exceptions.ProductNotFoundException;
import com.bestcommerce.repository.ProductRepository;
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


    public List<Product> getAllProducts(Pageable pageRequest) {

        return productRepository.findAll(pageRequest).toList();
    }


    @Override
    public Product getProductById(Long id) throws NotFoundException {
        Optional<Product> product = productRepository.findById(id);
        return product.get();
    }

    @Override
    public Long save(Product product) {

        Long productId = productRepository.save(product).getId();
        return productId;
    }

    @Override
    public Product setDiscount(Long id, Integer discount, LocalDate start,LocalDate end) {
        Optional<Product> productToDiscount = productRepository.findById(id);

        if(productToDiscount.isPresent()){
            Product product1 = productToDiscount.get();
            String emailAddress = "togrul125@gmail.com";
            product1.setDiscount_start(start);
            product1.setDiscount_end(end);
            product1.setDiscount((double)discount);

            Double discountedPrice = product1.getPrice()-(product1.getPrice()*(discount/100));

            StringBuilder builder = new StringBuilder();
            builder.append( "Congratulations discount for the product " + product1.getName() + " has been set ");
            builder.append(System.getProperty("line.separator"));
            builder.append( "Discounted price for your product is " + discountedPrice);
            builder.append(System.getProperty("line.separator"));
            builder.append( "Difference between original and discounted price will be " + String.valueOf(product1.getPrice()-discountedPrice));
            builder.append(System.getProperty("line.separator"));
            builder.append( "Discount will go online on " + product1.getDiscount_start());
            builder.append(System.getProperty("line.separator"));
            builder.append(System.getProperty("line.separator"));


            emailService.sendSimpleMessage(emailAddress,"New Discount",builder.toString());
            return productRepository.save(product1);
        }
        else {
            throw new ProductNotFoundException("Cant set discount because product was not found");
        }
    }


}

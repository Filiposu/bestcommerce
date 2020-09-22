package com.bestcommerce.services.impl;

import com.bestcommerce.entities.Product;
import com.bestcommerce.exceptions.ProductNotFoundException;
import com.bestcommerce.repository.ProductRepository;
import com.bestcommerce.services.ProductService;
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
            product1.setDiscount_start(start);
            product1.setDiscount_end(end);
            product1.setDiscount(discount);
            return productRepository.save(product1);
        }
        else {
            throw new ProductNotFoundException("Cant set discount because product was not found");
        }
    }


}

package com.bestcommerce.services.impl;

import com.bestcommerce.entities.Product;
import com.bestcommerce.exceptions.ProductNotFoundException;
import com.bestcommerce.repository.ProductRepository;
import com.bestcommerce.services.CategoryService;
import com.bestcommerce.services.ProductService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) throws NotFoundException {
        Optional<Product> product = productRepository.findById(id);
        return product.get();
    }


}

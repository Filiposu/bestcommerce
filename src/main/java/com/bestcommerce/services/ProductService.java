package com.bestcommerce.services;


import com.bestcommerce.entities.Product;
import javassist.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public List<Product> getAllProducts();
    public Product getProductById(Long id) throws NotFoundException;
}

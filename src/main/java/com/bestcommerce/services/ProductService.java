package com.bestcommerce.services;


import com.bestcommerce.entities.Product;
import javassist.NotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import java.time.LocalDate;
import java.util.List;

public interface ProductService {
    public List<Product> getAllProducts(Pageable pageable);
    public Product getProductById(Long id) throws NotFoundException;
    public Product save(Product product);
    public Product setDiscount(Long id, Integer discount, LocalDate start,LocalDate end);
}

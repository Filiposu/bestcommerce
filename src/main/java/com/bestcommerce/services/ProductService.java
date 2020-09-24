package com.bestcommerce.services;


import com.bestcommerce.entities.Merchant;
import com.bestcommerce.entities.Product;
import javassist.NotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import java.time.LocalDate;
import java.util.List;

public interface ProductService {
    List<Product> getAllProducts(Pageable pageable);
    Product getProductById(Long id) throws NotFoundException;
    Product save(Product product);
    Product setDiscount(Long id, Double discount, LocalDate start, LocalDate end, Merchant merchant);
}

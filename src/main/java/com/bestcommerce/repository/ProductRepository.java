package com.bestcommerce.repository;

import com.bestcommerce.entities.Country;
import com.bestcommerce.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product,Long> {

    public List<Product> findAll();

    @Override
    Page<Product> findAll(Pageable pageable);

    Optional<Product > findByName(String name);

    @Override
    Optional<Product> findById(Long id);






}

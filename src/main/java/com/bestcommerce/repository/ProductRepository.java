package com.bestcommerce.repository;

import com.bestcommerce.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product,Long> {

    public List<Product> findAll();

    @Override
    Page<Product> findAll(Pageable pageable);

    @Override
    Optional<Product> findById(Long id);



}

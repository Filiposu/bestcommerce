package com.bestcommerce.repository;

import com.bestcommerce.entities.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Long> {

    @Override
    List<Category> findAll();

    @Override
    Optional<Category> findById(Long aLong);

    Optional<Category> findByName(String name);





}

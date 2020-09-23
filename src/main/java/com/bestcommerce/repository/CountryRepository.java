package com.bestcommerce.repository;

import com.bestcommerce.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CountryRepository extends JpaRepository<Country,Integer> {

    @Override
    List<Country> findAll();

    Optional<Country> findByName(String name);
}

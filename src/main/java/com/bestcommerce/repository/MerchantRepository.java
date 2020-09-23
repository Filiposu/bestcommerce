package com.bestcommerce.repository;


import com.bestcommerce.entities.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant,Long> {

    Optional<Merchant> findByUserId(Long id);

}
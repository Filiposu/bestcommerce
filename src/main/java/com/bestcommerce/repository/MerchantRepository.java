package com.bestcommerce.repository;


import com.bestcommerce.entities.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant,Long> {

    Optional<Merchant> findByUserId(Long id);

    Merchant findById(Integer id);

    @Query("SELECT m FROM Merchant m WHERE m.owner_name=:name")
    Optional<Merchant> findMerchantByOwner_name(@Param("name") String name);

    @Query("SELECT m FROM Merchant m WHERE m.merchant_name=:name")
    Optional<Merchant> findByMerchant_name(@Param("name") String name);








//    Optional<Merchant> findByOwner_name(String name);


}

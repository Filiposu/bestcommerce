package com.bestcommerce.repository;

import com.bestcommerce.entities.Role;
import com.bestcommerce.entities.Role_Enum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(Role_Enum name);
}
